package com.itany.netClass.dao;

import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseExample;
import java.util.List;

import com.itany.netClass.entity.User;
import com.itany.netClass.vo.CourseQuery;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper {
    int countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    /**
     *
     * @param courseQuery
     * @return
     */
    List<Course> selectByCondition(CourseQuery courseQuery);
}