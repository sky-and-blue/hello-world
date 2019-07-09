package com.itany.netClass.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.exception.PraiseIsExistException;
import com.itany.netClass.vo.CommentQuery;

public interface CommentService {
    /**
     * 条件查询评论
     * @param page
     * @param pageSize
     * @param commentQuery
     * @return
     */
    PageInfo<Comment> findByConldition(int page, int pageSize, CommentQuery commentQuery);

    /**
     * 跟新评论状态
     * @param comment
     */
    void updateStatus(Comment comment);

    /**
     * 根据id查询评论
     * @param page 
     * @param pageSize 
     * @param cids
     * @return
     */
    PageInfo<Comment> findCommentByResourceIds(int page,int pageSize, String cids);

    /**
     * 给评论点赞
     * @param parseInt
     * @param parseInt2
     * @throws PraiseIsExistException 
     */
	void addZan(int commentId, int userId) throws PraiseIsExistException;

	/**
	 * 添加一个评论
	 * @param comment
	 * @return
	 */
	Comment addComment(Comment comment);


}
