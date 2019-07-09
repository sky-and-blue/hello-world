package com.itany.netClass.vo;

import java.util.Date;

import com.itany.netClass.entity.Course;

public class CourseQuery extends Course {


	private String authorLike;
	private String courseNameLike;
	private Date createDateBegin;
	private Date createDateEnd;


	public String getAuthorLike() {
		return authorLike;
	}

	public void setAuthorLike(String authorLike) {
		this.authorLike = authorLike;
	}

	public String getCourseNameLike() {
		return courseNameLike;
	}

	public void setCourseNameLike(String courseNameLike) {
		this.courseNameLike = courseNameLike;
	}

	public Date getCreateDateBegin() {
		return createDateBegin;
	}

	public void setCreateDateBegin(Date createDateBegin) {
		this.createDateBegin = createDateBegin;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
}
