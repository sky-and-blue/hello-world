package com.itany.netClass.vo;

import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.Resource;

import java.util.Date;

public class ResourceQuery extends Resource {


	private String titleLike;
	private String nickNameLike;
	private Date createDateBegin;
	private Date createDateEnd;


	public String getTitleLike() {
		return titleLike;
	}

	public void setTitleLike(String titleLike) {
		this.titleLike = titleLike;
	}

	public String getNickNameLike() {
		return nickNameLike;
	}

	public void setNickNameLike(String nickNameLike) {
		this.nickNameLike = nickNameLike;
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
