package com.raushan.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {
	
	private Integer id;
	
	@NotEmpty
	@Size(min =4, message = "User name must be of minimum of 4 chars !")
	private String name;
	
	@Email(message = "Email address not valid")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=10, message="Password must be between 3 to 10 chars only")
	private String password;
	
	@NotEmpty
	private String about;
	
	public UserDto() {
		super();
	}

	public UserDto(Integer id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	
	

}
