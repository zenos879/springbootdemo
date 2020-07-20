package com.example.demo.dao;

import com.example.demo.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Mapper
@Repository
public interface UserInfoDao {

    int deleteByPrimaryKey(String openId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByPrimaryKey(String openId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}