package com.itany.netClass.dao;

import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.ChapterExample;
import com.itany.netClass.vo.ChapterQuery;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChapterMapper {
    int countByExample(ChapterExample example);

    int deleteByExample(ChapterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    List<Chapter> selectByExample(ChapterExample example);

    Chapter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Chapter record, @Param("example") ChapterExample example);

    int updateByExample(@Param("record") Chapter record, @Param("example") ChapterExample example);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);

    /**
     * 条件查询章节
     * @param chapterQuery
     * @return
     */
	List<Chapter> selectByCondition(ChapterQuery chapterQuery);
}