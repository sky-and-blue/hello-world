package com.itany.netClass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dto.ResourceDto;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ResourceService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.vo.ResourceQuery;

/**
 * Controller
 */
@RequestMapping("/resource")
public class ResourceController {

	private ResourceService resourceServiceProxy = ObjectFactory.getObject("resourceServiceProxy");

	@RequestMapping("/query")
	@ResponseBody
	public AjaxResult query(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("查询成功");
		String page = request.getParameter("page");

		ResourceQuery resourceQuery = new ResourceQuery();
		CommonUtil.getObj(request, resourceQuery, null);
		try {
			PageInfo<Resource> pageInfo = resourceServiceProxy.findByConldition(Integer.parseInt(page),
					Constant.PAGE_SIZE, resourceQuery);
			ar.setObj(pageInfo);
		} catch (Exception e) {
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

		Resource resource = new Resource();
		CommonUtil.getObj(request, resource, null);
		try {
			resourceServiceProxy.updateStatus(resource);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
		return ar;
	}

	/** ---------------------------------前台------------------------------ */
	@RequestMapping("/add")
	@ResponseBody
	public AjaxResult add(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> files)
			throws IOException {
		AjaxResult ar = AjaxResult.isOk("添加成功");

		ResourceDto resourceDto = new ResourceDto();
		CommonUtil.getObj(request, resourceDto, null);

		CommonsMultipartFile file = files.get(0);
		resourceDto.setInputStream(file.getInputStream());
		resourceDto.setFileName(file.getOriginalFilename());
		resourceDto.setFileSize((int) file.getSize());
		resourceDto.setUploadPath(request.getSession().getServletContext().getRealPath("/pages/upload"));
		resourceDto.setPath("/pages/upload");
		resourceDto.setCoverImageUrl("/pages/upload");
		try {
			resourceServiceProxy.add(resourceDto);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
		return ar;
	}

	@RequestMapping("/singleResource")
	@ResponseBody
	public AjaxResult singleResource(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("查询成功");

		Resource resource = new Resource();
		CommonUtil.getObj(request, resource, null);
		try {
			resource = resourceServiceProxy.singleResource(resource);
			ar.setObj(resource);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
		return ar;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> files)
			throws IOException {
		AjaxResult ar = AjaxResult.isOk("修改成功");

		ResourceDto resourceDto = new ResourceDto();
		CommonUtil.getObj(request, resourceDto, null);

		if (!files.isEmpty() && files.get(0).getSize() > 0) {
			CommonsMultipartFile file = files.get(0);
			resourceDto.setInputStream(file.getInputStream());
			resourceDto.setFileName(file.getOriginalFilename());
			resourceDto.setFileSize((int) file.getSize());
			resourceDto.setUploadPath(request.getSession().getServletContext().getRealPath("/pages/upload"));
			resourceDto.setPath("/pages/upload");
			resourceDto.setCoverImageUrl("/pages/upload");
		}
		try {
			resourceServiceProxy.update(resourceDto);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
		return ar;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AjaxResult ar = AjaxResult.isOk("删除成功");
		Resource resource = new Resource();
		CommonUtil.getObj(request, resource, null);
		if(resource.getId()==null){
			ar.setMsg("删除失败");
			ar.setSuccess(false);
			return ar;
		}
		try {
			resourceServiceProxy.delete(resource);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
		return ar;
	}
}
