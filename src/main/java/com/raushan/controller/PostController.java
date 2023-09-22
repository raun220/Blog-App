package com.raushan.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.raushan.payloads.ApiResponse;
import com.raushan.payloads.PostDto;
import com.raushan.payloads.PostResponse;
import com.raushan.services.FileService;
import com.raushan.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("$(project.image)")
	private String path;
	
	// create
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, 
			@PathVariable Integer userId, @PathVariable Integer categoryId) {
		
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<>(createPost, HttpStatus.CREATED);
	}
	
	// delete post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
	}
	
	// update
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer postId) {
		PostDto pDto = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(pDto, HttpStatus.OK);
	}
	
	// get all post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId){
		List<PostDto> postDtoList = this.postService.getAllPostByUser(userId);
		
		return new ResponseEntity<>(postDtoList, HttpStatus.OK);
	}
	
	// get all post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsBycategory(@PathVariable("categoryId") Integer categoryId) {
		List<PostDto> postDtoList = this.postService.getAllPostByCategory(categoryId);
		
		return new ResponseEntity<>(postDtoList, HttpStatus.OK);
	}
	
	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortBy);
		
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
	
	//get single post by id
	@GetMapping("posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
		PostDto postDto = postService.getPostById(postId);
		// note writing 'this' is not necessary
		
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	// search
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable("keyword") String keyword) {
		List<PostDto> results = this.postService.searchPosts(keyword);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}


	
	// upload file
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> upload(@RequestParam("image") MultipartFile image, 
			@PathVariable("postId") Integer postId) throws IOException{
		
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uoloadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	// method to serve files
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
