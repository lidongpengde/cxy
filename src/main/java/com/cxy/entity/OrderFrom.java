package com.cxy.entity;

import java.util.Date;

public class OrderFrom {
    private Integer orderId;

    private Integer lineInfoId;

    private Integer publisherId;

    private Integer subscriberId;

    private Integer orderStatus;

    private Date createTime;

    private Double lineInfoPrice;

    private String lineInfoStart;

    private String lineInfoEnd;

    private String publisherName;

    private Long publisherMobile;

    private String subscriberName;

    private Long subscriberMobile;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getLineInfoId() {
        return lineInfoId;
    }

    public void setLineInfoId(Integer lineInfoId) {
        this.lineInfoId = lineInfoId;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Integer subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getLineInfoPrice() {
        return lineInfoPrice;
    }

    public void setLineInfoPrice(Double lineInfoPrice) {
        this.lineInfoPrice = lineInfoPrice;
    }

    public String getLineInfoStart() {
        return lineInfoStart;
    }

    public void setLineInfoStart(String lineInfoStart) {
        this.lineInfoStart = lineInfoStart == null ? null : lineInfoStart.trim();
    }

    public String getLineInfoEnd() {
        return lineInfoEnd;
    }

    public void setLineInfoEnd(String lineInfoEnd) {
        this.lineInfoEnd = lineInfoEnd == null ? null : lineInfoEnd.trim();
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName == null ? null : publisherName.trim();
    }

    public Long getPublisherMobile() {
        return publisherMobile;
    }

    public void setPublisherMobile(Long publisherMobile) {
        this.publisherMobile = publisherMobile;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName == null ? null : subscriberName.trim();
    }

    public Long getSubscriberMobile() {
        return subscriberMobile;
    }

    public void setSubscriberMobile(Long subscriberMobile) {
        this.subscriberMobile = subscriberMobile;
    }
}