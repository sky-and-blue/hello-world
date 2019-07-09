package com.itany.netClass.service;


import com.github.pagehelper.PageInfo;
import com.itany.netClass.dto.ResourceDto;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.exception.ResourceNameIsExistsException;
import com.itany.netClass.vo.ResourceQuery;
import org.apache.commons.fileupload.FileUploadException;

import java.util.List;

public interface ResourceService {

    /**
     * 分页条件查询资源信息
     *
     * @param page
     * @param pageSize
     * @param resourceQuery
     * @return
     */
    PageInfo<Resource> findByConldition(int page, int pageSize, ResourceQuery resourceQuery);

    /**
     * 修改状态
     *
     * @param resource
     */
    void updateStatus(Resource resource);

    /**
     * 添加资源
     *
     * @param resourceDto
     * @throws FileUploadException
     */
    void add(ResourceDto resourceDto) throws FileUploadException, ResourceNameIsExistsException;

    /**
     * 查询单个资源信息
     * @param resource
     * @return
     */
	Resource singleResource(Resource resource);

	/**
	 * 更新资源
	 * @param resourceDto
	 * @throws ResourceNameIsExistsException 
	 * @throws FileUploadException 
	 */
	void update(ResourceDto resourceDto) throws ResourceNameIsExistsException, FileUploadException;

	/**
	 * 删除资源
	 * @param resource
	 */
	void delete(Resource resource);

}

