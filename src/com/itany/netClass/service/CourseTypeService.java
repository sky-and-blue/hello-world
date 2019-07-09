package com.itany.netClass.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.TypedNameExistException;
import com.itany.netClass.exception.TypedParentIdExistException;

public interface CourseTypeService {

    void addCourseType(CourseType courseType) throws TypedNameExistException, TypedParentIdExistException;

    PageInfo<CourseType> findChildrenByParentId(Integer pageNo, Integer parentId);

    void updateStatus(CourseType type);

    /**
     * 查询三级级菜单
     */
    List<CourseType> findMenu();

    /**
     * 查询所有的父分类
     * @return
     */
	List<CourseType> findAllType(Integer parentId);
}
