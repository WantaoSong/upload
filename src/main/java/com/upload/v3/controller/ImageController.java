package com.upload.v3.controller;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.upload.v3.common.ViewType;
import com.upload.v3.entity.ImgResources;
import com.upload.v3.entity.ImgResourcesExample;
import com.upload.v3.entity.ImgResourcesExample.Criteria;
import com.upload.v3.mapper.ImgResourcesMapper;
import com.upload.v3.util.FileUtils;

/**
 * 图片上传控制器.<br/>
 * 1.上传数量可灵活配置<br/>
 * 2.上传时加入了签名校验，签名校验不通过则返回404页面.所以此项目可以作为自己的图片上传服务器使用<br/>
 * 3.上传地址示例:localhost:8080/upload/img/page/update/avatar/qwfqwfqwf?count=3&callback=uploadSuccess&sign=de25b5c9e43eb3f686a4ec0067c5c609<br/>
 * 从avatar开始全部为参数<br/>
 * avatar 代表图片分类<br/>
 * qwfqwfqwf 图片gid，groupId<br/>
 * count 代表该组图片数量<br/>
 * callback代表回调函数<br/>
 * sign为MD5(gid+自定义字符串)签名 <br/>
 * 4.如果页面地址数量大于数据库保存的数量，那么按插入时间顺序倒序取count条记录。<br/>
 * 5.只有已上传的图片可以在上传页面有删除按钮
 * 
 * @author Windos
 *
 */
@Controller
@RequestMapping("img")
public class ImageController {


	/**
	 * 这里直接注入mapper
	 */
	@Autowired
	private ImgResourcesMapper imgMapper;

	/**
	 * 定位到图片上传页面，如果有已经上传的图片，查询出count张，返回到页面
	 * 
	 * @param type
	 *            图片分类参数，字符串，必传
	 * @param gid
	 *            图片分组id，字符串，必传
	 * @param count
	 *            图片上传数量，Integer，必传
	 * @param sign
	 *            签名,32位md5 字符串
	 * @param req
	 *            sp注入
	 * @param res
	 *            sp注入
	 * @return
	 */
	@RequestMapping("page/update/{type}/{gid}")
	public String listPage(@PathVariable String type, @PathVariable String gid, @RequestParam Integer count,
			@RequestParam(required = true) String sign,
			HttpServletRequest req, HttpServletResponse res) {
		// 设置跨越参数
		setCrossDomainParam(res);

		/*********** 签名校验 **************/
		String testSign = DigestUtils.md5Hex(gid + "hncxyfb");
		if (testSign.equals(sign)) {
			ImgResourcesExample example = new ImgResourcesExample();
			example.createCriteria().andTypeEqualTo(type).andGidEqualTo(gid);
			if (count > 100 || count < 1) {
				count = 10;
			}

			/******** pageHelper分页,参数回传页面 *******/
			PageHelper.startPage(1, count, " _create_time desc ");
			List<ImgResources> data = imgMapper.selectByExample(example);
			req.setAttribute("count", count);
			req.setAttribute("type", type);
			req.setAttribute("pid", gid);
			req.setAttribute("data", data);
			req.setAttribute("sign", sign);
			String callBack = req.getParameter("callback");
			if (StringUtils.isNotBlank(callBack)) {
				req.setAttribute("callback", callBack);
			}
			return ViewType.Jsp.getView("upload_multiple");
		}
		return ViewType.Jsp.getView("404");
	}

