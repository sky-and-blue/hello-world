package com.itany.netClass.dao;

import com.itany.netClass.entity.Resource;
import com.itany.netClass.entity.ResourceExample;
import java.util.List;

import com.itany.netClass.vo.ResourceQuery;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper {
    int countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    Resource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    /**
     *条件查询资源
     * @param resourceQuery
     * @return
     */
    List<Resource> selectByCondition(ResourceQuery resourceQuery);
}