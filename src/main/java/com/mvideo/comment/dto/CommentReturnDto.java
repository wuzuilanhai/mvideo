package com.mvideo.comment.dto;

import com.mvideo.comment.dal.po.Comment;
import com.mvideo.comment.dal.po.CommentChild;

import java.util.List;

/**
 * Created by haibiao.zhang on 17/1/2.
 */
public class CommentReturnDto extends Comment {

    private List<CommentChild> commentChildList;

    public List<CommentChild> getCommentChildList() {
        return commentChildList;
    }

    public void setCommentChildList(List<CommentChild> commentChildList) {
        this.commentChildList = commentChildList;
    }
}
