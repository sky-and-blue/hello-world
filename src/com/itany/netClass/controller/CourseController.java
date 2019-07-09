package com.itany.netClass.controller;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dto.CourseDto;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.filters.ApplicationLisenter;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.service.CourseService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.vo.CourseQuery;
import com.itany.netClass.vo.UserQuery;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Controller
 */
@RequestMapping("/course")
public class CourseController {

    private CourseService courseServiceProxy = ObjectFactory.getObject("courseServiceProxy");

    @RequestMapping("/query")
    @ResponseBody
    public AjaxResult query(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = AjaxResult.isOk("查询成功");
        String page = request.getParameter("page");

        CourseQuery courseQuery = new CourseQuery();
        CommonUtil.getObj(request, courseQuery, null);
        try {
            PageInfo<Course> pageInfo = courseServiceProxy.findByCondition(Integer.parseInt(page), Constant.PAGE_SIZE, courseQuery);
            ar.setObj(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            ar.setMsg(e.getMessage());
            ar.setSuccess(false);
        }
        return ar;
    }
    @RequestMapping("/serachType")
    @ResponseBody
    public AjaxResult serachType(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = AjaxResult.isOk("查询成功");
        try {
            List<CourseType> courseTypes = courseServiceProxy.serachType();
            ar.setObj(courseTypes);
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

		Course course = new Course();
		CommonUtil.getObj(request, course, null);
		try {
			courseServiceProxy.updateStatus(course);
		}catch (Exception e){
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
    	return ar;
    }

    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult add(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> files) throws IOException {
    	 AjaxResult ar = AjaxResult.isOk("添加成功");

         CourseDto courseDto = new CourseDto();
         CommonUtil.getObj(request, courseDto, null);

         if (CommonUtil.isEmpty(courseDto.getCourseName())) {
             ar.setSuccess(false);
             ar.setMsg("课程名不能为空");
             return ar;
         }
         if (CommonUtil.isEmpty(courseDto.getCourseInfo())) {
             ar.setSuccess(false);
             ar.setMsg("课程简介不能为空");
             return ar;
         }
         if (CommonUtil.isEmpty(courseDto.getAuthor())) {
             ar.setSuccess(false);
             ar.setMsg("作者不能为空");
             return ar;
         }
         if (files.isEmpty()||files.get(0).getSize()==0) {
             ar.setSuccess(false);
             ar.setMsg("请上传封面图片");
             return ar;
         }
         if(!files.get(0).getOriginalFilename().matches(".+(.png|.jpg)$")){
             ar.setSuccess(false);
             ar.setMsg("封面图片格式不对");
             return ar;
         }
         if(files.get(0).getSize()>10*1024*1024){
             ar.setSuccess(false);
             ar.setMsg("封面图片太大");
             return ar;
         }
         if (courseDto.getRecommendationGrade()==-1) {
             ar.setSuccess(false);
             ar.setMsg("请选择推荐等级");
             return ar;
         }
         if (courseDto.getCourseTypeId()==-1) {
             ar.setSuccess(false);
             ar.setMsg("请选择课程类别");
             return ar;
         }
         CommonsMultipartFile file = files.get(0);
         courseDto.setInputStream(file.getInputStream());
         courseDto.setFileName(file.getOriginalFilename());
         courseDto.setUploadPath(request.getSession().getServletContext().getRealPath("/pages/upload"));
         courseDto.setCoverImageUrl("/pages/upload");
         try {
             courseServiceProxy.add(courseDto);
         } catch (Exception e) {
             e.printStackTrace();
             ar.setMsg(e.getMessage());
             ar.setSuccess(false);
         }
         return ar;
    }
    @RequestMapping("/singleCourse")
    @ResponseBody
    public AjaxResult singleCourse(HttpServletRequest request, HttpServletResponse response ) {
        AjaxResult ar = AjaxResult.isOk("查询成功");

        Course course = new Course();
        CommonUtil.getObj(request, course, null);
        try {
            course=courseServiceProxy.singleCourse(course);
            ar.setObj(course);
        }catch (Exception e){
            e.printStackTrace();
            ar.setMsg(e.getMessage());
            ar.setSuccess(false);
        }
        return ar;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> files) throws IOException {
    	  AjaxResult ar = AjaxResult.isOk("修改成功");

          CourseDto courseDto = new CourseDto();
          CommonUtil.getObj(request, courseDto, null);

          if (CommonUtil.isEmpty(courseDto.getCourseName())) {
              ar.setSuccess(false);
              ar.setMsg("课程名不能为空");
              return ar;
          }
          if (CommonUtil.isEmpty(courseDto.getCourseInfo())) {
              ar.setSuccess(false);
              ar.setMsg("课程简介不能为空");
              return ar;
          }
          if (CommonUtil.isEmpty(courseDto.getAuthor())) {
              ar.setSuccess(false);
              ar.setMsg("作者不能为空");
              return ar;
          }
          if (!files.isEmpty()&&files.get(0).getSize()!=0) {
              if(!files.get(0).getOriginalFilename().matches(".+(.png|.jpg)$")){
                  ar.setSuccess(false);
                  ar.setMsg("封面图片格式不对");
                  return ar;
              }
              if(files.get(0).getSize()>10*1024*1024){
                  ar.setSuccess(false);
                  ar.setMsg("封面图片太大");
                  return ar;
              }
          }
          if (courseDto.getRecommendationGrade()==-1) {
              ar.setSuccess(false);
              ar.setMsg("请选择推荐等级");
              return ar;
          }
          if (courseDto.getCourseTypeId()==-1) {
              ar.setSuccess(false);
              ar.setMsg("请选择课程类别");
              return ar;
          }



          if (files.size() > 0 && files.get(0).getSize() != 0) {
              CommonsMultipartFile file = files.get(0);
              courseDto.setInputStream(file.getInputStream());
              courseDto.setFileName(file.getOriginalFilename());
              courseDto.setUploadPath(request.getSession().getServletContext().getRealPath("/pages/upload"));
              courseDto.setCoverImageUrl("/pages/upload");
          }

          try {
              courseServiceProxy.update(courseDto);
          } catch (Exception e) {
              e.printStackTrace();
              ar.setMsg(e.getMessage());
              ar.setSuccess(false);
          }
          return ar;
    }
    /**-------------前台-----------------------------*/
    @RequestMapping("/findCourseByTypeId")
    @ResponseBody
    public AjaxResult findCourseByTypeId(HttpServletRequest request, HttpServletResponse response)  {
    	AjaxResult ar = AjaxResult.isOk("查询成功");
    	
    	String courseTypeId=request.getParameter("courseTypeId");
    	try {
    		List<Course> courseList=courseServiceProxy.findCourseByTypeId(Integer.parseInt(courseTypeId));
    		ar.setObj(courseList);
    	}catch (Exception e){
    		e.printStackTrace();
    		ar.setMsg(e.getMessage());
    		ar.setSuccess(false);
    	}
    	return ar;
    }
    @RequestMapping("/findCourse")
    @ResponseBody
    public AjaxResult findCourse(HttpServletRequest request, HttpServletResponse response)  {
    	AjaxResult ar = AjaxResult.isOk("查询成功");
    	
    	String page=request.getParameter("page");
    	try {
    		PageInfo<Course> pageInfo=courseServiceProxy.findCourse(Integer.parseInt(page),5);
    		ar.setObj(pageInfo);
    	}catch (Exception e){
    		e.printStackTrace();
    		ar.setMsg(e.getMessage());
    		ar.setSuccess(false);
    	}
    	return ar;
    }
    @RequestMapping("/updateClickNum")
    @ResponseBody
    public AjaxResult updateClickNum(HttpServletRequest request, HttpServletResponse response)  {
    	AjaxResult ar = AjaxResult.isOk("更新成功");
    	
    	String id =request.getParameter("id");
    	try {
    		courseServiceProxy.updateClickNum(Integer.parseInt(id));
    	}catch (Exception e){
    		e.printStackTrace();
    		ar.setMsg(e.getMessage());
    		ar.setSuccess(false);
    	}
    	return ar;
    }
    @RequestMapping("/findCourseByCourseTypeId")
    @ResponseBody
    public AjaxResult findCourseByCourseTypeId(HttpServletRequest request, HttpServletResponse response)  {
    	AjaxResult ar = AjaxResult.isOk("更新成功");
    	
    	String courseTypeId =request.getParameter("courseTypeId");
    	try {
    		List<Course> list = courseServiceProxy.findCourseByCourseTypeId(Integer.parseInt(courseTypeId));
    		ar.setObj(list);
    	}catch (Exception e){
    		e.printStackTrace();
    		ar.setMsg(e.getMessage());
    		ar.setSuccess(false);
    	}
    	return ar;
    }
    
}
