package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserInfo;

@Mapper
@Repository
public interface UserDao {
	List<User> selectUserByUsername(String username);

	List<UserInfo> selectNicknameByOpenid(String openId);

	int selectIfExit(String openId);

	void updateUser(UserInfo user);

	HashMap<String, String> queryOperations(UserInfo user);

	void insertUserInfo(UserInfo user);

	void updateUserOperations(String collections, String update_time, String open_id);

	void insertUserOperations(String collections, String histories, int vip_left_day, String update_time,
			String create_time, String open_id);

	int selectIfExistUserOperations(String openId);

	void updateUserHistories(String histories, String update_time, String open_id);

	int selectIfExistArtOperations(String art_id);

	void updateArticleOperations(String art_id, String update_time);

	void insertArticleOperations(String art_id, int collection_times, int view_times, String update_time,
			String create_time);

	HashMap<String, Object> selectUserVipday(String openId);

	void updateUserVipday(String openId);

	void updateUserCreatetimeAndVip(int default_vip_left_day, String currentTime, String openId);
}