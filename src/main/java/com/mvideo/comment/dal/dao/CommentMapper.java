package com.mvideo.comment.dal.dao;

import com.mvideo.comment.dal.po.Comment;
import com.mvideo.comment.dto.CommentQueryDto;
import com.mvideo.common.dao.BaseMapper;

import java.util.List;

/**
 * Created by admin on 16/12/5.
 */
public interface CommentMapper extends BaseMapper<Comment, Integer> {

    List<Comment> getCommentsByVideoMsg(Integer videoId);

}
