package com.itany.netClass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.DataAccessException;
import com.itany.netClass.exception.ServiceException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.service.UserService;
import com.itany.netClass.transaction.TransactionManager;
import com.itany.netClass.vo.UserQuery;

public class AdminServiceProxy implements AdminService {

	private TransactionManager trans = ObjectFactory.getObject("transaction");
	private AdminService adminServiceImpl = ObjectFactory.getObject("adminServiceImpl");
	
	@Override
	public User login(User user) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			User u = adminServiceImpl.login(user);
			trans.commit();
			return u;
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}
}
