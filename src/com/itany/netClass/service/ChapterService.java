package com.itany.netClass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.dto.ChapterDto;
import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.exception.ChapterTitleException;
import com.itany.netClass.exception.ResourceTitleException;
import com.itany.netClass.vo.ChapterQuery;

import org.apache.commons.fileupload.FileUploadException;

import java.util.List;

public interface ChapterService {
    /**
     * 添加章节
     * @param chapterDto
     * @throws ChapterTitleException 
     * @throws ResourceTitleException 
     */
    void add(ChapterDto chapterDto) throws FileUploadException, ChapterTitleException, ResourceTitleException;

    /**
     * 条件查询章节
     * @param parseInt
     * @param pageSize
     * @param chapterQuery
     * @return
     */
	PageInfo<Chapter> findByConldition(int page, int pageSize, ChapterQuery chapterQuery);

	/**
	 * 跟新状态
	 * @param chapter
	 */
	void updateStatus(Chapter chapter);

	/**
	 * 查询单个章节
	 * @param chapter
	 * @return
	 */
	Chapter singleChapter(Chapter chapter);

	/**
	 * 跟新章节
	 * @param chapterDto
	 * @throws FileUploadException 
	 * @throws ResourceTitleException 
	 * @throws ChapterTitleException 
	 */
	void update(ChapterDto chapterDto) throws FileUploadException, ResourceTitleException, ChapterTitleException;

	/**
	 * 查询单个资源
	 * @param resource
	 * @return
	 */
    Resource singleResource(Resource resource);

	/**
	 * 通过资源ID查询所有章节
	 * @param courseId
	 * @return
	 */
	List<Chapter> findChapterByCourseId(int courseId);
}
