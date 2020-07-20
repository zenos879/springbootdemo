package com.example.demo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * article_operations
 * @author 
 */
public class ArticleOperations implements Serializable {
    private Integer artId;

    private Integer collectTimes;

    private Integer viewTimes;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    public Integer getCollectTimes() {
        return collectTimes;
    }

    public void setCollectTimes(Integer collectTimes) {
        this.collectTimes = collectTimes;
    }

    public Integer getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(Integer viewTimes) {
        this.viewTimes = viewTimes;
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
        ArticleOperations other = (ArticleOperations) that;
        return (this.getArtId() == null ? other.getArtId() == null : this.getArtId().equals(other.getArtId()))
            && (this.getCollectTimes() == null ? other.getCollectTimes() == null : this.getCollectTimes().equals(other.getCollectTimes()))
            && (this.getViewTimes() == null ? other.getViewTimes() == null : this.getViewTimes().equals(other.getViewTimes()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArtId() == null) ? 0 : getArtId().hashCode());
        result = prime * result + ((getCollectTimes() == null) ? 0 : getCollectTimes().hashCode());
        result = prime * result + ((getViewTimes() == null) ? 0 : getViewTimes().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", artId=").append(artId);
        sb.append(", collectTimes=").append(collectTimes);
        sb.append(", viewTimes=").append(viewTimes);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}