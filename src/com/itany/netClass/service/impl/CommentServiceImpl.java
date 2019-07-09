package com.itany.netClass.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.CommentMapper;
import com.itany.netClass.dao.PraiseMapper;
import com.itany.netClass.dao.UserMapper;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.Praise;
import com.itany.netClass.entity.PraiseExample;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.PraiseIsExistException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.vo.CommentQuery;

public class CommentServiceImpl implements CommentService {

    private CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");
    private PraiseMapper praiseMapper = ObjectFactory.getObject("praiseMapper");
    private UserMapper userMapper = ObjectFactory.getObject("userMapper");

    @Override
    public PageInfo<Comment> findByConldition(int page, int pageSize, CommentQuery commentQuery) {
        PageHelper.startPage(page, pageSize);
        List<Comment> list = commentMapper.selectByCondition(commentQuery);
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
        for (Comment comment : list
        		) {
        	PraiseExample example = new PraiseExample();
        	example.or().andCommentIdEqualTo(comment.getId());
        	List<Praise> praiseList = praiseMapper.selectByExample(example);
        	comment.setPraise(praiseList);
        }
        return pageInfo;
    }

	@Override
	public void updateStatus(Comment comment) {
        commentMapper.updateByPrimaryKeySelective(comment);
	}

	@Override
	public PageInfo<Comment> findCommentByResourceIds(int page, int pageSize, String cids) {
		cids = cids.substring(0, cids.lastIndexOf(','));
		PageHelper.startPage(page, pageSize);
		List<Comment> list=commentMapper.findCommentByResourceIds(cids);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
        for (Comment comment : list
        		) {
        	PraiseExample example = new PraiseExample();
        	example.or().andCommentIdEqualTo(comment.getId());
        	List<Praise> praiseList = praiseMapper.selectByExample(example);
        	comment.setPraise(praiseList);
        }
		return pageInfo;
	}

	@Override
	public void addZan(int commentId, int userId) throws PraiseIsExistException {
		PraiseExample example = new PraiseExample();
		example.or().andCommentIdEqualTo(commentId)
					.andUserIdEqualTo(userId);
		List<Praise> list = praiseMapper.selectByExample(example);
		if(list.size()>0){
			praiseMapper.deleteByPrimaryKey(list.get(0).getId());
			throw new PraiseIsExistException("您已点赞");
		}else{
		Praise praise = new Praise();
		praise.setCommentId(commentId);
		praise.setUserId(userId);
		praise.setCreateDate(new Date());
		praiseMapper.insert(praise);
		}
	}

	@Override
	public Comment addComment(Comment comment) {
		comment.setCreateDate(new Date());
		comment.setStatus(Constant.COMMENT_STATUS_WAITING);
		commentMapper.insert(comment);
		User user = userMapper.findById(comment.getUserId());
		comment.setUser(user);
		comment.setPraise(new ArrayList<Praise>());
		return comment;
	}


}
