package com.itany.netClass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.dto.CourseDto;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.CourseNameIsExistsException;
import com.itany.netClass.exception.DataAccessException;
import com.itany.netClass.exception.ServiceException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.service.CourseService;
import com.itany.netClass.transaction.TransactionManager;
import com.itany.netClass.vo.CourseQuery;
import com.itany.netClass.vo.UserQuery;
import org.apache.commons.fileupload.FileUploadException;

import java.util.List;

public class CourseServiceProxy implements CourseService {

	private TransactionManager trans = ObjectFactory.getObject("transaction");
	private CourseService courseServiceImpl = ObjectFactory.getObject("courseServiceImpl");

	@Override
	public PageInfo<Course> findByCondition(int page, int pageSize, CourseQuery courseQuery) {
		PageInfo<Course> pageinfo;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			pageinfo= courseServiceImpl.findByCondition(page,pageSize,courseQuery);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return pageinfo;
	}

	@Override
	public List<CourseType> serachType() {
		List<CourseType> courseTypes;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			courseTypes= courseServiceImpl.serachType();
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return courseTypes;
	}

	@Override
	public void updateStatus(Course course) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			courseServiceImpl.updateStatus(course);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public void add(CourseDto courseDto)throws FileUploadException, CourseNameIsExistsException {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			courseServiceImpl.add(courseDto);
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
	public Course singleCourse(Course course) {
		Course c;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			 c= courseServiceImpl.singleCourse(course);
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

		return c;
	}

	@Override
	public void update(CourseDto courseDto) throws FileUploadException, CourseNameIsExistsException {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			courseServiceImpl.update(courseDto);
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
	public List<Course> findCourseByTypeId(int typeId) {
		List<Course> list;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			list=courseServiceImpl.findCourseByTypeId(typeId);
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
		return list;
	}

	@Override
	public PageInfo<Course> findCourse(int page, int pageSize) {
		PageInfo<Course> pageinfo;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			pageinfo= courseServiceImpl.findCourse(page,pageSize);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return pageinfo;
	}

	@Override
	public void updateClickNum(int id) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			courseServiceImpl.updateClickNum(id);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public List<Course> findCourseByCourseTypeId(int courseTypeId) {
		List<Course> list;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			list= courseServiceImpl.findCourseByCourseTypeId(courseTypeId);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return list;
	}


}
