package com.example.demo.pojo;

import java.util.Date;

public class UserOperations {

	private String collections;
	private Date createTime;
	private String histories;
	private String openId;
	private Date updateTime;

	public String getCollections() {
		return collections;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getHistories() {
		return histories;
	}

	public String getOpenId() {
		return openId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setCollections(String collections) {
		this.collections = collections;
	}

	public void setCreate_time(Date createTime) {
		this.createTime = createTime;
	}

	public void setHistories(String histories) {
		this.histories = histories;
	}

	public void setOpen_id(String openId) {
		this.openId = openId;
	}

	public void setUpdate_time(Date updateTime) {
		this.updateTime = updateTime;
	}

}
