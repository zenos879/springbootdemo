package com.example.demo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * user_operations
 *
 * @author
 */
public class UserOperations implements Serializable {
    private String openId;

    /**
     * 文章的收藏
     */
    private String collections;

    /**
     * 隐患排查的搜藏
     */
    private String hCollections;

    /**
     * 隐患排查的点赞
     */
    private String hAgree;

    /**
     * 文章的点开历史
     */
    private String histories;

    private Date updateTime;

    private Date createTime;

    private Integer vipLeftDay;

    private static final long serialVersionUID = 1L;

    public UserOperations() {

    }

    public UserOperations(String openId, String collections, String histories, String hCollections, String hAgree, int vipLeftDay, Date updateTime, Date createTime) {
        this.openId = openId;
        this.collections = collections;
        this.histories = histories;
        this.hAgree = hAgree;
        this.hCollections = hCollections;
        this.vipLeftDay = vipLeftDay;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public UserOperations(String openId, int vip_left_day, Date createTime) {
        this.openId = openId;
        this.vipLeftDay = vipLeftDay;
        this.createTime = createTime;
    }

    public UserOperations(String openId, Date updateTime) {
        this.openId = openId;
        this.updateTime = updateTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCollections() {
        return collections;
    }

    public void setCollections(String collections) {
        this.collections = collections;
    }

    public String gethCollections() {
        return hCollections;
    }

    public void sethCollections(String hCollections) {
        this.hCollections = hCollections;
    }

    public String gethAgree() {
        return hAgree;
    }

    public void sethAgree(String hAgree) {
        this.hAgree = hAgree;
    }

    public String getHistories() {
        return histories;
    }

    public void setHistories(String histories) {
        this.histories = histories;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVipLeftDay() {
        return vipLeftDay;
    }

    public void setVipLeftDay(Integer vipLeftDay) {
        this.vipLeftDay = vipLeftDay;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserOperations other = (UserOperations) that;
        return (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
                && (this.getCollections() == null ? other.getCollections() == null : this.getCollections().equals(other.getCollections()))
                && (this.gethCollections() == null ? other.gethCollections() == null : this.gethCollections().equals(other.gethCollections()))
                && (this.gethAgree() == null ? other.gethAgree() == null : this.gethAgree().equals(other.gethAgree()))
                && (this.getHistories() == null ? other.getHistories() == null : this.getHistories().equals(other.getHistories()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getVipLeftDay() == null ? other.getVipLeftDay() == null : this.getVipLeftDay().equals(other.getVipLeftDay()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getCollections() == null) ? 0 : getCollections().hashCode());
        result = prime * result + ((gethCollections() == null) ? 0 : gethCollections().hashCode());
        result = prime * result + ((gethAgree() == null) ? 0 : gethAgree().hashCode());
        result = prime * result + ((getHistories() == null) ? 0 : getHistories().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getVipLeftDay() == null) ? 0 : getVipLeftDay().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", openId=").append(openId);
        sb.append(", collections=").append(collections);
        sb.append(", hCollections=").append(hCollections);
        sb.append(", hAgree=").append(hAgree);
        sb.append(", histories=").append(histories);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", vipLeftDay=").append(vipLeftDay);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}