package com.example.demo.pojo;

import java.util.Date;

public class UserInfo {

	private String avatarUrl;
	private Date createTime;
	private String nickName;
	private String openId;
	private Integer registerCnt;
	private Date updateTime;

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getNickName() {
		return nickName;
	}

	public String getOpenId() {
		return openId;
	}

	public Integer getRegisterCnt() {
		return registerCnt;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setAvatar_url(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public void setCreate_time(Date createTime) {
		this.createTime = createTime;
	}

	public void setNick_name(String nickName) {
		this.nickName = nickName;
	}

	public void setOpen_id(String openId) {
		this.openId = openId;
	}

	public void setRegister_cnt(Integer registerCnt) {
		this.registerCnt = registerCnt;
	}

	public void setUpdate_time(Date updateTime) {
		this.updateTime = updateTime;
	}

}
