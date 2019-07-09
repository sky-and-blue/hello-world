package com.itany.netClass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.DataAccessException;
import com.itany.netClass.exception.PointCountIsLessException;
import com.itany.netClass.exception.ServiceException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.service.GoldPointsService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.Map;

public class GoldPointsServiceProxy implements GoldPointsService {

	private TransactionManager trans = ObjectFactory.getObject("transaction");
	private GoldPointsService goldPointsServiceImpl = ObjectFactory.getObject("goldPointsServiceImpl");

	@Override
	public Map<String,Integer> findByUserId(int userId) {
		Map<String,Integer> goldPoints;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			goldPoints = goldPointsServiceImpl.findByUserId(userId);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return goldPoints;
	}

	@Override
	public PageInfo<GoldPoints> findgoldPoints(int page, int pageSize, int userId) {
		PageInfo<GoldPoints> pageInfo;
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			pageInfo = goldPointsServiceImpl.findgoldPoints(page,pageSize,userId);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}

		return pageInfo;
	}

	@Override
	public void changeMoney(int changeNum, int userId) throws PointCountIsLessException {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			goldPointsServiceImpl.changeMoney(changeNum,userId);
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

//	@Override
//	public User login(User user) {
//		try {
//			trans.beginTransaction();
//			//调用serviceImpl的方法
//			User u = adminServiceImpl.login(user);
//			trans.commit();
//			return u;
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			trans.rollback();
//			throw new ServiceException("服务器繁忙!");
//		}
//	}
}
