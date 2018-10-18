package com.dev.controller;

import com.dev.pojo.User;
import com.dev.pojo.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;

@Controller
public class HelloController {

	@Autowired
	private DataSource dataSource;

	@GetMapping("hello")
	@ResponseBody
	public String hello() {

		System.out.println("dataSource = " + dataSource);
		System.out.println("断点查看datasource的属性是否注入，以及bean是否被spring管理");

		return "springboot更为优雅的属性注入方式";
	}

	@Autowired
	private User user;

	@GetMapping("test/gps")
	@ResponseBody
	public String gps(){
		System.out.println("name=======>"+user.getName());

		return "springboot属性注入方式，相对于xml配置（没有）而言，前者有对象导航特性";
	}

}
