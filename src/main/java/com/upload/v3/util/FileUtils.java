package com.upload.v3.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 因为用过spmvc后所有请求都被spmvc处理过了，所以按照servlet的处理方式是不行的，这里仅仅用于spring mvc的控制器
 * 
 * @author Windos
 *
 */
public class FileUtils {

	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	static String urlPrifix = "";
	/**
	 * base64文件上传，可多个，可一个name多个
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public static JSONObject uploadBase64(HttpServletRequest req) {
		JSONObject rs = new JSONObject();
		// 获取上传路径
		String savePath = getSavePath(req);
		// 获取请求参数
		Map<String, String[]> reqMap = req.getParameterMap();
		logger.info(JSONObject.toJSONString(reqMap.keySet()));
		// 获取请求参数itertor
		Iterator<Entry<String, String[]>> i = reqMap.entrySet().iterator();
		// 遍历
		try {
			// 这里的try cache可以确定能读取到上传的图片信息，而下面的while循环却不确定，他俩组合却一定能读取到上传的图片数据
			List<String> lines = org.apache.commons.io.IOUtils.readLines(req.getInputStream(), "utf-8");
			if (lines.size() > 0) {
				for (String line : lines) {
					line = URLDecoder.decode(line, "utf-8");
					String[] vals = line.split("=");
					for (String val : vals) {
						if (val.length()>5*1024) {
							String fileName=decodeBase64AndSaveFile(savePath,val);
							// 返回文件的访问路径
							String fileUrl = urlPrifix + req.getServletContext().getContextPath() + "/upload/"
									+ fileName;
							rs.put(System.currentTimeMillis() + "", fileUrl);
						}
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (i.hasNext()) {
			Entry<String, String[]> entry = i.next();
			String key = entry.getKey();
			// 如果没有name值，放弃
			if (StringUtils.isBlank(key)) {
				continue;
			}
			String[] value = entry.getValue();
			for (String val : value) {
				// 如果value值小于5Kb,放弃,5kb的图片不允许
				if (val.length() < 5 * 1024) {
					continue;
				}

				try {
					String fileName = decodeBase64AndSaveFile(savePath, val);
					// 返回文件的访问路径
					String fileUrl = urlPrifix + req.getServletContext().getContextPath() + "/upload/" + fileName;
					// 如果只有一个,那么就是一个name值对应一个图片的url
					if (value.length == 1) {
						rs.put(key, fileUrl);
					} else {
						// 如果只有多个,那么就是一个name值对应一个数组图片的url
						JSONArray array = rs.getJSONArray(key);
						if (array == null) {
							array = new JSONArray();
						}
						array.add(fileUrl);
						rs.put(key, array);
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
		return rs;
	}

	private static String decodeBase64AndSaveFile(String savePath, String val) {
		String ext = ".png";
		// base64逗号前面的字符为描述字符，所以截取一下正文
		if (val.substring(val.indexOf(":") + 1, val.indexOf(";")).toLowerCase().indexOf("image/gif") > -1) {
			ext = ".gif";
		}
		val = val.substring(val.lastIndexOf(",") + 1);
		// base64转为二进制
		byte[] fileBytes = Base64.decodeBase64(val);
		// 文件名后缀统一为png,文件名为纯数字格式的时间戳,避免文件名冲突
		String fileName = System.currentTimeMillis() + ext;
		// 将二进制写入到磁盘
		try {
			FileCopyUtils.copy(fileBytes, new FileOutputStream(savePath + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 返回文件的访问路径
		return fileName;
	}
	/**
	 * 二进制文件上传，可多个，可一个name多个
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public static JSONObject uploadBinary(HttpServletRequest req) {
		JSONObject rs = new JSONObject();
		// 获取保存路径
		String savePath = getSavePath(req);
		// 判断是否是二进制文件上传请求
		if (req instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest uploadReq = (MultipartHttpServletRequest) req;
			// 获取所有上传的文件名字
			Iterator<String> fileNames = uploadReq.getFileNames();
			while (fileNames.hasNext()) {
				String originalFileName = fileNames.next();
				List<MultipartFile> files = uploadReq.getFiles(originalFileName);
				for (MultipartFile multipartFile : files) {
					try {
						String fileName = System.currentTimeMillis() + "."
								+ FilenameUtils.getExtension(multipartFile.getOriginalFilename());
						// 写入磁盘
						multipartFile.transferTo(new File(savePath + fileName));
						// 获取文件访问url
						String fileUrl = urlPrifix + req.getServletContext().getContextPath() + "/upload/" + fileName;
						if (files.size() > 1) {
							rs.put(originalFileName, fileUrl);
						} else {
							// 如果只有多个,那么就是一个name值对应一个数组图片的url
							JSONArray array = rs.getJSONArray(fileName);
							if (array == null) {
								array = new JSONArray();
							}
							array.add(fileUrl);
							rs.put(originalFileName, array);
						}
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
		}
		return rs;
	}

	/**
	 * 获取保存路径
	 * 
	 * @param req
	 * @return
	 */
	public static String getSavePath(HttpServletRequest req) {
		// 先创建一个路径字符串，然后创建文件夹，再然后将windows路径转为linux路径
		String dirPath = req.getServletContext().getRealPath("/") + "upload\\";
		dirPath = dirPath.replace("\\", "/");
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (file.getAbsolutePath().endsWith("/")) {
			return file.getAbsolutePath().replace("\\", "/");
		}
		return file.getAbsolutePath().replace("\\", "/") + "/";
	}
}
