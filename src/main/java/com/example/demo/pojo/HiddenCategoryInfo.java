package com.example.demo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * hidden_category_info
 * @author 
 */
public class HiddenCategoryInfo implements Serializable {

    /**
     * 隐患排查—分类ID
     */
    private Integer catId;

    /**
     * 隐患排查—分类名称
     */
    private String catName;

    /**
     * 隐患排查—分类级别
     */
    private Integer levelNo;

    //排序
    private Integer orderNo;

    /**
     * 隐患排查—上级分类的id
     */
    private Integer upperCatId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public Integer getUpperCatId() {
        return upperCatId;
    }

    public void setUpperCatId(Integer upperCatId) {
        this.upperCatId = upperCatId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        HiddenCategoryInfo other = (HiddenCategoryInfo) that;
        return (this.getCatId() == null ? other.getCatId() == null : this.getCatId().equals(other.getCatId()))
            && (this.getCatName() == null ? other.getCatName() == null : this.getCatName().equals(other.getCatName()))
            && (this.getLevelNo() == null ? other.getLevelNo() == null : this.getLevelNo().equals(other.getLevelNo()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getUpperCatId() == null ? other.getUpperCatId() == null : this.getUpperCatId().equals(other.getUpperCatId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCatId() == null) ? 0 : getCatId().hashCode());
        result = prime * result + ((getCatName() == null) ? 0 : getCatName().hashCode());
        result = prime * result + ((getLevelNo() == null) ? 0 : getLevelNo().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getUpperCatId() == null) ? 0 : getUpperCatId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catId=").append(catId);
        sb.append(", catName=").append(catName);
        sb.append(", levelNo=").append(levelNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", upperCatId=").append(upperCatId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}