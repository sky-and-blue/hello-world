package com.itany.netClass.service.proxy;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.User;
import com.itany.netClass.entity.UserResource;
import com.itany.netClass.exception.DataAccessException;
import com.itany.netClass.exception.GoldCountIsLessException;
import com.itany.netClass.exception.IsBuyResourceException;
import com.itany.netClass.exception.PointCountIsLessException;
import com.itany.netClass.exception.ServiceException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserService;
import com.itany.netClass.transaction.TransactionManager;
import com.itany.netClass.vo.UserQuery;

public class UserServiceProxy implements UserService {

	private TransactionManager trans = ObjectFactory.getObject("transaction");
	private UserService userServiceTarget = ObjectFactory.getObject("userServiceTarget");

	@Override
	public void add(User user) {
		try {
			trans.beginTransaction();
			// 调用serviceImpl的方法
			userServiceTarget.add(user);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public User login(User user) {
		try {
			trans.beginTransaction();
			// 调用serviceImpl的方法
			User u = userServiceTarget.login(user);
			trans.commit();
			return u;
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public void sign(Integer id) throws RuntimeException {
		try {
			trans.beginTransaction();
			// 调用serviceImpl的方法
			userServiceTarget.sign(id);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public User update(User user, String newPassword) {
		try {
			trans.beginTransaction();
			// 调用serviceImpl的方法
			User u = userServiceTarget.update(user, newPassword);
			trans.commit();
			return u;
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public PageInfo<User> findByCondition(int pageNo, int pageSize, UserQuery query) {
		PageInfo<User> pageInfo = null;
		try {
			trans.beginTransaction();
			// 调用serviceImpl的方法,接收返回值,并返回
			pageInfo = userServiceTarget.findByCondition(pageNo, pageSize, query);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return pageInfo;
	}

	@Override
	public void updateStatus(User user) {
		try {
			trans.beginTransaction();
			userServiceTarget.updateStatus(user);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public User singleById(int id) {
		User user = null;
		try {
			trans.beginTransaction();
			// 调用serviceImpl的方法,接收返回值,并返回
			user = userServiceTarget.singleById(id);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return user;
	}

	@Override
	public void update(User user) {
		try {
			trans.beginTransaction();
			userServiceTarget.update(user);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public UserResource findResourceByUserId(int userId, int resourceId) {
		UserResource userResource = null;
		try {
			trans.beginTransaction();
			// 调用serviceImpl的方法,接收返回值,并返回
			userResource = userServiceTarget.findResourceByUserId(userId, resourceId);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return userResource;
	}

	@Override
	public void addResource(int userId, int resourceId) throws IsBuyResourceException, PointCountIsLessException, GoldCountIsLessException {
		try {
			trans.beginTransaction();
			// 调用serviceImpl的方法,接收返回值,并返回
			userServiceTarget.addResource(userId, resourceId);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

}
