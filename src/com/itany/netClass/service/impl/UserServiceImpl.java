package com.itany.netClass.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.GoldPointsMapper;
import com.itany.netClass.dao.ResourceMapper;
import com.itany.netClass.dao.UserMapper;
import com.itany.netClass.dao.UserResourceMapper;
import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.entity.GoldPointsExample;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.entity.User;
import com.itany.netClass.entity.UserResource;
import com.itany.netClass.entity.UserResourceExample;
import com.itany.netClass.exception.CheckDataFormatException;
import com.itany.netClass.exception.GoldCountIsLessException;
import com.itany.netClass.exception.IsBuyResourceException;
import com.itany.netClass.exception.PointCountIsLessException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserService;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.vo.UserQuery;

/**
 * 用户serviceImpl
 */
public class UserServiceImpl implements UserService {

	private UserMapper userMapper = ObjectFactory.getObject("userMapper");
	private UserResourceMapper userResourceMapper = ObjectFactory.getObject("userResourceMapper");
	private GoldPointsMapper goldPointsMapper = ObjectFactory.getObject("goldPointsMapper");
	private ResourceMapper resourceMapper = ObjectFactory.getObject("resourceMapper");

	@Override
	public void add(User user) {
		if (!checkData(user)) {
			throw new CheckDataFormatException("数据校验失败");
		}
		// 数据验证
		// loginName
		List<User> list = userMapper.selectByLoginName(user.getLoginName().trim());
		if (!list.isEmpty()) {// 存在,重复
			throw new RuntimeException("登录名重复");
		}
		List<User> list1 = userMapper.selectByEmail(user.getEmail().trim());
		if (!list1.isEmpty()) {// 存在,重复
			throw new RuntimeException("邮箱已注册");
		}
		user.setLoginName(user.getLoginName().trim());
		user.setRole(Constant.USER_ROLE_NORMAL);
		user.setCreateDate(new Date());
		user.setStatus(Constant.STATUS_ENABLE);
		user.setPassword(CommonUtil.md5(user.getPassword()));// 密码加密

		userMapper.insert(user);
	}

	@Override
	public User login(User user) {
		List<User> list = userMapper.selectByLoginName(user.getLoginName());
		if (list.isEmpty()) {
			throw new RuntimeException("登录名错误");
		}
		User u = list.get(0);
		if (!u.getPassword().equals(CommonUtil.md5(user.getPassword()))) {
			throw new RuntimeException("密码错误");
		}
		if (u.getStatus().equals(Constant.STATUS_DISABLE)) {
			throw new RuntimeException("没有登录权限");
		}
		User us = new User();
		us.setId(u.getId());
		us.setLoginDate(new Date());
		userMapper.updateById(us);
		return u;
	}

	@Override
	public void sign(Integer userId) throws RuntimeException {
		GoldPoints gp = new GoldPoints();

		UserQuery query = new UserQuery();
		query.setId(userId);
		List<User> users = userMapper.selectByCondition(query);
		if (users.isEmpty()) {
			throw new RuntimeException("当前登录用户不存在");
		}
		User user = users.get(0);

		Date loginDate = user.getLoginDate();// 上一次签到时间
		Date currentDate = new Date();// 当前时间
		// 从未签到过的用户 此属性值为null
		// 肯定没有签到
		// 直接进行签到操作
		if (null != loginDate) {
			// 有签到记录的用户 判断当天是否签到
			// 当前时间比loginDate大
			if (currentDate.getTime() <= loginDate.getTime()) {
				// throw new RuntimeException("您的上一次签到时间异常");
			}
			String currentDateStr = CommonUtil.dateToString(currentDate, "yyyy-MM-dd");
			String loginDateStr = CommonUtil.dateToString(loginDate, "yyyy-MM-dd");
			if (currentDateStr.equals(loginDateStr)) {// 年月日相同，表示当天已签到
				throw new RuntimeException("您当天已签到!");
			}
		}

		gp.setUserId(userId);
		gp.setPointCount(Constant.SIGN_POINT);
		gp.setGoldCount(0);
		gp.setInfo("签到获得积分");
		userMapper.insertGoldPoints(gp);
		// 更新用户的签到时间
		user.setLoginDate(currentDate);
		userMapper.updateById(user);

	}

	@Override
	public User update(User user, String newPassword) {
		if (!checkData(user)) {
			throw new CheckDataFormatException("数据校验失败");
		}
		if (newPassword.length() > 40) {
			throw new CheckDataFormatException("数据校验失败");
		}
		List<User> list = userMapper.selectByLoginName(user.getLoginName());
		if (!list.isEmpty() && !list.get(0).getId().equals(user.getId())) {// 存在,重复
			throw new RuntimeException("用户不存在");
		}
		User u = list.get(0);
		if (!CommonUtil.md5(user.getPassword()).equals(u.getPassword())) {
			throw new RuntimeException("旧密码错误");
		}
		if (u.getPassword().equals(CommonUtil.md5(newPassword))) {
			throw new RuntimeException("新密码与旧密码重复错误");
		}
		user.setPassword(CommonUtil.md5(newPassword));
		System.out.println(user);
		userMapper.updateById(user);
		return user;
	}

