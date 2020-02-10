package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.ProjectInfo;

@Mapper
@Repository
public interface ProjectDao {
	List<ProjectInfo> getProjectInfo();
	
}