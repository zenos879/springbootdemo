package com.example.demo.pojo;

import java.util.Date;

public class ArticleInfo {

	private Integer artId;
	private String artNo;
	private Date createTime;
	private String filePath;
	private Date implDate;
	private Date publishDate;
	private String title;

	public Integer getArtId() {
		return artId;
	}

	public String getArtNo() {
		return artNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getFilePath() {
		return filePath;
	}

	public Date getImplDate() {
		return implDate;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public String getTitle() {
		return title;
	}

	public void setArt_id(Integer artId) {
		this.artId = artId;
	}

	public void setArt_no(String artNo) {
		this.artNo = artNo;
	}

	public void setCreate_time(Date createTime) {
		this.createTime = createTime;
	}

	public void setFile_path(String filePath) {
		this.filePath = filePath;
	}

	public void setImpl_date(Date implDate) {
		this.implDate = implDate;
	}

	public void setPublish_date(Date publishDate) {
		this.publishDate = publishDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
