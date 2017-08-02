package com.cxy.entity;

import java.util.Date;

public class Comment {
    private Integer cid;

    private Integer commenterId;

    private String commenterName;

    private Integer commenteredId;

    private String commenteredName;

    private Integer score;

    private String description;

    private Date commentTime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(Integer commenterId) {
        this.commenterId = commenterId;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName == null ? null : commenterName.trim();
    }

    public Integer getCommenteredId() {
        return commenteredId;
    }

    public void setCommenteredId(Integer commenteredId) {
        this.commenteredId = commenteredId;
    }

    public String getCommenteredName() {
        return commenteredName;
    }

    public void setCommenteredName(String commenteredName) {
        this.commenteredName = commenteredName == null ? null : commenteredName.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}