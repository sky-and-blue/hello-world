package com.itany.netClass.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.netClass.entity.Course;

import java.io.InputStream;
import java.util.Date;

/**
 * DTO：Data Transfer Object 数据传输对象
 */
public class CourseDto {
    private Integer id;
    private String courseName;

    private String courseInfo;

    private String author;
    private String coverImageUrl;
    private Integer recommendationGrade;

    private Integer courseTypeId;

    private InputStream inputStream; // 文件的输入流
    private String fileName; // 文件名
    private String uploadPath; //文件的上传目录

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Integer getRecommendationGrade() {
        return recommendationGrade;
    }

    public void setRecommendationGrade(Integer recommendationGrade) {
        this.recommendationGrade = recommendationGrade;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
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

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
