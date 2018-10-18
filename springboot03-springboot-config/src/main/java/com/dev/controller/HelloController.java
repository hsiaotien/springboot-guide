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

		System.out.println("断点查看是否注入对象，是否注入属性。dataSource = " + dataSource);
		return "springboot的属性注入示例";
	}
}
