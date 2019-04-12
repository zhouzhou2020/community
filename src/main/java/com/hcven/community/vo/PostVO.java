package com.hcven.community.vo;

import java.util.Date;
import java.util.List;

/**
 * @author zhanghao
 * @since 2019-03-12
 */
public class PostVO {
    /**
     * 话题id
     */
    private Long id;
    /**
     * 发表日期
     */
    private Date date;
    /**
     * 用户
     */
    private UserVO userVO;
    /**
     * 可见列表
     */
    private List<Long> unVisibleId;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片列表
     */
    private List<Long> imageIds;
    /**
     * 状态 0-delete 1-normal
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public List<Long> getUnVisibleId() {
        return unVisibleId;
    }

    public void setUnVisibleId(List<Long> unVisibleId) {
        this.unVisibleId = unVisibleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Long> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<Long> imageIds) {
        this.imageIds = imageIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}