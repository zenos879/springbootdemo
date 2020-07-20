package com.example.demo.dao;

import com.example.demo.pojo.HiddenRequirements;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HiddenRequirementsDao {
    int deleteByPrimaryKey(Integer autoId);

    int insert(HiddenRequirements record);

    int insertSelective(HiddenRequirements record);

    List<HiddenRequirements> selectByPrimaryKey(String[] reqIds);

    int updateByPrimaryKeySelective(HiddenRequirements record);

    int updateByPrimaryKey(HiddenRequirements record);

    List<HiddenRequirements> getRequirementByCatid(String[] catIds);

    List<HiddenRequirements> getRequirementsByKeywords(Map<String,Object> map);

    int getRequirementsCountByKeywords(Map<String, Object> paraMap);
}