package com.dev.service.impl;

import com.dev.mapper.UserMapper;
import com.dev.pojo.User;
import com.dev.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserById(Long id) {
		return userMapper.queryUserById(id);
	}

	@Override
	public List<User> getListUser() {
		return userMapper.getListUser();
	}
}
