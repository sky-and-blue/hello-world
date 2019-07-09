package com.itany.netClass.dao;

import com.itany.netClass.entity.CourseType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author:teacher@itany.com
 * Date:2018/11/8 14:58
 * Description:
 * version:1.0
 */
public interface CourseTypeMapper {

    /**
     * 添加类别
     * @param courseType
     */
    void insertCourseType(CourseType courseType);

    /**
     * 根据parentId查询子类别
     * @param parentId
     * @return
     */
    List<CourseType> selectChildrenByParentId(@Param("parentId") Integer parentId);

    /**
     *查询课程分类
     * @param id
     */
    CourseType selectById(@Param("id") Integer id );

    /**
     * 跟新课程分类
     * @param type
     */
    void update(CourseType type);

    /**
     * 查询所有分类
     * @return
     */
    List<CourseType> selectAll();

    /**
     * 根据parentId查询可用子类别
     * @param parentId
     * @return
     */
	List<CourseType> selectChildrenByParentIdAndStatus(@Param("parentId") Integer parentId);
}
