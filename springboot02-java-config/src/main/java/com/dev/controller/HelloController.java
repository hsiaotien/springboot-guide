package com.dev.controller;

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

		// 可以看到bean已经成功交给spring管理了，并且属性值也能赋值给bean
		System.out.println("断点查看dataSource,dataSource = " + dataSource);

		return "java配置替代xml, 配置类相当于配置文件，bean注解相当于xml中的bean标签";
	}
}
