package com.example.demo.dao;

import com.example.demo.pojo.ArticleOperations;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ArticleOperationsDao {
    int deleteByPrimaryKey(Integer artId);

    int insert(ArticleOperations record);

    int insertSelective(ArticleOperations record);

    ArticleOperations selectByPrimaryKey(Integer artId);

    int updateByPrimaryKeySelective(ArticleOperations record);

    int updateByPrimaryKey(ArticleOperations record);

    int selectIfExistArtOperations(String art_id);

}