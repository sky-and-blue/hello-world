package com.itany.netClass.service.impl;

import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.UserMapper;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.util.CommonUtil;

import java.util.List;

public class AdminServiceImpl implements AdminService {

	private UserMapper userMapper = ObjectFactory.getObject("userMapper");

	@Override
	public User login(User user) {
		List<User> list = userMapper.selectByLoginName(user.getLoginName());

		if(list.isEmpty()){
			throw new RuntimeException("登录名错误");
		}
		User u = list.get(0);
		
		if(!u.getPassword().equals(CommonUtil.md5(user.getPassword()))){
			throw new RuntimeException("密码错误");
		}

		if(!u.getRole().equals(Constant.USER_ROLE_ADMIN)){
			throw new RuntimeException("没有登录权限");
		}
		return u;
	}

}


