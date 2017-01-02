package com.mvideo.comment.dal.dao;

import com.mvideo.comment.dal.po.CommentChild;
import com.mvideo.common.dao.BaseMapper;

import java.util.List;

/**
 * Created by haibiao.zhang on 17/1/2.
 */
public interface CommentChildMapper extends BaseMapper<CommentChild, Integer> {

    List<CommentChild> getByCommentId(Integer commentId);

}
