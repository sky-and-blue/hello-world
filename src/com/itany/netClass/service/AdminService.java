package com.itany.netClass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.User;
import com.itany.netClass.vo.UserQuery;

/**
 * 管理员Service接口
 */
public interface AdminService {

	/**
	 * 管理员登录
	 * @param user
	 * @return
	 */
	User login(User user);

}
