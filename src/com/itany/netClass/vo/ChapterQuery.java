package com.itany.netClass.vo;

import java.util.Date;

import com.itany.netClass.entity.Chapter;
public class ChapterQuery extends Chapter {

	public ChapterQuery() {
		super();
	}

	private String titleLike;
	private String infoLike;
	private Date createDateBegin;
	private Date createDateEnd;


	
	public String getTitleLike() {
		return titleLike;
	}

	public void setTitleLike(String titleLike) {
		this.titleLike = titleLike;
	}

	public String getInfoLike() {
		return infoLike;
	}

	public void setInfoLike(String infoLike) {
		this.infoLike = infoLike;
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
