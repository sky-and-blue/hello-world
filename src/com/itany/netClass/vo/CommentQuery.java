package com.itany.netClass.vo;

import com.itany.netClass.entity.Comment;

import java.util.Date;

public class CommentQuery extends Comment {


	private String contextLike;
	private String nickNameLike;
	private Date createDateBegin;
	private Date createDateEnd;


	public String getContextLike() {
		return contextLike;
	}

	public void setContextLike(String contextLike) {
		this.contextLike = contextLike;
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
