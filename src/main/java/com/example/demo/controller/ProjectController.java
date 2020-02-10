package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ProjectService;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {


	@Autowired
	ProjectService projectService;

	@Autowired
	HttpServletRequest request;

	
	@RequestMapping(value = "getProjectInfo")
	public String getProjectInfo() {
		return projectService.getProjectInfo();
	}
	@RequestMapping(value = "getHomeImage")
	public String getHomeImage() {
		String image = projectService.getHomeImage();
		return image;
	}
	
	@RequestMapping(value = "getAboutus")
	public String getAboutus() {
		String image = projectService.getAboutus();
		return image;
	}
	

}
