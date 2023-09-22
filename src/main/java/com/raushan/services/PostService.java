package com.raushan.services;


import java.util.List;



import com.raushan.payloads.PostDto;
import com.raushan.payloads.PostResponse;


public interface PostService {
	
	//create Post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update Post
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete post
	void deletePost(Integer postId);
	
	// get single post
	PostDto getPostById(Integer postId);
	
	//get all the post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//get all post of a particular category
	List<PostDto> getAllPostByCategory(Integer categoryId);
	
	// get all post by a user
	List<PostDto> getAllPostByUser(Integer userId);
	
	// search post
	List<PostDto> searchPosts(String keyword);
	
	
	

}
