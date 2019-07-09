package com.itany.netClass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.entity.User;
import com.itany.netClass.entity.UserResource;
import com.itany.netClass.exception.GoldCountIsLessException;
import com.itany.netClass.exception.IsBuyResourceException;
import com.itany.netClass.exception.PointCountIsLessException;
import com.itany.netClass.vo.UserQuery;

public interface UserService {
	
	/**
	 * 添加用户
	 * @param user
	 */
	void add(User user);
	
	/**
	 * 分页条件查询用户
	 * @param pageNo
	 * @param pageSize
	 * @param query
	 * @return
	 */
	PageInfo<User> findByCondition(int pageNo, int pageSize, UserQuery query);

	User login(User user);

	/**
	 * 签到
	 * @param userId
	 * @exception RuntimeException 签到失败时抛出此异常
	 */
    void sign(Integer userId) throws RuntimeException;

	/**
	 * 修改用户信息
	 * @param user
	 * @param newPassword
	 * @return
	 */
	User update(User user, String newPassword);

	/**
	 * 修改用户状态
	 * @param user
	 */
	void updateStatus(User user);

	/**
	 * 修改单个用户信息
	 * @param id
	 * @return
	 */
    User singleById(int id);

	/**
	 * 修改用户
	 * @param user
	 */
	void update(User user);

	/**
	 * 根据用户id查询资源
	 * @param userId
	 * @param resourceId
	 * @return
	 */
	UserResource findResourceByUserId(int userId, int resourceId);

	/**
	 * 用户购买资源
	 * @param userId
	 * @param resourceId
	 * @return
	 * @throws IsBuyResourceException 
	 * @throws PointCountIsLessException 
	 * @throws GoldCountIsLessException 
	 */
	void addResource(int userId, int resourceId) throws IsBuyResourceException, PointCountIsLessException, GoldCountIsLessException;
}
