package com.itany.netClass.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;

@RequestMapping("/courseType")
public class CourseTypeController {

    private CourseTypeService courseTypeService = ObjectFactory.getObject("courseTypeService");

    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult add(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = AjaxResult.isOk("添加成功");
        CourseType type = new CourseType();
        CommonUtil.getObj(request,type, null);
        //数据格式校验
        if(CommonUtil.isEmpty(type.getTypeName())){
            ar.setSuccess(false);
            ar.setMsg("分类名不能为空");
            return ar;
        }
        try {
            courseTypeService.addCourseType(type);
        }catch (Exception e){
            e.printStackTrace();
            ar.setMsg(e.getMessage());
            ar.setSuccess(false);
        }
        return ar;
    }

    @RequestMapping("/findBackCourseType")
    @ResponseBody
    public AjaxResult findBackCourseType(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = AjaxResult.isOk("查询成功");
        String parentIdStr = request.getParameter("parentId");
        String pageNoStr = request.getParameter("pageNo");
        Integer pageNo = 1;
        if(!CommonUtil.isEmpty(pageNoStr)){
            pageNo = Integer.parseInt(pageNoStr);
        }
        Integer parentId = null;
        if(!CommonUtil.isEmpty(parentIdStr)){
            parentId = Integer.parseInt(parentIdStr);
        }
        try {
            PageInfo<CourseType> pageInfo = courseTypeService.findChildrenByParentId(pageNo, parentId);
            ar.setObj(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            ar.setMsg(e.getMessage());
            ar.setSuccess(false);
        }
        return ar;
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public AjaxResult updateStatus(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = AjaxResult.isOk("修改成功");
        String page = request.getParameter("page");

        CourseType type = new CourseType();
        CommonUtil.getObj(request, type, null);
        try {
            courseTypeService.updateStatus(type);
        }catch (Exception e){
            e.printStackTrace();
            ar.setMsg(e.getMessage());
            ar.setSuccess(false);
        }

        return ar;
    }
    /** -------------前台------------------*/
    @RequestMapping("/findMenu")
    @ResponseBody
    public AjaxResult findMenu(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = AjaxResult.isOk("查询成功");
        try {
           List<CourseType> menu = courseTypeService.findMenu();
            ar.setObj(menu);
        }catch (Exception e){
            e.printStackTrace();
            ar.setMsg(e.getMessage());
            ar.setSuccess(false);
        }
        return ar;
    }
    @RequestMapping("/findAllType")
    @ResponseBody
    public AjaxResult findAllType(HttpServletRequest request, HttpServletResponse response) {
    	AjaxResult ar = AjaxResult.isOk("查询成功");
    	String parentId = request.getParameter("parentId");
    	Integer id=null;
    	
    	try {
    		if(parentId!=null&&parentId.trim()!=""){
        		id=Integer.parseInt(parentId);
        	}
    		List<CourseType> menu = courseTypeService.findAllType(id);
    		ar.setObj(menu);
    	}catch (Exception e){
    		e.printStackTrace();
    		ar.setMsg(e.getMessage());
    		ar.setSuccess(false);
    	}
    	return ar;
    }
    
}