	@Override
	public PageInfo<User> findByCondition(int pageNo, int pageSize, UserQuery query) {
		PageHelper.startPage(pageNo, pageSize);
		List<User> list = userMapper.selectByCondition(query);
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		return pageInfo;
	}

	@Override
	public void updateStatus(User user) {
		userMapper.updateById(user);
	}

	@Override
	public User singleById(int id) {
		User user = userMapper.findById(id);
		user.setPassword(null);
		return user;
	}

	@Override
	public void update(User user) {
		if (!checkData(user)) {
			throw new CheckDataFormatException("数据校验失败");
		}
		if (user.getPassword() != null) {
			user.setPassword(CommonUtil.md5(user.getPassword()));
		}
		userMapper.updateById(user);
	}

	private boolean checkData(User user) {
		if (!CommonUtil.isEmpty(user.getLoginName())) {
			if (user.getLoginName().length() > 40) {
				return false;
			}
		}
		if (!CommonUtil.isEmpty(user.getNickname())) {
			if (user.getNickname().length() > 60) {
				return false;
			}
		}

		if (!CommonUtil.isEmpty(user.getPassword())) {
			if (user.getPassword().length() > 40) {
				return false;
			}
		}

		if (!CommonUtil.isEmpty(user.getRole())) {
			if ((!user.getRole().equals(Constant.USER_ROLE_ADMIN))
					&& (!user.getRole().equals(Constant.USER_ROLE_NORMAL))) {
				return false;
			}
		}
		if (!CommonUtil.isEmpty(user.getEmail())) {
			if (!user.getEmail().matches("^[_a-z0-9]+(\\.[_a-z0-9]+)*@[a-z0-9]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$")) {
				return false;
			}
		}
		return true;
	}

	@Override
	public UserResource findResourceByUserId(int userId, int resourceId) {
		UserResourceExample example = new UserResourceExample();
		example.or().andUserIdEqualTo(userId).andResourceIdEqualTo(resourceId);
		List<UserResource> list = userResourceMapper.selectByExample(example);
		UserResource userResource = null;
		if (!list.isEmpty()) {
			userResource = list.get(0);
			userResource.setUpdateDate(new Date());
			userResourceMapper.updateByPrimaryKey(userResource);
		}
		return userResource;
	}

	@Override
	public void addResource(int userId, int resourceId) throws IsBuyResourceException, PointCountIsLessException, GoldCountIsLessException {
		UserResourceExample example = new UserResourceExample();
		example.or().andUserIdEqualTo(userId).andResourceIdEqualTo(resourceId);
		List<UserResource> userResources = userResourceMapper.selectByExample(example);
		if (!userResources.isEmpty()) {
			throw new IsBuyResourceException("您已经购买请勿重复购买");
		}
		GoldPointsExample goldPointsExample = new GoldPointsExample();
		goldPointsExample.or().andUserIdEqualTo(userId);
		List<GoldPoints> list = goldPointsMapper.selectByExample(goldPointsExample);
		HashMap<String, Integer> map = new HashMap<>();
		map.put("pointCount", 0);
		map.put("goldCount", 0);
		for (GoldPoints goldPoints : list) {
			map.put("pointCount", map.get("pointCount") + goldPoints.getPointCount());
			map.put("goldCount", map.get("goldCount") + goldPoints.getGoldCount());
		}
		Resource resource = resourceMapper.selectByPrimaryKey(resourceId);
		GoldPoints goldPoints = new GoldPoints();
		goldPoints.setCreateDate(new Date());
		goldPoints.setUserId(userId);
		switch (resource.getCostType()) {
		case 0:
			if(resource.getCostNumber()>map.get("pointCount")){
				throw new PointCountIsLessException("您的积分不足");
			}
			goldPoints.setGoldCount(0);
			goldPoints.setPointCount(-resource.getCostNumber());
			goldPoints.setInfo("购买"+resource.getTitle()+"消耗"+resource.getCostNumber()+"积分");
			goldPointsMapper.insert(goldPoints);
			
			goldPoints.setUserId(resource.getUserId());
			goldPoints.setPointCount(resource.getCostNumber());
			goldPoints.setInfo("资源"+resource.getTitle()+"被购买，获得"+resource.getCostNumber()+"积分");
			goldPointsMapper.insert(goldPoints);
			break;
		case 1:
			if(resource.getCostNumber()>map.get("goldCount")){
				throw new GoldCountIsLessException("您的金币不足");
			}
			goldPoints.setPointCount(0);
			goldPoints.setGoldCount(-resource.getCostNumber());
			goldPoints.setInfo("购买"+resource.getTitle()+"消耗"+resource.getCostNumber()+"金币");
			goldPointsMapper.insert(goldPoints);
			
			goldPoints.setUserId(resource.getUserId());
			goldPoints.setGoldCount(resource.getCostNumber());
			goldPoints.setInfo("资源"+resource.getTitle()+"被购买，获得"+resource.getCostNumber()+"金币");
			goldPointsMapper.insert(goldPoints);
			break;
		default:
			break;
		}
		UserResource userResource = new UserResource();
		userResource.setCreateDate(new Date());
		userResource.setUpdateDate(new Date());
		userResource.setResourceId(resourceId);
		userResource.setUserId(userId);
		userResourceMapper.insertSelective(userResource);
	}

}