	/**
	 * 查询图片列表
	 * 
	 * @param type
	 *            必传参数，图片分类
	 * @param gid
	 *            必传参数，图片分组id字符串
	 * @param callBack
	 *            可选参数，js回调函数
	 * @param pn
	 *            可选参数，页码数量
	 * @param pz
	 *            可选参数，一页的数据量
	 * @param req
	 *            sping注入
	 * @param res
	 *            sping注入
	 */
	@RequestMapping("search/{type}/{gid}")
	public void listJSON(@PathVariable String type, @PathVariable String gid,
			@RequestParam(required = false) String callBack, @RequestParam(required = false) Integer pn,
			@RequestParam(required = false) Integer pz, HttpServletRequest req, HttpServletResponse res) {
		setCrossDomainParam(res);
		ImgResourcesExample example = new ImgResourcesExample();
		example.createCriteria().andTypeEqualTo(type).andGidEqualTo(gid);

		if (pn == null || pn < 1) {
			pn = 1;
		}
		if (pz == null || pz < 1) {
			pz = 10;
		}
		PageHelper.startPage(pn, pz);

		example.setOrderByClause(" _create_time desc ");
		List<ImgResources> data = imgMapper.selectByExample(example);

		try {

			if (StringUtils.isNotBlank(callBack)) {
				res.getWriter().write(callBack + "(" + JSONObject.toJSONString(data) + ")");
			} else {
				res.getWriter().write(JSONObject.toJSONString(data));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping("upload/base64")
	public JSONObject uploadBase64(HttpServletRequest req, HttpServletResponse res) {
		setCrossDomainParam(res);

		JSONObject rs = new JSONObject();
		String type = req.getParameter("type");
		String reqGid = req.getParameter("gid");

		if (StringUtils.isBlank(type) || StringUtils.isBlank(reqGid)) {
			rs.put("errMsg", "请求参数type不能为空,gid不能为空.");
			return rs;
		}

		String sign = req.getParameter("sign");
		String testSign = DigestUtils.md5Hex(reqGid + "hncxyfb");
		if (StringUtils.isBlank(sign) || !sign.equals(testSign)) {
			rs.put("errMsg", "签名校验失败.");
			return rs;
		}

		// 获取base64上传结果
		rs = FileUtils.uploadBase64(req);
		// if (rs.size() > 0) {
		// // 删除原有的
		// ImgResourcesExample example = new ImgResourcesExample();
		// example.createCriteria().andGidEqualTo(reqGid);
		// imgMapper.deleteByExample(example);
		// }

		Iterator<String> i = rs.keySet().iterator();
		ImgResources img = new ImgResources();
		img.setType(type);
		img.setGid(reqGid);
		// img.setAccId(user.getAccId());

		img.setStatus("normal");
		img.setCreateTime(new Timestamp(System.currentTimeMillis()));

		while (i.hasNext()) {
			String fieldName = i.next();
			if (!(rs.get(fieldName) instanceof JSONArray)) {
				img.setImg(rs.getString(fieldName));
			} else {
				JSONArray jsonArray = rs.getJSONArray(fieldName);
				img.setImg(jsonArray.toJSONString());
			}
			imgMapper.insertSelective(img);
		}
		JSONObject json = new JSONObject();
		json.put("data", rs);
		String callBack = req.getParameter("callback");
		if (StringUtils.isNotBlank(callBack)) {
			json.put("callback", callBack);
		}
		return json;
	}

	/**
	 * 二进制上传
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping("upload/binary")
	public JSONObject uploadBinary(HttpServletRequest req, HttpServletResponse res) {
		setCrossDomainParam(res);

		JSONObject rs = new JSONObject();
		String type = req.getParameter("type");
		String reqGid = req.getParameter("gid");
		if (StringUtils.isBlank(type) || StringUtils.isBlank(reqGid) || !StringUtils.isNumeric(reqGid)) {
			rs.put("errMsg", "请求参数type不能为空,pid必须为数字.");
			return rs;
		}
		String sign = req.getParameter("sign");
		String testSign = DigestUtils.md5Hex(reqGid + "hncxyfb");
		if (StringUtils.isBlank(sign) || !sign.equals(testSign)) {
			rs.put("errMsg", "签名校验失败.");
			return rs;
		}
		// 删除原有的
		ImgResourcesExample example = new ImgResourcesExample();
		example.createCriteria().andGidEqualTo(reqGid);
		imgMapper.deleteByExample(example);

		rs = FileUtils.uploadBinary(req);
		Iterator<String> i = rs.keySet().iterator();
		ImgResources img = new ImgResources();
		img.setType(type);
		img.setGid(reqGid);
		img.setStatus("normal");
		img.setCreateTime(new Timestamp(System.currentTimeMillis()));

		while (i.hasNext()) {
			String fieldName = i.next();
			if (rs.getJSONArray(fieldName) == null) {
				img.setImg(rs.getString(fieldName));
			} else {
				JSONArray jsonArray = rs.getJSONArray(fieldName);
				img.setImg(jsonArray.toJSONString());
			}
			imgMapper.insertSelective(img);
		}
		return rs;
	}

	@ResponseBody
	@RequestMapping("delete")
	public JSONObject delete(HttpServletRequest req, HttpServletResponse res) {
		setCrossDomainParam(res);

		JSONObject rs = new JSONObject();
		String type = req.getParameter("type");
		String reqGid = req.getParameter("gid");
		String idStr = req.getParameter("id");
		String sign = req.getParameter("sign");
		String testSign = DigestUtils.md5Hex(reqGid + "hncxyfb");
		if (StringUtils.isBlank(sign) || !sign.equals(testSign)) {
			rs.put("errMsg", "签名校验失败.");
			return rs;
		}

		Integer id = -1;
		int count = 0;
		if (StringUtils.isNotBlank(idStr) && StringUtils.isNumeric(idStr)) {
			id = Integer.parseInt(idStr);
		}

		if (id > 0) {
			count += imgMapper.deleteByPrimaryKey(id);
		} else {
			ImgResourcesExample example = new ImgResourcesExample();
			Criteria c = example.createCriteria();
			if (StringUtils.isNotBlank(type)) {
				c.andTypeEqualTo(type);
			}
			if (StringUtils.isNotBlank(reqGid)) {
				c.andGidEqualTo(reqGid);
			}
			count += imgMapper.deleteByExample(example);
		}
		rs.put("delete_count", count);
		rs.put("success", true);
		return rs;
	}

	private void setCrossDomainParam(HttpServletResponse res) {
		/************** 设置跨域参数 start，摘抄自网上 *************/
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600"); // 设置过期时间
		res.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization");
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1.
		res.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0. response.setHeader("Expires", "0");
	}
}
