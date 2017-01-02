package com.mvideo.comment.service.impl;

import com.github.pagehelper.PageHelper;
import com.mvideo.comment.dal.dao.CommentChildMapper;
import com.mvideo.comment.dal.dao.CommentMapper;
import com.mvideo.comment.dal.po.Comment;
import com.mvideo.comment.dal.po.CommentChild;
import com.mvideo.comment.dto.CommentQueryDto;
import com.mvideo.comment.dto.CommentReturnDto;
import com.mvideo.comment.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by haibiao.zhang on 17/1/2.
 */
@RestController
@RequestMapping("/comment")
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentChildMapper commentChildMapper;

    @RequestMapping("/addComment")
    public Object addComment(Comment comment) {
        Map<String, String> message = new HashMap<>();
        comment.setSubTime(new Date());
        if (commentMapper.insert(comment) > 0) {
            message.put("status", "success");
        } else {
            message.put("status", "fail");
        }
        return message;
    }

    @RequestMapping("/addCommentChild")
    public Object addCommentChild(CommentChild commentChild) {
        Map<String, String> message = new HashMap<>();
        Comment comment = new Comment();
        comment.setId(commentChild.getParentId());
        comment.setHasChild(1);
        if (commentChildMapper.insert(commentChild) > 0 && commentMapper.update(comment) > 0) {
            message.put("status", "success");
        } else {
            message.put("status", "fail");
        }
        return message;
    }

    @Override
    @RequestMapping("/getCommentsByVideoMsg")
    public List<CommentReturnDto> getCommentsByVideoMsg(CommentQueryDto commentQueryDto) {
        List<CommentReturnDto> commentReturnDtoList = new ArrayList<>();
        PageHelper.startPage(commentQueryDto.getPageNum(), commentQueryDto.getPageSize());
        List<Comment> comments = commentMapper.getCommentsByVideoMsg(commentQueryDto.getVideoId());
        for (Comment comment : comments) {
            CommentReturnDto commentReturnDto = new CommentReturnDto();
            BeanUtils.copyProperties(comment, commentReturnDto);
            if (comment != null && comment.getHasChild() == 1) {
                List<CommentChild> commentChildren = commentChildMapper.getByCommentId(comment.getId());
                commentReturnDto.setCommentChildList(commentChildren);
            }
            commentReturnDtoList.add(commentReturnDto);
        }
        return commentReturnDtoList;
    }

}
