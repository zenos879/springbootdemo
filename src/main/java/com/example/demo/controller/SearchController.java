package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SearchService;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	SearchService searchService;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "getCategory")
	public String getCategory() {
		String cateList = searchService.getCategory();
		return cateList;
	}

	@RequestMapping(value = "getArticleList")
	public String getArticleList() {
		String catId = request.getParameter("catId");
		String startStr = request.getParameter("start");
		int start = Integer.parseInt(startStr);
		String countStr = request.getParameter("count");
		int count = Integer.parseInt(countStr);
		String artlist = searchService.getArticleList(catId, start, count);
		return artlist;
	}

	@RequestMapping(value = "getAritlcleTitle")
	public String getAritlcleTitle() {
		String artId = request.getParameter("artId");
		String retStr = searchService.getAritlcleTitle(artId);
		return retStr;
	}

	@RequestMapping(value = "getAriticleInfo")
	public String getAriticleInfo() {
		String artId = request.getParameter("artId");
		String retStr = searchService.getAriticleInfo(artId);
		return retStr;
	}

	@RequestMapping(value = "getUpdateList")
	public String getUpdateList() {
		String startStr = request.getParameter("start");
		int start = Integer.parseInt(startStr);
		String countStr = request.getParameter("count");
		int count = Integer.parseInt(countStr);
		String retStr = searchService.getUpdateList(start, count);
		return retStr;
	}

	@RequestMapping(value = "getTotalArticles")
	public String getTotalArticles() {
		String retStr = searchService.getTotalArticles();
		return retStr;
	}

	@RequestMapping(value = "getHotTag")
	public String getHotTag() {
		String retStr = searchService.getHotTag();
		return retStr;
	}

	@RequestMapping(value = "searchTitle")
	public String searchTitle() {
		String startStr = request.getParameter("start");
		int start = Integer.parseInt(startStr);
		String countStr = request.getParameter("count");
		int count = Integer.parseInt(countStr);
		String search = request.getParameter("search");
		String retStr = searchService.searchTitle(search, start, count);
		return retStr;
	}

}
