package com.itany.netClass.dao;

import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.CommentExample;
import java.util.List;

import com.itany.netClass.vo.CommentQuery;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper {
    int countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 条件查询评论
     * @param commentQuery
     * @return
     */
    List<Comment> selectByCondition(CommentQuery commentQuery);

    /**
     * 根据ids查询评论
     * @param cids
     * @return
     */
	List<Comment> findCommentByResourceIds(@Param("cids") String cids);
}