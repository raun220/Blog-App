package com.raushan;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppApplication.class, args);
	}

// before git stash
int a =1;
int b= 4;
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	//System.out.println("Raushan");
        // System.out.println("Raushan from Git Local Repo");

// After git stash
double x=78.09;
double y=98.83;



}
