package com.itany.netClass.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.itany.netClass.entity.Resource;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.vo.ChapterQuery;

/**
 * Controller
 */
@RequestMapping("/chapter")
public class ChapterController {

    private ChapterService chapterServiceProxy = ObjectFactory.getObject("chapterServiceProxy");

    @RequestMapping("/query")
	    @ResponseBody
	    public AjaxResult query(HttpServletRequest request, HttpServletResponse response) {
	        AjaxResult ar = AjaxResult.isOk("查询成功");
	        String page = request.getParameter("page");
	
	        ChapterQuery chapterQuery = new ChapterQuery();
	        CommonUtil.getObj(request, chapterQuery, null);
	        try {
	            PageInfo<Chapter> pageInfo = chapterServiceProxy.findByConldition(Integer.parseInt(page), Constant.PAGE_SIZE, chapterQuery);
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
	
	    	Chapter chapter = new Chapter();
			CommonUtil.getObj(request, chapter, null);
			try {
				chapterServiceProxy.updateStatus(chapter);
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
	
	        ChapterDto chapterDto = new ChapterDto();
	        CommonUtil.getObj(request, chapterDto, null);
	
	        CommonsMultipartFile file=files.get(0);
	        chapterDto.setInputStream(file.getInputStream());
	        chapterDto.setFileName(file.getOriginalFilename());
	        chapterDto.setFileSize((int)file.getSize());
	        chapterDto.setUploadPath(request.getSession().getServletContext().getRealPath("/pages/upload"));
	        chapterDto.setPath("/pages/upload");
	        chapterDto.setCoverImageUrl("/pages/upload");
	        try {
	            chapterServiceProxy.add(chapterDto);
	        }catch (Exception e){
	            e.printStackTrace();
	            ar.setMsg(e.getMessage());
	            ar.setSuccess(false);
	        }
	        return ar;
	    }

    @RequestMapping("/singleChapter")
    @ResponseBody
    public AjaxResult singleChapter(HttpServletRequest request, HttpServletResponse response ) {
        AjaxResult ar = AjaxResult.isOk("查询成功");

        Chapter chapter = new Chapter();
        CommonUtil.getObj(request, chapter, null);
        try {
        	chapter=chapterServiceProxy.singleChapter(chapter);
            ar.setObj(chapter);
        }catch (Exception e){
            e.printStackTrace();
            ar.setMsg(e.getMessage());
            ar.setSuccess(false);
        }
        return ar;
    }
    @RequestMapping("/resourceDetail")
    @ResponseBody
    public AjaxResult resourceDetail(HttpServletRequest request, HttpServletResponse response ) {
        AjaxResult ar = AjaxResult.isOk("查询成功");

		Resource resource = new Resource();
        CommonUtil.getObj(request, resource, null);
        try {
			resource=chapterServiceProxy.singleResource(resource);
            ar.setObj(resource);
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

        ChapterDto chapterDto = new ChapterDto();
        CommonUtil.getObj(request, chapterDto, null);
        if(files.size()>0&&files.get(0).getSize()!=0){
            CommonsMultipartFile file=files.get(0);
            chapterDto.setInputStream(file.getInputStream());
	        chapterDto.setFileName(file.getOriginalFilename());
	        chapterDto.setFileSize((int)file.getSize());
	        chapterDto.setUploadPath(request.getSession().getServletContext().getRealPath("/pages/upload"));
	        chapterDto.setPath("/pages/upload");
	        chapterDto.setCoverImageUrl("/pages/upload");
        }

        try {
        	chapterServiceProxy.update(chapterDto);
        }catch (Exception e){
            e.printStackTrace();
            ar.setMsg(e.getMessage());
            ar.setSuccess(false);
        }
        return ar;
    }

	@RequestMapping("/findChapterByCourseId")
	@ResponseBody
	public AjaxResult findChapterByCourseId(HttpServletRequest request, HttpServletResponse response ) {
		AjaxResult ar = AjaxResult.isOk("查询成功");
		String courseId = request.getParameter("courseId");

		try {
			List<Chapter> list=chapterServiceProxy.findChapterByCourseId(Integer.parseInt(courseId));
			ar.setObj(list);
		}catch (Exception e){
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
		return ar;
	}

}
