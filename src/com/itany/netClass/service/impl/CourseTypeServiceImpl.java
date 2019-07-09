package com.itany.netClass.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.CourseTypeMapper;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CheckDataFormatException;
import com.itany.netClass.exception.TypedNameExistException;
import com.itany.netClass.exception.TypedParentIdExistException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeService;

public class CourseTypeServiceImpl implements CourseTypeService {

    private CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");

    @Override
    public void addCourseType(CourseType courseType) throws TypedNameExistException, TypedParentIdExistException {
    	   // 判断名称是否相同,以及parentId等属性的校验
        if(courseType.getTypeName().length()<18){
            throw new CheckDataFormatException("数据校验错误");
        }

        if (courseType.getParentId() != null) {
            CourseType c1 = courseTypeMapper.selectById(courseType.getParentId());
            if (c1 == null) {
                throw new TypedParentIdExistException("父分类不存在");
            }
        }
        List<CourseType> courseTypes = courseTypeMapper.selectChildrenByParentId(courseType.getParentId());
        for (CourseType c : courseTypes) {
            if (c.getTypeName().equals(courseType.getTypeName())) {
                throw new TypedNameExistException("分类已存在");
            }
        }
        courseType.setStatus(Constant.STATUS_ENABLE);
        courseTypeMapper.insertCourseType(courseType);
    }

    @Override
    public PageInfo<CourseType> findChildrenByParentId(Integer pageNo, Integer parentId) {

        PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
        List<CourseType> children = courseTypeMapper.selectChildrenByParentId(parentId);
        PageInfo<CourseType> pageInfo = new PageInfo<CourseType>(children);

        return pageInfo;
    }

    @Override
    public void updateStatus(CourseType type) {
        courseTypeMapper.update(type);
    }

	@Override
	public List<CourseType> findMenu() {
		List<CourseType> listOne = courseTypeMapper.selectChildrenByParentIdAndStatus(null);
		for (CourseType courseType : listOne) {
			List<CourseType> listTwo = courseTypeMapper.selectChildrenByParentIdAndStatus(courseType.getId());
			for (CourseType courseType1 : listTwo) {
				List<CourseType> listThree = courseTypeMapper.selectChildrenByParentIdAndStatus(courseType1.getId());
				courseType1.setChildren(listThree);
			}
			courseType.setChildren(listTwo);
		}
		return listOne;
	}

	@Override
	public List<CourseType> findAllType(Integer parentId) {
		return courseTypeMapper.selectChildrenByParentIdAndStatus(parentId);
	}
}
