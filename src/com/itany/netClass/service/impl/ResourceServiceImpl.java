package com.itany.netClass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.ResourceMapper;
import com.itany.netClass.dto.ResourceDto;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.entity.ResourceExample;
import com.itany.netClass.exception.ResourceNameIsExistsException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ResourceService;
import com.itany.netClass.util.StringUtil;
import com.itany.netClass.vo.ResourceQuery;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ResourceServiceImpl implements ResourceService {

	private ResourceMapper resourceMapper = ObjectFactory.getObject("resourceMapper");

	@Override
	public PageInfo<Resource> findByConldition(int page, int pageSize, ResourceQuery resourceQuery) {
		PageHelper.startPage(page, pageSize);
		List<Resource> list = resourceMapper.selectByCondition(resourceQuery);
		PageInfo<Resource> pageInfo = new PageInfo<Resource>(list);
		return pageInfo;
	}

	@Override
	public void updateStatus(Resource resource) {
		resourceMapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public void add(ResourceDto resourceDto) throws FileUploadException, ResourceNameIsExistsException {
		ResourceExample example = new ResourceExample();
		example.or().andTitleEqualTo(resourceDto.getTitle().trim());
		List<Resource> list = resourceMapper.selectByExample(example);
		if (!list.isEmpty()) {
			throw new ResourceNameIsExistsException("资源标题已存在");
		}
		// 1.文件上传，将文件上传保存到Web服务器的目录下
		String fileName = StringUtil.renameFileName(resourceDto.getFileName());
		String filePath = resourceDto.getUploadPath() + "/" + fileName;
		try {
			FileUtils.copyInputStreamToFile(resourceDto.getInputStream(), new File(filePath));
		} catch (IOException e) {
			throw new FileUploadException("文件上传失败：" + e.getMessage());
		}
		Resource resource = new Resource();
		resource.setCreateDate(new Date());
		resource.setCostType(resourceDto.getCostType());
		resource.setCostNumber(resourceDto.getCostNumber());
		resource.setFileSize(resourceDto.getFileSize());
		resource.setTitle(resourceDto.getTitle().trim());
		resource.setFileType("0");
		// resource.setCoverImageUrl(chapterDto.getCoverImageUrl()+"/"+fileName);
		resource.setCoverImageUrl((resourceDto.getCoverImageUrl() + "/" + "default.png").replace("\\", "/"));
		resource.setOriginalName(resourceDto.getFileName());
		resource.setPath((resourceDto.getPath() + "/" + fileName).replace("\\", "/"));
		resource.setUserId(resourceDto.getUserId());
		resource.setStatus(Constant.STATUS_DISABLE);
		resource.setClickCount(1);
		resourceMapper.insertSelective(resource);
	}

	@Override
	public Resource singleResource(Resource resource) {
		return resourceMapper.selectByPrimaryKey(resource.getId());
	}

	@Override
	public void update(ResourceDto resourceDto) throws ResourceNameIsExistsException, FileUploadException {
		ResourceExample example = new ResourceExample();
		example.or().andTitleEqualTo(resourceDto.getTitle().trim());
		List<Resource> list = resourceMapper.selectByExample(example);
		if (!list.isEmpty()&&!list.get(0).equals(resourceDto.getId())) {
			throw new ResourceNameIsExistsException("资源标题已存在");
		}
		// 1.文件上传，将文件上传保存到Web服务器的目录下
		Resource resource = new Resource();
		if (resourceDto.getInputStream() != null) {
			String fileName = StringUtil.renameFileName(resourceDto.getFileName());
			String filePath = resourceDto.getUploadPath() + "/" + fileName;
			try {
				FileUtils.copyInputStreamToFile(resourceDto.getInputStream(), new File(filePath));
			} catch (IOException e) {
				throw new FileUploadException("文件上传失败：" + e.getMessage());
			}
			resource.setOriginalName(resourceDto.getFileName());
			resource.setFileSize(resourceDto.getFileSize());
			resource.setPath((resourceDto.getPath() + "/" + fileName).replace("\\", "/"));
		}
		resource.setId(resourceDto.getId());
		resource.setCostType(resourceDto.getCostType());
		resource.setCostNumber(resourceDto.getCostNumber());
		resource.setTitle(resourceDto.getTitle().trim());
		// resource.setCoverImageUrl(chapterDto.getCoverImageUrl()+"/"+fileName);
		// resource.setCoverImageUrl((resourceDto.getCoverImageUrl() + "/" +
		// "default.png").replace("\\", "/"));

		resourceMapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public void delete(Resource resource) {
		resourceMapper.deleteByPrimaryKey(resource.getId());
	}
	
	

}
