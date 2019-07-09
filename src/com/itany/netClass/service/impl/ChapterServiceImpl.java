package com.itany.netClass.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.itany.netClass.entity.ChapterExample;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.ChapterMapper;
import com.itany.netClass.dao.ResourceMapper;
import com.itany.netClass.dto.ChapterDto;
import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.entity.ResourceExample;
import com.itany.netClass.exception.ChapterTitleException;
import com.itany.netClass.exception.ResourceTitleException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.util.StringUtil;
import com.itany.netClass.vo.ChapterQuery;

public class ChapterServiceImpl implements ChapterService {

	private ChapterMapper chapterMapper = ObjectFactory.getObject("chapterMapper");
	private ResourceMapper resourceMapper = ObjectFactory.getObject("resourceMapper");

	@Override
	public PageInfo<Chapter> findByConldition(int page, int pageSize, ChapterQuery chapterQuery) {
		PageHelper.startPage(page, pageSize);
		List<Chapter> list = chapterMapper.selectByCondition(chapterQuery);
		PageInfo<Chapter> pageInfo = new PageInfo<Chapter>(list);
		return pageInfo;
	}

	@Override
	public void updateStatus(Chapter chapter) {
		chapterMapper.updateByPrimaryKeySelective(chapter);
	}

	@Override
	public void add(ChapterDto chapterDto) throws FileUploadException, ChapterTitleException, ResourceTitleException {
		ChapterExample example = new ChapterExample();
		example.or().andTitleEqualTo(chapterDto.getTitle().trim());
		List<Chapter> chapters = chapterMapper.selectByExample(example);
		if(!chapters.isEmpty()){
			throw new ChapterTitleException("章节标题已存在");
		}
		ResourceExample resourceExample = new ResourceExample();
		resourceExample.or().andTitleEqualTo(chapterDto.getResourceTitle().trim());
		List<Resource> resources = resourceMapper.selectByExample(resourceExample);
		if(!resources.isEmpty()){
			throw new ResourceTitleException("资源标题已存在");
		}
		
		// 1.文件上传，将文件上传保存到Web服务器的目录下
		String fileName = StringUtil.renameFileName(chapterDto.getFileName());
		String filePath = chapterDto.getUploadPath() + "/" + fileName;
		try {
			FileUtils.copyInputStreamToFile(chapterDto.getInputStream(), new File(filePath));
		} catch (IOException e) {
			throw new FileUploadException("文件上传失败：" + e.getMessage());
		}
		// 2.保存到数据库，将DTO转换为PO
		Chapter chapter = new Chapter();
		chapter.setCreateDate(new Date());
		chapter.setCourseId(chapterDto.getCourseId());
		chapter.setInfo(chapterDto.getInfo());
		chapter.setTitle(chapterDto.getTitle().trim());
		chapter.setStatus(Constant.STATUS_DISABLE);
		chapterMapper.insert(chapter);
		Resource resource = new Resource();
		resource.setChapterId(chapter.getId());
		resource.setCreateDate(new Date());
		resource.setCostType(chapterDto.getCostType());
		resource.setCostNumber(chapterDto.getCostNumber());
		resource.setFileSize(chapterDto.getFileSize());
		resource.setTitle(chapterDto.getResourceTitle().trim());
		resource.setFileType(chapterDto.getFileType());
		// resource.setCoverImageUrl(chapterDto.getCoverImageUrl()+"/"+fileName);
		resource.setCoverImageUrl((chapterDto.getCoverImageUrl() + "/" + "default.png").replace("\\", "/"));
		resource.setOriginalName(chapterDto.getFileName());
		resource.setPath((chapterDto.getPath() + "/" + fileName).replace("\\", "/"));
		resource.setUserId(chapterDto.getUserId());
		resource.setStatus(Constant.STATUS_DISABLE);
		resource.setClickCount(0);
		resourceMapper.insertSelective(resource);
	}

