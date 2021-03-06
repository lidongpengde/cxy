package com.cxy.entity;

import java.io.Serializable;
import java.util.Date;

public class AdviceBox implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advice_box.advice_box_id
     *
     * @mbggenerated
     */
    private Long adviceBoxId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advice_box.advice_content
     *
     * @mbggenerated
     */
    private String adviceContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advice_box.advice_title
     *
     * @mbggenerated
     */
    private String adviceTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advice_box.advice_user_id
     *
     * @mbggenerated
     */
    private Long adviceUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advice_box.advice_user_name
     *
     * @mbggenerated
     */
    private String adviceUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advice_box.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table advice_box
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advice_box.advice_box_id
     *
     * @return the value of advice_box.advice_box_id
     *
     * @mbggenerated
     */
    public Long getAdviceBoxId() {
        return adviceBoxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advice_box.advice_box_id
     *
     * @param adviceBoxId the value for advice_box.advice_box_id
     *
     * @mbggenerated
     */
    public void setAdviceBoxId(Long adviceBoxId) {
        this.adviceBoxId = adviceBoxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advice_box.advice_content
     *
     * @return the value of advice_box.advice_content
     *
     * @mbggenerated
     */
    public String getAdviceContent() {
        return adviceContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advice_box.advice_content
     *
     * @param adviceContent the value for advice_box.advice_content
     *
     * @mbggenerated
     */
    public void setAdviceContent(String adviceContent) {
        this.adviceContent = adviceContent == null ? null : adviceContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advice_box.advice_title
     *
     * @return the value of advice_box.advice_title
     *
     * @mbggenerated
     */
    public String getAdviceTitle() {
        return adviceTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advice_box.advice_title
     *
     * @param adviceTitle the value for advice_box.advice_title
     *
     * @mbggenerated
     */
    public void setAdviceTitle(String adviceTitle) {
        this.adviceTitle = adviceTitle == null ? null : adviceTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advice_box.advice_user_id
     *
     * @return the value of advice_box.advice_user_id
     *
     * @mbggenerated
     */
    public Long getAdviceUserId() {
        return adviceUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advice_box.advice_user_id
     *
     * @param adviceUserId the value for advice_box.advice_user_id
     *
     * @mbggenerated
     */
    public void setAdviceUserId(Long adviceUserId) {
        this.adviceUserId = adviceUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advice_box.advice_user_name
     *
     * @return the value of advice_box.advice_user_name
     *
     * @mbggenerated
     */
    public String getAdviceUserName() {
        return adviceUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advice_box.advice_user_name
     *
     * @param adviceUserName the value for advice_box.advice_user_name
     *
     * @mbggenerated
     */
    public void setAdviceUserName(String adviceUserName) {
        this.adviceUserName = adviceUserName == null ? null : adviceUserName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advice_box.create_time
     *
     * @return the value of advice_box.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advice_box.create_time
     *
     * @param createTime the value for advice_box.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advice_box
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adviceBoxId=").append(adviceBoxId);
        sb.append(", adviceContent=").append(adviceContent);
        sb.append(", adviceTitle=").append(adviceTitle);
        sb.append(", adviceUserId=").append(adviceUserId);
        sb.append(", adviceUserName=").append(adviceUserName);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}