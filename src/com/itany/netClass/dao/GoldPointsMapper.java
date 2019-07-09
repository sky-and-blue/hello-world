package com.itany.netClass.dao;

import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.entity.GoldPointsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoldPointsMapper {
    int countByExample(GoldPointsExample example);

    int deleteByExample(GoldPointsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoldPoints record);

    int insertSelective(GoldPoints record);

    List<GoldPoints> selectByExample(GoldPointsExample example);

    GoldPoints selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoldPoints record, @Param("example") GoldPointsExample example);

    int updateByExample(@Param("record") GoldPoints record, @Param("example") GoldPointsExample example);

    int updateByPrimaryKeySelective(GoldPoints record);

    int updateByPrimaryKey(GoldPoints record);
}