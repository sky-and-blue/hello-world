package com.itany.netClass.dto;

import java.io.InputStream;

/**
 * DTO：Data Transfer Object 数据传输对象
 */
public class ResourceDto {
    private Integer id;
    private String title;
    private Integer costType;
    private Integer costNumber;
    private Integer fileSize;
    private String path;
    private String coverImageUrl;
    private Integer userId;

    private InputStream inputStream; // 文件的输入流
    private String fileName; // 文件名
    private String uploadPath; //文件的上传目录
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCostNumber() {
		return costNumber;
	}
	public void setCostNumber(Integer costNumber) {
		this.costNumber = costNumber;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCoverImageUrl() {
		return coverImageUrl;
	}
	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public Integer getCostType() {
		return costType;
	}
	public void setCostType(Integer costType) {
		this.costType = costType;
	}
	

    
}
