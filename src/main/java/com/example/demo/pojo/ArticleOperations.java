package com.example.demo.pojo;

import java.util.Date;

public class ArticleOperations {

	private Integer artId;
	private Integer collectTimes;
	private Date createTime;
	private Date updateTime;
	private Integer viewTimes;

	public Integer getArtId() {
		return artId;
	}

	public Integer getCollectTimes() {
		return collectTimes;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Integer getViewTimes() {
		return viewTimes;
	}

	public void setArt_id(Integer artId) {
		this.artId = artId;
	}

	public void setCollect_times(Integer collectTimes) {
		this.collectTimes = collectTimes;
	}

	public void setCreate_time(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdate_time(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setView_times(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

}
