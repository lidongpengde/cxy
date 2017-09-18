package com.cxy.entity;

import java.io.Serializable;

public class CommonImage implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_image.image_id
     *
     * @mbggenerated
     */
    private Long imageId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_image.image_path
     *
     * @mbggenerated
     */
    private String imagePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_image.biz_id
     *
     * @mbggenerated
     */
    private String bizId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_image.uploadder_id
     *
     * @mbggenerated
     */
    private Long uploadderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_image.upload_time
     *
     * @mbggenerated
     */
    private String uploadTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_image
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_image.image_id
     *
     * @return the value of common_image.image_id
     *
     * @mbggenerated
     */
    public Long getImageId() {
        return imageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_image.image_id
     *
     * @param imageId the value for common_image.image_id
     *
     * @mbggenerated
     */
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_image.image_path
     *
     * @return the value of common_image.image_path
     *
     * @mbggenerated
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_image.image_path
     *
     * @param imagePath the value for common_image.image_path
     *
     * @mbggenerated
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_image.biz_id
     *
     * @return the value of common_image.biz_id
     *
     * @mbggenerated
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_image.biz_id
     *
     * @param bizId the value for common_image.biz_id
     *
     * @mbggenerated
     */
    public void setBizId(String bizId) {
        this.bizId = bizId == null ? null : bizId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_image.uploadder_id
     *
     * @return the value of common_image.uploadder_id
     *
     * @mbggenerated
     */
    public Long getUploadderId() {
        return uploadderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_image.uploadder_id
     *
     * @param uploadderId the value for common_image.uploadder_id
     *
     * @mbggenerated
     */
    public void setUploadderId(Long uploadderId) {
        this.uploadderId = uploadderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_image.upload_time
     *
     * @return the value of common_image.upload_time
     *
     * @mbggenerated
     */
    public String getUploadTime() {
        return uploadTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_image.upload_time
     *
     * @param uploadTime the value for common_image.upload_time
     *
     * @mbggenerated
     */
    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime == null ? null : uploadTime.trim();
    }

/*    *//**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_image
     *
     * @mbggenerated
     *//*
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
        common_image other = (common_image) that;
        return (this.getImageId() == null ? other.getImageId() == null : this.getImageId().equals(other.getImageId()))
            && (this.getImagePath() == null ? other.getImagePath() == null : this.getImagePath().equals(other.getImagePath()))
            && (this.getBizId() == null ? other.getBizId() == null : this.getBizId().equals(other.getBizId()))
            && (this.getUploadderId() == null ? other.getUploadderId() == null : this.getUploadderId().equals(other.getUploadderId()))
            && (this.getUploadTime() == null ? other.getUploadTime() == null : this.getUploadTime().equals(other.getUploadTime()));
    }

    *//**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_image
     *
     * @mbggenerated
     *//*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getImageId() == null) ? 0 : getImageId().hashCode());
        result = prime * result + ((getImagePath() == null) ? 0 : getImagePath().hashCode());
        result = prime * result + ((getBizId() == null) ? 0 : getBizId().hashCode());
        result = prime * result + ((getUploadderId() == null) ? 0 : getUploadderId().hashCode());
        result = prime * result + ((getUploadTime() == null) ? 0 : getUploadTime().hashCode());
        return result;
    }*/

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_image
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", imageId=").append(imageId);
        sb.append(", imagePath=").append(imagePath);
        sb.append(", bizId=").append(bizId);
        sb.append(", uploadderId=").append(uploadderId);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}