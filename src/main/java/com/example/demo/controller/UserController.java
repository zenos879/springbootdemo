package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "user")
	public List<User> selectUser() {

		String username = request.getParameter("username");
		List<User> users = userService.selectUserByUsername(username);
		return users;
	}
}