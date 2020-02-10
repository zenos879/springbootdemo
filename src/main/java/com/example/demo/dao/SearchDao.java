package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.CategoryInfo;
import com.example.demo.pojo.User;

@Mapper
@Repository
public interface SearchDao {
	List<User> selectUserByUsername(String username);

	List<CategoryInfo> selectTopCate();

	List<CategoryInfo> selectLowerCat(int catId);

	List<HashMap<String, String>> selectArticleList(String catId, int start, int count);

	int selectArticleCnt(String catId);

	List<HashMap<String, Object>> findArticleViewTimes(List<String> list);

	List<HashMap<String, String>> getArticleTitle(List<String> list);

	List<HashMap<String, Object>> getArticleInfo(String artId);

	List<HashMap<String, Object>> findUpdateList(String publishDate, int start, int count);

	int findUpdateListCount(String publishDate);

	int getTotalAriticles();

	List<Object> findCatIdByCatName();

	int findArticleTitleCount(String keywords);

	List<HashMap<String, String>> searchArticleTitle(String keywords, int start, int count);
}