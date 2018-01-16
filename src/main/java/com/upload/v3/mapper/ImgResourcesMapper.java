package com.upload.v3.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.upload.v3.entity.ImgResources;
import com.upload.v3.entity.ImgResourcesExample;

public interface ImgResourcesMapper {
    long countByExample(ImgResourcesExample example);

    int deleteByExample(ImgResourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImgResources record);

    int insertSelective(ImgResources record);

    List<ImgResources> selectByExample(ImgResourcesExample example);

    ImgResources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ImgResources record, @Param("example") ImgResourcesExample example);

    int updateByExample(@Param("record") ImgResources record, @Param("example") ImgResourcesExample example);

    int updateByPrimaryKeySelective(ImgResources record);

    int updateByPrimaryKey(ImgResources record);
}