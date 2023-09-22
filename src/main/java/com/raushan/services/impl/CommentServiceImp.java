package com.raushan.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raushan.entity.Comment;
import com.raushan.entity.Post;
import com.raushan.exceptions.ResourceNotFoundException;
import com.raushan.payloads.CommentDto;
import com.raushan.repositories.CommentRepo;
import com.raushan.repositories.PostRepo;
import com.raushan.services.CommentService;


@Service
public class CommentServiceImp implements CommentService{


	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// create comments
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		//comment.setContent(commentDto.getContent());
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		 return this.modelMapper.map(savedComment, CommentDto.class);
	}

	// delete comment
	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
		this.commentRepo.delete(comment);
	}

	// find comment by id
	@Override
	public CommentDto findCommentById(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
		
		return this.modelMapper.map(comment, CommentDto.class);
	}
	


}
