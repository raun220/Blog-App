package com.raushan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raushan.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
