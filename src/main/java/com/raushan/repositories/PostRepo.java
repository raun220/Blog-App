package com.raushan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raushan.entity.Category;
import com.raushan.entity.Post;
import com.raushan.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
}
