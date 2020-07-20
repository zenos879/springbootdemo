package com.example.demo.dao;

import com.example.demo.pojo.CategoryInfo;
import com.example.demo.pojo.HiddenCategoryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface HiddenCategoryInfoDao {
    int deleteByPrimaryKey(Integer autoId);

    int insert(HiddenCategoryInfo record);

    int insertSelective(HiddenCategoryInfo record);

    HiddenCategoryInfo selectByPrimaryKey(Integer autoId);

    int updateByPrimaryKeySelective(HiddenCategoryInfo record);

    int updateByPrimaryKey(HiddenCategoryInfo record);

    List<HiddenCategoryInfo> selectTopCate();

    List<HiddenCategoryInfo> selectLowerCat(int upperCatId);

    List<HiddenCategoryInfo> getLastCatgory(int upperCatId);
}