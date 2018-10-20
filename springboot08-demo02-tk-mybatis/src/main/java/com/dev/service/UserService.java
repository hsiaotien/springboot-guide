package com.dev.service;

import com.dev.mapper.UserMapper;
import com.dev.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User selectUser(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public void insertUser(User user) {

		userMapper.insert(user);
		//事务管理测试
//		int i = 1/0;
	}
}
