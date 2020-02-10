package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProjectDao;
import com.example.demo.pojo.ProjectInfo;

import net.sf.json.JSONObject;

@Service
public class ProjectService {

	@Autowired
	ProjectDao projectDao;

	public String getHomeImage() {
		JSONObject json = new JSONObject();
		List<ProjectInfo> list = projectDao.getProjectInfo();
		String img = "无图片";
		if (list != null && list.size() > 0) {
			ProjectInfo pro = list.get(0);
			if (pro != null) {
				json.put("img1", pro.getHomepageImg1());
				json.put("img2", pro.getHomepageImg2());
				json.put("img3", pro.getHomepageImg3());
			}
			return json.toString();
		} else {
			return img;
		}
	}

	public String getAboutus() {
		List<ProjectInfo> list = projectDao.getProjectInfo();
		ProjectInfo p = list.get(0);
		JSONObject json = new JSONObject();
		json.put("aboutus", p.getAboutUs());
		json.put("contactus", p.getContactUs());
		return json.toString();

	}

	public String getProjectInfo() {
		List<ProjectInfo> list = projectDao.getProjectInfo();
		ProjectInfo p = list.get(0);
		return p.toString();

	}

}