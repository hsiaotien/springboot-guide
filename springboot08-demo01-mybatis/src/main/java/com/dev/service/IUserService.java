package com.dev.service;

import com.dev.pojo.User;

import java.util.List;

public interface IUserService {

	User getUserById(Long id);

	List<User> getListUser();
}
