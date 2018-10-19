package com.dev.service;

import com.dev.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	//伪代码，注入mapper

	public User getUser(User user){
		return null;
	}

	@Transactional
	public void insert(User user){
		//伪代码
		// @Transactional注解还有多个属性可以配置，如隔离级别，传播行为，
		// 是否只读，事物管理器等等
	}
	
}
