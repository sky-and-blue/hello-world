package com.itany.netClass.dao;

import java.util.List;

import com.itany.netClass.entity.GoldPoints;
import org.apache.ibatis.annotations.Param;

import com.itany.netClass.entity.User;
import com.itany.netClass.vo.UserQuery;

/**
 * t_user的操作
 */
public interface UserMapper {

	void insert(User user);
	
	void updateById(User user);

	List<User> selectByLoginName(String loginName);
	
	List<User> selectByCondition(UserQuery query);
	
	void insertReturnId(User user);
	
	Integer getUserCount(@Param("nickname") String nickname);

	/**
	 * 添加记录
	 * @param gp
	 */
    void insertGoldPoints(GoldPoints gp);

	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 */
	User findById(int id);

	/**
	 * 根据邮箱查询用户信息
	 * @param trim
	 * @return
	 */
	List<User> selectByEmail(String email);
}
