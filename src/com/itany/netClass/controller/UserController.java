package com.itany.netClass.controller;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.entity.User;
import com.itany.netClass.entity.UserResource;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.filters.ApplicationLisenter;
import com.itany.netClass.service.UserService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.CommonUtil.MyImage;
import com.itany.netClass.vo.UserQuery;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@RequestMapping("/user")
public class UserController {

	private UserService userService = ObjectFactory.getObject("userService");

	@RequestMapping("/updateStatus")
	@ResponseBody
	public AjaxResult updateStatus(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("修改成功");
		String page = request.getParameter("page");

		User user = new User();
		CommonUtil.getObj(request, user, null);
		try {
			userService.updateStatus(user);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}

		return ar;
	}

	@RequestMapping("/query")
	@ResponseBody
	public AjaxResult query(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("查询成功");
		String page = request.getParameter("page");

		UserQuery userQuery = new UserQuery();
		CommonUtil.getObj(request, userQuery, null);
		
		
		
		try {
			PageInfo<User> pageInfo = userService.findByCondition(Integer.parseInt(page), Constant.PAGE_SIZE,
					userQuery);
			ar.setObj(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}

		return ar;
	}

	@RequestMapping("/singleUser")
	@ResponseBody
	public AjaxResult singleUser(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("查询成功");
		String id = request.getParameter("id");
		try {
			User user = userService.singleById(Integer.parseInt(id));
			ar.setObj(user);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}

		return ar;
	}

	@RequestMapping("/updateUser")
	@ResponseBody
	public AjaxResult updateUser(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("修改成功");
		User user = new User();
		CommonUtil.getObj(request, user, null);
		if (CommonUtil.isEmpty(user.getNickname())) {
			ar.setSuccess(false);
			ar.setMsg("昵称不能为空");
			return ar;
		}

		if (CommonUtil.isEmpty(user.getRole())) {
			ar.setSuccess(false);
			ar.setMsg("角色不能为空");
			return ar;
		}
		if (CommonUtil.isEmpty(user.getEmail())) {
			ar.setSuccess(false);
			ar.setMsg("邮箱不能为空");
			return ar;
		}
		try {
			userService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			// 注册失败原因
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
		return ar;
	}

	/*------------------------前台--------------------------*/
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("修改成功");
		String newPassword = request.getParameter("newPassword");
		String rePassword = request.getParameter("rePassword");
		if (CommonUtil.isEmpty(newPassword)) {
			ar.setSuccess(false);
			ar.setMsg("新密码不能为空");
			return ar;
		}
		if (!newPassword.equals(rePassword)) {
			ar.setMsg("两次密码输入不一致");
			ar.setSuccess(false);
			return ar;
		}
		User user = new User();
		CommonUtil.getObj(request, user, null);
		user.setId(ApplicationLisenter.getUsers().get(user.getLoginName()).getId());
		if (CommonUtil.isEmpty(user.getNickname())) {
			ar.setSuccess(false);
			ar.setMsg("昵称不能为空");
			return ar;
		}

		if (CommonUtil.isEmpty(user.getEmail())) {
			ar.setSuccess(false);
			ar.setMsg("邮箱不能为空");
			return ar;
		}

		try {
			user = userService.update(user, newPassword);
			ApplicationLisenter.getUsers().put(user.getLoginName(), user);
		} catch (Exception e) {
			e.printStackTrace();
			// 注册失败原因
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}

		ar.setObj(user);
		return ar;
	}

	@RequestMapping("/regist")
	@ResponseBody
	public AjaxResult regist(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("注册成功");
		User user = new User();
		CommonUtil.getObj(request, user, null);
		// 数据格式验证
		if (CommonUtil.isEmpty(user.getLoginName())) {
			ar.setSuccess(false);
			ar.setMsg("登录名不能为空");
			return ar;
		}
		if (CommonUtil.isEmpty(user.getPassword())) {
			ar.setSuccess(false);
			ar.setMsg("密码不能为空");
			return ar;
		}
		if (CommonUtil.isEmpty(user.getNickname())) {
			ar.setSuccess(false);
			ar.setMsg("昵称不能为空");
			return ar;
		}
		if (CommonUtil.isEmpty(user.getEmail())) {
			ar.setSuccess(false);
			ar.setMsg("邮箱不能为空");
			return ar;
		}

		try {
			userService.add(user);
		} catch (Exception e) {
			e.printStackTrace();
			// 注册失败原因
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
		return ar;
	}

	@RequestMapping("/login")
	@ResponseBody
	public AjaxResult login(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("登录成功");
		User user = new User();
		CommonUtil.getObj(request, user, null);
		if (CommonUtil.isEmpty(user.getLoginName())) {
			ar.setSuccess(false);
			ar.setMsg("用户名不能为空");
			return ar;
		}

		if (CommonUtil.isEmpty(user.getPassword())) {
			ar.setSuccess(false);
			ar.setMsg("密码不能为空");
			return ar;
		}

		try {
			User loginUser = userService.login(user);
			loginUser.setPassword(null);// 清除密码
			ar.setObj(loginUser);
			Map<String, User> usersMap = ApplicationLisenter.getUsers();
			usersMap.put(loginUser.getLoginName(), loginUser);

		} catch (Exception e) {
			e.printStackTrace();
			ar.setSuccess(false);
			ar.setMsg(e.getMessage());
		}
		return ar;
	}

	@RequestMapping("/sign")
	@ResponseBody
	public AjaxResult sign(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("签到成功");
		try {
			String loginName = request.getParameter("loginName");
			Map<String, User> usersMap = ApplicationLisenter.getUsers();
			User loginUser = usersMap.get(loginName);
			if (null == loginUser) {
				ar.setSuccess(false);
				ar.setMsg("当前用户未登录");
				return ar;
			}
			userService.sign(loginUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ar.setSuccess(false);
			ar.setMsg(e.getMessage());
		}
		return ar;
	}

	@RequestMapping("/loginOut")
	@ResponseBody
	public AjaxResult loginOut(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("退出成功");
		String loginName = request.getParameter("loginName");
		Map<String, User> usersMap = ApplicationLisenter.getUsers();
		usersMap.remove(loginName);
		return ar;
	}

	@RequestMapping("/check")
	@ResponseBody
	public AjaxResult check(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("登录验证通过");
		Map<String, User> usersMap = ApplicationLisenter.getUsers();
		String loginName = request.getParameter("loginName");
		User user = usersMap.get(loginName);
		if (null == user) {
			ar.setSuccess(false);
			ar.setMsg("当前未登录");
			return ar;
		}
		ar.setObj(user);
		return ar;
	}

	@RequestMapping("/addResource")
	@ResponseBody
	public AjaxResult addResource(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("购买成功");
		String userId = request.getParameter("userId");
		String resourceId = request.getParameter("resourceId");

		try {
			userService.addResource(Integer.parseInt(userId), Integer.parseInt(resourceId));
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
		return ar;
	}

	@RequestMapping("/findResourceByUserId")
	@ResponseBody
	public AjaxResult findResourceByUserId(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("查询成功");
		String userId = request.getParameter("userId");
		String resourceId = request.getParameter("resourceId");
		try {
			UserResource resource = userService.findResourceByUserId(Integer.parseInt(userId),
					Integer.parseInt(resourceId));
			ar.setObj(resource);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
		return ar;
	}

	@RequestMapping("/getCodeImage")
	public void getCodeImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String numStr = request.getParameter("num");
		MyImage m = null;
		if (null == numStr) {
			numStr = "0";
		}
		int num = 0;
		try {
			num = Integer.parseInt(numStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (num) {
		case 0:
			String codeStr = request.getParameter("codeStr");
			if (null == codeStr) {
				codeStr = CommonUtil.randomCode();
			}
			// 用自己的字符串来生成图片
			m = CommonUtil.getImage(null, codeStr, true);
			break;
		case 1:
			// 使用中文验证码,有干扰线
			m = CommonUtil.getImage(null, 4, true, true);
			break;
		case 2:
			// 使用中文验证码,没有干扰线
			m = CommonUtil.getImage(null, 4, true, false);
			break;
		case 3:
			// 使用数字和字母(4个)验证码,有干扰线
			m = CommonUtil.getImage(null, 4, false, true);
			break;
		case 4:
			// 使用数字和字母(4个)验证码,没有干扰线
			m = CommonUtil.getImage(null, 4, false, false);
			break;
		default:
			// 使用数字和字母验证码,有干扰线
			m = CommonUtil.getImage(null, num, false, true);
			break;
		}
		System.out.println("code=" + m.getCode());
		request.getSession().setAttribute("code", m.getCode());
		ServletOutputStream out = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(m.getImage());
	}

}
