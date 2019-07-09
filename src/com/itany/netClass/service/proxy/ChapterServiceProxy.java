package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.Resource;
import org.apache.commons.fileupload.FileUploadException;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.dto.ChapterDto;
import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Course;
import com.itany.netClass.exception.ChapterTitleException;
import com.itany.netClass.exception.DataAccessException;
import com.itany.netClass.exception.ResourceTitleException;
import com.itany.netClass.exception.ServiceException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.transaction.TransactionManager;
import com.itany.netClass.vo.ChapterQuery;

import java.util.List;

public class ChapterServiceProxy implements ChapterService {

	private TransactionManager trans = ObjectFactory.getObject("transaction");
	private ChapterService chapterServiceImpl = ObjectFactory.getObject("chapterServiceImpl");


	@Override
	public PageInfo<Chapter> findByConldition(int page, int pageSize, ChapterQuery chapterQuery) {
		PageInfo<Chapter> pageinfo;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			pageinfo= chapterServiceImpl.findByConldition(page,pageSize,chapterQuery);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return pageinfo;
	}

	@Override
	public void updateStatus(Chapter chapter) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			chapterServiceImpl.updateStatus(chapter);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public void add(ChapterDto chapterDto)throws FileUploadException, ChapterTitleException, ResourceTitleException {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			chapterServiceImpl.add(chapterDto);
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
	public Chapter singleChapter(Chapter chapter) {
		Chapter c;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			 c= chapterServiceImpl.singleChapter(chapter);
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
	public void update(ChapterDto chapterDto) throws FileUploadException, ResourceTitleException, ChapterTitleException {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			chapterServiceImpl.update(chapterDto);
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
	public Resource singleResource(Resource resource) {
		Resource c;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			c= chapterServiceImpl.singleResource(resource);
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
	public List<Chapter> findChapterByCourseId(int courseId) {
		List<Chapter> list;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			list= chapterServiceImpl.findChapterByCourseId(courseId);
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
}