	@Override
	public Chapter singleChapter(Chapter chapter) {
		chapter = chapterMapper.selectByPrimaryKey(chapter.getId());
		ResourceExample resourceExample = new ResourceExample();
		resourceExample.or().andChapterIdEqualTo(chapter.getId());
		List<Resource> list = resourceMapper.selectByExample(resourceExample);
		chapter.setResource(list.size() > 0 ? list.get(0) : null);
		return chapter;
	}

	@Override
	public void update(ChapterDto chapterDto) throws FileUploadException, ResourceTitleException, ChapterTitleException {
		ChapterExample example = new ChapterExample();
		example.or().andTitleEqualTo(chapterDto.getTitle().trim());
		List<Chapter> chapters = chapterMapper.selectByExample(example);
		if(!chapters.isEmpty()&&!chapters.get(0).getId().equals(chapterDto.getId())){
			throw new ChapterTitleException("章节标题已存在");
		}
		ResourceExample resourceExample = new ResourceExample();
		resourceExample.or().andTitleEqualTo(chapterDto.getResourceTitle().trim());
		List<Resource> resources = resourceMapper.selectByExample(resourceExample);
		if(!resources.isEmpty()&&!resources.get(0).getId().equals(chapterDto.getResourceId())){
			throw new ResourceTitleException("资源标题已存在");
		}
		
		Resource resource = new Resource();
		// 1.文件上传，将文件上传保存到Web服务器的目录下
		if (chapterDto.getInputStream() != null) {
			String fileName = StringUtil.renameFileName(chapterDto.getFileName());
			String filePath = chapterDto.getUploadPath() + "/" + fileName;
			try {
				FileUtils.copyInputStreamToFile(chapterDto.getInputStream(), new File(filePath));
				resource.setPath((chapterDto.getPath() + "/" + fileName).replace("\\", "/"));
				resource.setFileType(chapterDto.getFileType());
				resource.setOriginalName(chapterDto.getFileName());
				resource.setFileSize(chapterDto.getFileSize());
			} catch (IOException e) {
				throw new FileUploadException("文件上传失败：" + e.getMessage());
			}
		}

		// 2.保存到数据库，将DTO转换为PO
		// 2.保存到数据库，将DTO转换为PO
				Chapter chapter = new Chapter();
				chapter.setId(chapterDto.getId());
				chapter.setInfo(chapterDto.getInfo());
				chapter.setTitle(chapterDto.getTitle());
				chapterMapper.updateByPrimaryKeySelective(chapter);
				
				resource.setId(chapterDto.getResourceId());
				resource.setCostType(chapterDto.getCostType());
				resource.setCostNumber(chapterDto.getCostNumber());
				resource.setTitle(chapterDto.getResourceTitle());
				// resource.setCoverImageUrl(chapterDto.getCoverImageUrl()+"/"+fileName);
//				resource.setCoverImageUrl((chapterDto.getCoverImageUrl() + "/" + "default.png").replace("\\", "/"));
				resourceMapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public Resource singleResource(Resource resource) {
		if(resource.getId()!=null){
			return resourceMapper.selectByPrimaryKey(resource.getId());
		}else if (resource.getChapterId()!=null){
			ResourceExample resourceExample = new ResourceExample();
			resourceExample.or().andChapterIdEqualTo(resource.getChapterId());
			List<Resource> list = resourceMapper.selectByExample(resourceExample);
			return list.size()>0?list.get(0):null;
		}
		return null;
	}

	@Override
	public List<Chapter> findChapterByCourseId(int courseId) {
		ChapterExample chapterExample = new ChapterExample();
		chapterExample.or().andCourseIdEqualTo(courseId)
							.andStatusEqualTo(Constant.STATUS_ENABLE);
		List<Chapter> chapters = chapterMapper.selectByExample(chapterExample);
		for (Chapter chapter:chapters
			 ) {
			ResourceExample resourceExample = new ResourceExample();
			resourceExample.or().andChapterIdEqualTo(chapter.getId());
			List<Resource> list = resourceMapper.selectByExample(resourceExample);
			chapter.setResource(list.size()>0?list.get(0):null);
		}
		return chapters;
	}
}
