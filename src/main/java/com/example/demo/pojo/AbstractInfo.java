package com.example.demo.pojo;

import java.util.Date;

public class AbstractInfo {

	private Integer artId;
	private Integer autoId;
	private Integer catId;
	private Date createTime;

	public Integer getArtId() {
		return artId;
	}

	public Integer getAutoId() {
		return autoId;
	}

	public Integer getCatId() {
		return catId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setArt_id(Integer artId) {
		this.artId = artId;
	}

	public void setAuto_id(Integer autoId) {
		this.autoId = autoId;
	}

	public void setCat_id(Integer catId) {
		this.catId = catId;
	}

	public void setCreate_time(Date createTime) {
		this.createTime = createTime;
	}

}
