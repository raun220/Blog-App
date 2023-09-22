package com.raushan.services;

import com.raushan.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
	
	CommentDto findCommentById(Integer commentId);
}
