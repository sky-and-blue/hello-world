package com.itany.netClass.controller;

import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.filters.ApplicationLisenter;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.service.UserService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.CommonUtil.MyImage;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 管理员 Controller
 */
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminServiceProxy = ObjectFactory.getObject("adminServiceProxy");

    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(HttpServletRequest request, HttpServletResponse response) {
    	   AjaxResult ar = AjaxResult.isOk("登录成功");
           User user = new User();
           CommonUtil.getObj(request, user, null);
           if(CommonUtil.isEmpty(user.getLoginName())){
               ar.setSuccess(false);
               ar.setMsg("用户名不能为空");
               return ar;
           }

           if(CommonUtil.isEmpty(user.getPassword())){
               ar.setSuccess(false);
               ar.setMsg("密码不能为空");
               return ar;
           }

           try {
               User loginUser = adminServiceProxy.login(user);
               loginUser.setPassword(null);//清除密码
               ar.setObj(loginUser);
               ApplicationLisenter.getUsers().put(user.getLoginName(),user);
           } catch (Exception e) {
               e.printStackTrace();
               ar.setSuccess(false);
               ar.setMsg(e.getMessage());
           }
           return ar;
    }

    @RequestMapping("/check")
    @ResponseBody
    public AjaxResult check(HttpServletRequest request, HttpServletResponse response) {
    	AjaxResult ar = AjaxResult.isOk("登录验证通过");
		Map<String, User> usersMap = ApplicationLisenter.getUsers();
		String loginName = request.getParameter("loginName");
		User user = usersMap.get(loginName);
		if(null == user){
			ar.setSuccess(false);
			ar.setMsg("当前未登录");
			return ar;
		}
		ar.setObj(user);
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
}
