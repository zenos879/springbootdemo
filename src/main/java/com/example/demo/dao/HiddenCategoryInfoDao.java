package com.example.demo.dao;

import com.example.demo.pojo.CategoryInfo;
import com.example.demo.pojo.HiddenCategoryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface HiddenCategoryInfoDao {

    HiddenCategoryInfo selectByPrimaryKey(Integer autoId);

    List<HiddenCategoryInfo> selectTopCate();

    List<HiddenCategoryInfo> selectLowerCat(int upperCatId);

    List<HiddenCategoryInfo> getLastCatgory(int upperCatId);
}