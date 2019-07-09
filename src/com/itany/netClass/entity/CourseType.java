package com.itany.netClass.entity;

import java.io.Serializable;
import java.util.List;

public class CourseType implements Serializable, Cloneable {

	private Integer id;
	private String typeName;
	private Integer parentId;
	private Integer status;

	private List<CourseType> children;

	public CourseType() {
	}

	public CourseType(Integer id, String typeName, Integer parentId, Integer status) {
		this.id = id;
		this.typeName = typeName;
		this.parentId = parentId;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<CourseType> getChildren() {
		return children;
	}

	public void setChildren(List<CourseType> children) {
		this.children = children;
	}

	@Override
	public Object clone() {
		CourseType courseType = null;
		try {
			courseType = (CourseType) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return courseType;
	}
}
