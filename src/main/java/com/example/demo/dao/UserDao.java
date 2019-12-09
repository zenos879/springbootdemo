package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.User;

@Mapper
@Repository
public interface UserDao {
	List<User> selectUserByUsername(String username);
}