package com.itany.netClass.service.proxy;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.DataAccessException;
import com.itany.netClass.exception.ServiceException;
import com.itany.netClass.exception.TypedNameExistException;
import com.itany.netClass.exception.TypedParentIdExistException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeService;
import com.itany.netClass.transaction.TransactionManager;

public class CourseTypeServiceProxy implements CourseTypeService {

    private CourseTypeService courseTypeService = ObjectFactory.getObject("courseTypeServiceTarget");
    private TransactionManager trans = ObjectFactory.getObject("transaction");

    @Override
    public void addCourseType(CourseType courseType)throws TypedNameExistException, TypedParentIdExistException {
        try {
            trans.beginTransaction();
            //调用serviceImpl的方法
            courseTypeService.addCourseType(courseType);
            trans.commit();
        } catch (DataAccessException e) {
            e.printStackTrace();
            trans.rollback();
            throw new ServiceException("服务器繁忙!");
        }catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        }
    }

    @Override
    public PageInfo<CourseType> findChildrenByParentId(Integer pageNo, Integer parentId) {
        PageInfo<CourseType> pageInfo;
        try {
            trans.beginTransaction();
            //调用serviceImpl的方法
            pageInfo = courseTypeService.findChildrenByParentId(pageNo, parentId);
            trans.commit();
        } catch (DataAccessException e) {
            e.printStackTrace();
            trans.rollback();
            throw new ServiceException("服务器繁忙!");
        }
        return pageInfo;
    }

    @Override
    public void updateStatus(CourseType type) {
        try {
            trans.beginTransaction();
            //调用serviceImpl的方法
            courseTypeService.updateStatus(type);
            trans.commit();
        } catch (DataAccessException e) {
            e.printStackTrace();
            trans.rollback();
            throw new ServiceException("服务器繁忙!");
        }

    }

	@Override
	public List<CourseType> findMenu() {
		List<CourseType> list;
		  try {
	            trans.beginTransaction();
	            //调用serviceImpl的方法
	            list=courseTypeService.findMenu();
	            trans.commit();
	        } catch (DataAccessException e) {
	            e.printStackTrace();
	            trans.rollback();
	            throw new ServiceException("服务器繁忙!");
	        }
		return list;
	}

	@Override
	public List<CourseType> findAllType(Integer parentId) {
		List<CourseType> list;
		  try {
	            trans.beginTransaction();
	            //调用serviceImpl的方法
	            list=courseTypeService.findAllType(parentId);
	            trans.commit();
	        } catch (DataAccessException e) {
	            e.printStackTrace();
	            trans.rollback();
	            throw new ServiceException("服务器繁忙!");
	        }
		return list;
	}
}
