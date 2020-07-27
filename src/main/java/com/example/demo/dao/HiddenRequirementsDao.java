package com.example.demo.dao;

import com.example.demo.pojo.HiddenRequirements;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HiddenRequirementsDao {

    List<HiddenRequirements> selectByPrimaryKey(String[] reqIds);

    List<HiddenRequirements> getRequirementByCatid(String[] catIds);

    List<HiddenRequirements> getRequirementsByKeywords(Map<String,Object> map);

    int getRequirementsCountByKeywords(Map<String, Object> paraMap);
}