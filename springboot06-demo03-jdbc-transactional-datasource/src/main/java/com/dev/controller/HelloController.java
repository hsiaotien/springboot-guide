package com.dev.controller;

import com.dev.pojo.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

	@GetMapping("hello")
	public String hello(){
		System.out.println("原理依旧是springboot的属性注入，自动配置完成配置");
		return "springboot整合jdbc,事物管理以及连接池";
	}

	@GetMapping("user/{id}")
	public User getUser(@PathVariable("id")Long id) {
		return null;
	}

	@PostMapping("user")
	public void insertUser(@ModelAttribute("user") User user) {
		//增加用户
	}
}
