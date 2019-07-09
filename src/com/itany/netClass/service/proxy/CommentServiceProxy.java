package com.itany.netClass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.dto.ChapterDto;
import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.exception.DataAccessException;
import com.itany.netClass.exception.PraiseIsExistException;
import com.itany.netClass.exception.ServiceException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.transaction.TransactionManager;
import com.itany.netClass.vo.ChapterQuery;
import com.itany.netClass.vo.CommentQuery;

import java.util.List;

import org.apache.commons.fileupload.FileUploadException;

public class CommentServiceProxy implements CommentService {

	private TransactionManager trans = ObjectFactory.getObject("transaction");
	private CommentService commentServiceImpl = ObjectFactory.getObject("commentServiceImpl");


	@Override
	public PageInfo<Comment> findByConldition(int page, int pageSize, CommentQuery commentQuery) {
		PageInfo<Comment> pageinfo;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			pageinfo= commentServiceImpl.findByConldition(page,pageSize,commentQuery);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return pageinfo;
	}

	@Override
	public void updateStatus(Comment comment) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			commentServiceImpl.updateStatus(comment);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public PageInfo<Comment> findCommentByResourceIds(int page, int pageSize, String cids) {
		PageInfo<Comment> list;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			list=commentServiceImpl.findCommentByResourceIds(page,pageSize,cids);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return list;
	}
	@Override
	public void addZan(int commentId, int userId)throws PraiseIsExistException {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			commentServiceImpl.addZan(commentId,userId);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}catch (PraiseIsExistException e) {
			e.printStackTrace();
			trans.commit();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

	@Override
	public Comment addComment(Comment comment) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			comment=commentServiceImpl.addComment(comment);
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
		return comment;
	}

	




}
