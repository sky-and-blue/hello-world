package com.itany.netClass.controller;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.filters.ApplicationLisenter;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.service.GoldPointsService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 管理员 Controller
 */
@RequestMapping("/goldPoints")
public class GoldPointsController {

    private GoldPointsService goldPointsServiceProxy = ObjectFactory.getObject("goldPointsServiceProxy");

    @RequestMapping("/findByUserId")
    @ResponseBody
    public AjaxResult findByUserId(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = AjaxResult.isOk("登录成功");
        String userId =request.getParameter("userId");
        try {

            Map<String,Integer> goldPointsMap = goldPointsServiceProxy.findByUserId(Integer.parseInt(userId));
            ar.setObj(goldPointsMap);
        } catch (Exception e) {
            e.printStackTrace();
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        }
        return ar;
    }
    @RequestMapping("/findgoldPoints")
    @ResponseBody
    public AjaxResult findgoldPoints(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = AjaxResult.isOk("登录成功");
        String userId =request.getParameter("userId");
        String page =request.getParameter("page");
        try {

            PageInfo<GoldPoints> pageInfo =goldPointsServiceProxy.findgoldPoints(Integer.parseInt(page),4,Integer.parseInt(userId));
            ar.setObj(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        }
        return ar;
    }
    @RequestMapping("/changeMoney")
    @ResponseBody
    public AjaxResult changeMoney(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = AjaxResult.isOk("登录成功");
        String userId =request.getParameter("userId");
        String changeNum = request.getParameter("changeNum");
        try {
            goldPointsServiceProxy.changeMoney(Integer.parseInt(changeNum),Integer.parseInt(userId));
        } catch (Exception e) {
            e.printStackTrace();
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        }
        return ar;
    }
//
//    @RequestMapping("/check")
//    @ResponseBody
//    public AjaxResult check(HttpServletRequest request, HttpServletResponse response) {
//    	AjaxResult ar = AjaxResult.isOk("登录验证通过");
//		Map<String, User> usersMap = ApplicationLisenter.getUsers();
//		String loginName = request.getParameter("loginName");
//		User user = usersMap.get(loginName);
//		if(null == user){
//			ar.setSuccess(false);
//			ar.setMsg("当前未登录");
//			return ar;
//		}
//		ar.setObj(user);
//		return ar;
//    }
//
//    @RequestMapping("/loginOut")
//    @ResponseBody
//    public AjaxResult loginOut(HttpServletRequest request, HttpServletResponse response) {
//    	AjaxResult ar = AjaxResult.isOk("退出成功");
//		String loginName = request.getParameter("loginName");
//		Map<String, User> usersMap = ApplicationLisenter.getUsers();
//		usersMap.remove(loginName);
//        return ar;
//    }
}
