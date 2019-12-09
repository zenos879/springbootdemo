package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public List<User> selectUserByUsername(String username) {
		return userDao.selectUserByUsername(username);
	}
}