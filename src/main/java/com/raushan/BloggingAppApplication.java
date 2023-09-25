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
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	//System.out.println("Raushan");
        // System.out.println("Raushan from Git Local Repo");

      // to apply change after git stash
      doublr x = 19.09;
      double y = 34.98;
      double sumAfterStash = x+y;

}
