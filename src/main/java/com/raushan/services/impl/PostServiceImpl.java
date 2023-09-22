package com.raushan.services.impl;


import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.raushan.entity.Category;
import com.raushan.entity.Post;
import com.raushan.entity.User;
import com.raushan.exceptions.ResourceNotFoundException;

import com.raushan.payloads.PostDto;
import com.raushan.payloads.PostResponse;
import com.raushan.repositories.CategoryRepo;
import com.raushan.repositories.PostRepo;
import com.raushan.repositories.UserRepo;

import com.raushan.services.PostService;


@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo  categoryRepo;


	
	//create
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		// DTO will provide 'title' and 'content'
		
		Post savedPost = this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	//update post
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		
		post.setImageName(postDto.getImageName());
		//post.setAddDate(new Date());   // date will be created date
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		//post.setCategory(postDto.getCategory());
		//post.setUser(postDto.getUser());
		
		Post updatedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
		
	}

	// delete post
	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		this.postRepo.delete(post);

	}

	//get single the post
	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	//get all the post
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> pagePost = this.postRepo.findAll(pageable);
		
		List<Post> allPosts = pagePost.getContent();
		
		List<PostDto> postDtos = allPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastpage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> postList = this.postRepo.findByCategory(cat);
		List<PostDto> postDtoList = postList.stream().map(post-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtoList;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		List<Post> postList = this.postRepo.findByUser(user);
		List<PostDto> postDtoList = postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return postDtos;
	}

	
	
	

}
