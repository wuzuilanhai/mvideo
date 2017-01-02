package com.mvideo.comment.service;

import com.mvideo.comment.dto.CommentQueryDto;
import com.mvideo.comment.dto.CommentReturnDto;

import java.util.List;

/**
 * Created by haibiao.zhang on 17/1/2.
 */
public interface ICommentService {

    List<CommentReturnDto> getCommentsByVideoMsg(CommentQueryDto commentQueryDto);

}
