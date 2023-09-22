package com.raushan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.raushan.payloads.ApiResponse;
import com.raushan.payloads.CommentDto;
import com.raushan.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	//create comment
	@PostMapping("/comment/postId/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Integer postId){
		
		CommentDto commentCreated = this.commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(commentCreated, HttpStatus.CREATED);
		
	}
	
	//delete comment
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId) {
		this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successufully", true), HttpStatus.OK);
	}
	
	// get comment by id
	@GetMapping("/comment/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable("commentId") Integer commentId){
		CommentDto commentDto = this.commentService.findCommentById(commentId);
		
		return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
	}

}
