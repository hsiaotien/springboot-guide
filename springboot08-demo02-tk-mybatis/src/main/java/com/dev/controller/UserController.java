package com.dev.controller;

import com.dev.pojo.User;
import com.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("user/{id}")
	public User getUser(@PathVariable("id")Long id) {
		return userService.selectUser(id);
	}

	@PostMapping("user")
	public void insertUser(@ModelAttribute("user") User user) {
		//不加注解@ModelAttribute，也可以直接pojo接收。意义可能在于注解有注解属性值可以指定
		userService.insertUser(user);
	}
}
