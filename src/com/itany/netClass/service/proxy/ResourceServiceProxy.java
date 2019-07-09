package com.itany.netClass.service.proxy;

import com.itany.netClass.exception.ResourceNameIsExistsException;
import org.apache.commons.fileupload.FileUploadException;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.dto.ResourceDto;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.exception.DataAccessException;
import com.itany.netClass.exception.ServiceException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ResourceService;
import com.itany.netClass.transaction.TransactionManager;
import com.itany.netClass.vo.ResourceQuery;

public class ResourceServiceProxy implements ResourceService {

	private TransactionManager trans = ObjectFactory.getObject("transaction");
	private ResourceService resourceServiceImpl = ObjectFactory.getObject("resourceServiceImpl");


	@Override
	public PageInfo<Resource> findByConldition(int page, int pageSize, ResourceQuery resourceQuery) {
		PageInfo<Resource> pageinfo;
		try {
			trans.beginTransaction();
			pageinfo= resourceServiceImpl.findByConldition(page,pageSize,resourceQuery);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return pageinfo;
	}

	@Override
	public void updateStatus(Resource resource) {
		try {
			trans.beginTransaction();
			resourceServiceImpl.updateStatus(resource);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public void add(ResourceDto resourceDto) throws FileUploadException, ResourceNameIsExistsException {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			resourceServiceImpl.add(resourceDto);
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
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			resource= resourceServiceImpl.singleResource(resource);
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
		return resource;
	}

	@Override
	public void update(ResourceDto resourceDto)throws ResourceNameIsExistsException, FileUploadException{
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			resourceServiceImpl.update(resourceDto);
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
	public void delete(Resource resource) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			resourceServiceImpl.delete(resource);
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


}
