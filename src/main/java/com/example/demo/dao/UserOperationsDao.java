package com.example.demo.dao;

import com.example.demo.pojo.UserInfo;
import com.example.demo.pojo.UserOperations;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Mapper
@Repository
public interface UserOperationsDao {
    int selectIfExistUserOperations(String openId);

    int deleteByPrimaryKey(String openId);

    int insert(UserOperations record);

    int insertSelective(UserOperations record);

    UserOperations selectByPrimaryKey(String openId);

    int updateByPrimaryKeySelective(UserOperations record);

    int updateByPrimaryKey(UserOperations record);
}