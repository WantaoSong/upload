package com.upload.v3.entity;


import java.io.Serializable;
import java.util.Date;

public class ImgResources implements Serializable {
    /** banner图表 */
    private Integer id;

    /** 按顺序分别为：1.个人头像--->personal_avatar,2.组织或者社团头像--->org_avatar,3.个人轮播---->personal_banner,4.--->社团轮播org_banner */
    private String type;

    /** gid组id */
    private String gid;

    /** 图片 */
    private String img;

    private String status;

    private Date createTime;

    /** 子分类 */
    private String childType;

    private String accId;

    private String accName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChildType() {
        return childType;
    }

    public void setChildType(String childType) {
        this.childType = childType;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }
}