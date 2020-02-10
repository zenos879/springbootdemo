package com.example.demo.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

public class CategoryInfo {

	private Integer catId;
	private String catName;
	private Date createTime;
	private Integer levelNo;
	private Integer upperCatId;

	public Integer getCatId() {
		return catId;
	}

	public String getCatName() {
		return catName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Integer getLevelNo() {
		return levelNo;
	}

	public Integer getUpperCatId() {
		return upperCatId;
	}

	public void setCat_id(Integer catId) {
		this.catId = catId;
	}

	public void setCat_name(String catName) {
		this.catName = catName;
	}

	public void setCreate_time(Date createTime) {
		this.createTime = createTime;
	}

	public void setLevel_no(Integer levelNo) {
		this.levelNo = levelNo;
	}

	public void setUpper_cat_id(Integer upperCatId) {
		this.upperCatId = upperCatId;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("catId", this.getCatId());
		json.put("catName", this.getCatName());
		json.put("levelNo", this.getLevelNo());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		json.put("createTime", sdf.format(this.getCreateTime()));
		json.put("upperCatId", this.getUpperCatId());

		return json.toString();
	}
}
