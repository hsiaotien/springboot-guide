package com.dev.controller;

import com.dev.pojo.User;
import com.dev.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("user/{id}")
	public User getUser(@PathVariable("id")Long id) {

		return userService.getUserById(id);
	}

	@GetMapping("user")
	public List<User> getListUser() {

		return userService.getListUser();
	}
}
