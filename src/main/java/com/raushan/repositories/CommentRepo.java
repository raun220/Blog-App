package com.raushan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raushan.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
