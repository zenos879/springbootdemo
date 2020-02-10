package com.example.demo.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

public class ProjectInfo {

	private String aboutUs;
	private Integer autoId;
	private String contactUs;
	private Date createTime;
	private String homepageImg1;
	private String homepageImg2;
	private String homepageImg3;
	private Date updateTime;

	public String getAboutUs() {
		return aboutUs;
	}

	public Integer getAutoId() {
		return autoId;
	}

	public String getContactUs() {
		return contactUs;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getHomepageImg1() {
		return homepageImg1;
	}

	public String getHomepageImg2() {
		return homepageImg2;
	}

	public String getHomepageImg3() {
		return homepageImg3;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setAbout_us(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public void setAuto_id(Integer autoId) {
		this.autoId = autoId;
	}

	public void setContact_us(String contactUs) {
		this.contactUs = contactUs;
	}

	public void setCreate_time(Date createTime) {
		this.createTime = createTime;
	}

	public void setHomepage_img1(String homepageImg1) {
		this.homepageImg1 = homepageImg1;
	}

	public void setHomepage_img2(String homepageImg2) {
		this.homepageImg2 = homepageImg2;
	}

	public void setHomepage_img3(String homepageImg3) {
		this.homepageImg3 = homepageImg3;
	}

	public void setUpdate_time(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("aboutus", this.getAboutUs());
		json.put("contactus", this.getContactUs());
		json.put("autoId", this.getAutoId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		json.put("createTime", sdf.format(this.getCreateTime()));
		json.put("homepageImg1", this.getHomepageImg1());
		json.put("homepageImg2", this.getHomepageImg2());
		json.put("homepageImg3", this.getHomepageImg3());
		return json.toString();
	}

}
