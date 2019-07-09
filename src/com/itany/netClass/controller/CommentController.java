package com.itany.netClass.controller;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.annotation.ResponseEntity;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.mvc.util.HttpHeaders;
import com.itany.mvc.util.MediaType;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dto.ChapterDto;
import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.vo.ChapterQuery;
import com.itany.netClass.vo.CommentQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Controller
 */
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentServiceProxy = ObjectFactory.getObject("commentServiceProxy");

    @RequestMapping("/query")
	    @ResponseBody
	    public AjaxResult query(HttpServletRequest request, HttpServletResponse response) {
	        AjaxResult ar = AjaxResult.isOk("查询成功");
	        String page = request.getParameter("page");

        CommentQuery commentQuery = new CommentQuery();
	        CommonUtil.getObj(request, commentQuery, null);
	        try {
	            PageInfo<Comment> pageInfo = commentServiceProxy.findByConldition(Integer.parseInt(page), Constant.PAGE_SIZE, commentQuery);
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

			Comment comment = new  Comment();
			CommonUtil.getObj(request, comment, null);
			try {
				commentServiceProxy.updateStatus(comment);
			}catch (Exception e){
				e.printStackTrace();
				ar.setMsg(e.getMessage());
				ar.setSuccess(false);
			}
	    	return ar;
	    }
	    @RequestMapping("/findCommentByResourceIds")
	    @ResponseBody
	    public AjaxResult findCommentByResourceIds(HttpServletRequest request, HttpServletResponse response) {
	    	AjaxResult ar = AjaxResult.isOk("查询成功");
	    	String cids = request.getParameter("cids");
	    	String page = request.getParameter("page");
	    	try {
	    		PageInfo<Comment> pageInfo=commentServiceProxy.findCommentByResourceIds(Integer.parseInt(page),Constant.PAGE_SIZE,cids);
	    		ar.setObj(pageInfo);
	    	}catch (Exception e){
	    		e.printStackTrace();
	    		ar.setMsg(e.getMessage());
	    		ar.setSuccess(false);
	    	}
	    	return ar;
	    }
	    @RequestMapping("/addZan")
	    @ResponseBody
	    public AjaxResult addZan(HttpServletRequest request, HttpServletResponse response) {
	    	AjaxResult ar = AjaxResult.isOk("修改成功");
	    	String commentId = request.getParameter("commentId");
	    	String userId = request.getParameter("userId");
	    	try {
	    		commentServiceProxy.addZan(Integer.parseInt(commentId),Integer.parseInt(userId));
	    	}catch (Exception e){
	    		e.printStackTrace();
	    		ar.setMsg(e.getMessage());
	    		ar.setSuccess(false);
	    	}
	    	return ar;
	    }
	    @RequestMapping("/addComment")
	    @ResponseBody
	    public AjaxResult addComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        AjaxResult ar = AjaxResult.isOk("添加成功");
//	        String resourceId = request.getParameter("resourceId");
//	    	String userId = request.getParameter("userId");
//	    	String context = request.getParameter("context");
	        Comment comment = new Comment();
	        CommonUtil.getObj(request, comment, null);
	        try {
//	        	comment.setContext(context);
//	        	comment.setUserId(Integer.parseInt(userId));
//	        	comment.setResourceId(Integer.parseInt(resourceId));
	        	comment=commentServiceProxy.addComment(comment);
	        	ar.setObj(comment);
	        }catch (Exception e){
	            e.printStackTrace();
	            ar.setMsg(e.getMessage());
	            ar.setSuccess(false);
	        }
	        return ar;
	    }
}
