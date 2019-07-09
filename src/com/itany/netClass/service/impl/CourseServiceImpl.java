package com.itany.netClass.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.CourseMapper;
import com.itany.netClass.dao.CourseTypeMapper;
import com.itany.netClass.dto.CourseDto;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseExample;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CheckDataFormatException;
import com.itany.netClass.exception.CourseNameIsExistsException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseService;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.StringUtil;
import com.itany.netClass.vo.CourseQuery;

public class CourseServiceImpl implements CourseService {

	private CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
	private CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");

	@Override
	public PageInfo<Course> findByCondition(int page, int pageSize, CourseQuery courseQuery) {
		PageHelper.startPage(page, pageSize);
		List<Course> list = courseMapper.selectByCondition(courseQuery);
		PageInfo<Course> pageInfo = new PageInfo<Course>(list);
		return pageInfo;

	}

	@Override
	public List<CourseType> serachType() {
		List<CourseType> courseTypes = courseTypeMapper.selectAll();
		return courseTypes;
	}

	@Override
	public void updateStatus(Course course) {
		courseMapper.updateByPrimaryKeySelective(course);
	}

	@Override
	public void add(CourseDto courseDto) throws FileUploadException, CourseNameIsExistsException {
		if (!checkData(courseDto)) {
			throw new CheckDataFormatException("数据校验失败");
		}
		CourseExample example = new CourseExample();
		example.or().andCourseNameEqualTo(courseDto.getCourseName());
		List<Course> list = courseMapper.selectByExample(example);
		if (!list.isEmpty()) {
			throw new CourseNameIsExistsException("课程名已存在");
		}
		// 1.文件上传，将文件上传保存到Web服务器的目录下
		String fileName = StringUtil.renameFileName(courseDto.getFileName());
		String filePath = courseDto.getUploadPath() + "/" + fileName;
		try {
			FileUtils.copyInputStreamToFile(courseDto.getInputStream(), new File(filePath));
		} catch (IOException e) {
			throw new FileUploadException("文件上传失败：" + e.getMessage());
		}

		// 2.保存到数据库，将DTO转换为PO
		Course course = new Course();
		course.setCourseName(courseDto.getCourseName());
		course.setCourseInfo(courseDto.getCourseInfo());
		course.setAuthor(courseDto.getAuthor());
		course.setCreateDate(new Date());
		course.setClickNumber(0);
		course.setStatus(Constant.STATUS_DISABLE);
		course.setRecommendationGrade(courseDto.getRecommendationGrade());
		course.setCourseTypeId(courseDto.getCourseTypeId());
		course.setCoverImageUrl((courseDto.getCoverImageUrl() + "/" + fileName).replace("\\", "/"));
		courseMapper.insert(course);
	}

	private boolean checkData(CourseDto courseDto) {
		if (!CommonUtil.isEmpty(courseDto.getCourseName())) {
			if (courseDto.getCourseName().length() < 1 || courseDto.getCourseName().length() > 18) {
				return false;
			}
		}
		if (!CommonUtil.isEmpty(courseDto.getCourseInfo())) {
			if (courseDto.getCourseInfo().length() < 1 || courseDto.getCourseInfo().length() > 50) {
				return false;
			}
		}

		if (!CommonUtil.isEmpty(courseDto.getAuthor())) {
			if (courseDto.getAuthor().length() < 1 || courseDto.getAuthor().length() > 18) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Course singleCourse(Course course) {
		return courseMapper.selectByPrimaryKey(course.getId());
	}

	@Override
	public void update(CourseDto courseDto) throws FileUploadException, CourseNameIsExistsException {
		 if (!checkData(courseDto)) {
	            throw new CheckDataFormatException("数据校验失败");
	        }
	        CourseExample example = new CourseExample();
	        example.or().andCourseNameEqualTo(courseDto.getCourseName());
	        List<Course> list = courseMapper.selectByExample(example);
	        if (!list.isEmpty()&&!list.get(0).getId().equals(courseDto.getId())) {
	            throw new CourseNameIsExistsException("课程名已存在");
	        }
	        Course course = new Course();
	        // 1.文件上传，将文件上传保存到Web服务器的目录下
	        if (courseDto.getInputStream() != null) {
	            String fileName = StringUtil.renameFileName(courseDto.getFileName());
	            String filePath = courseDto.getUploadPath() + "/" + fileName;
	            try {
	                FileUtils.copyInputStreamToFile(courseDto.getInputStream(), new File(filePath));
	                course.setCoverImageUrl((courseDto.getCoverImageUrl() + "/" + fileName).replace("\\", "/"));
	            } catch (IOException e) {
	                throw new FileUploadException("文件上传失败：" + e.getMessage());
	            }
	        }

	        // 2.保存到数据库，将DTO转换为PO
	        course.setCourseName(courseDto.getCourseName());
	        course.setId(courseDto.getId());
	        course.setCourseInfo(courseDto.getCourseInfo());
	        course.setAuthor(courseDto.getAuthor());
	        course.setRecommendationGrade(courseDto.getRecommendationGrade());
	        course.setCourseTypeId(courseDto.getCourseTypeId());

	        courseMapper.updateByPrimaryKeySelective(course);
	}

	@Override
	public List<Course> findCourseByTypeId(int typeId) {
		CourseExample example = new CourseExample();
		example.or().andStatusEqualTo(Constant.STATUS_ENABLE)
					.andCourseTypeIdEqualTo(typeId);
		example.setOrderByClause("click_number DESC");
		PageHelper.startPage(1, 4);
		List<Course> list = courseMapper.selectByExample(example);
		return list;
	}

	@Override
	public PageInfo<Course> findCourse(int page, int pageSize) {
		CourseExample example = new CourseExample();
		example.or().andStatusEqualTo(Constant.STATUS_ENABLE);
		example.setOrderByClause("recommendation_grade DESC,click_number DESC");
		PageHelper.startPage(page, pageSize);
		List<Course> list = courseMapper.selectByExample(example);
		PageInfo<Course> pageInfo = new PageInfo<Course>(list);
//		System.out.println("-------------=============");
		for (Course course : pageInfo.getList()) {
			CourseType courseType = courseTypeMapper.selectById(course.getCourseTypeId());
			course.setCourseType((CourseType) courseType.clone());
		}
		return pageInfo;
	}

	@Override
	public void updateClickNum(int id) {
		Course course = courseMapper.selectByPrimaryKey(id);
		if(course!=null){
			course.setClickNumber(course.getClickNumber()+1);
			courseMapper.updateByPrimaryKey(course);
		}
		
	}

	@Override
	public List<Course> findCourseByCourseTypeId(int courseTypeId) {
		CourseExample example = new CourseExample();
		example.or().andStatusEqualTo(Constant.STATUS_ENABLE)
					.andCourseTypeIdEqualTo(courseTypeId);
		example.setOrderByClause("click_number DESC");
		PageHelper.startPage(1, 3);
		List<Course> list = courseMapper.selectByExample(example);
		return list;
	}

}
