package com.itany.netClass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.dto.CourseDto;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseNameIsExistsException;
import com.itany.netClass.vo.CourseQuery;
import org.apache.commons.fileupload.FileUploadException;

import java.util.List;

/**
 * Date: 2019年06月25日 下午3:06
 */
public interface CourseService {
    /**
     * 分页条件查询
     * @param page
     * @param pageSize
     * @param courseQuery
     * @return
     */
    PageInfo<Course> findByCondition(int page, int pageSize, CourseQuery courseQuery);

    /**
     * 搜索所有类型
     * @return
     */
    List<CourseType> serachType();

    /**
     * 更新课程的状态
     * @param course
     */
	void updateStatus(Course course);

    /**
     * 添加课程
     * @param courseDto
     * @throws CourseNameIsExistsException 
     */
    void add(CourseDto courseDto) throws FileUploadException, CourseNameIsExistsException;

    /**
     * 查询单个课程信息
     * @param course
     */
    Course singleCourse(Course course);

    /**
     * 跟新课程信息
     * @param courseDto
     * @throws CourseNameIsExistsException 
     */
    void update(CourseDto courseDto) throws FileUploadException, CourseNameIsExistsException;

    /**
     * 根据类型查询
     * @param typeId
     * @return
     */
	List<Course> findCourseByTypeId(int typeId);

	/**
	 * 分页查询课程
	 * @param page
	 * @param pageSize
	 * @return
	 */
	PageInfo<Course> findCourse(int page, int pageSize);

	/**
	 * 更新点击量
	 * @param id
	 */
	void updateClickNum(int id);

	/**
	 * 查询前四的课程
	 * @param courseTypeId
	 * @return
	 */
	List<Course> findCourseByCourseTypeId(int courseTypeId);
	
}
