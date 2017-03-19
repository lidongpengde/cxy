package com.cxy.entity;

public class TCxyPersonalLetter {
    private String letterId;

    private String assresseeId;

    private String assressee;

    private String senderId;

    private String sender;

    private String sendTime;

    private String isanswer;

    private String lettId;

    private String state;

    private String letter;

    private String isdrop;

    public String getLetterId() {
        return letterId;
    }

    public void setLetterId(String letterId) {
        this.letterId = letterId == null ? null : letterId.trim();
    }

    public String getAssresseeId() {
        return assresseeId;
    }

    public void setAssresseeId(String assresseeId) {
        this.assresseeId = assresseeId == null ? null : assresseeId.trim();
    }

    public String getAssressee() {
        return assressee;
    }

    public void setAssressee(String assressee) {
        this.assressee = assressee == null ? null : assressee.trim();
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId == null ? null : senderId.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    public String getIsanswer() {
        return isanswer;
    }

    public void setIsanswer(String isanswer) {
        this.isanswer = isanswer == null ? null : isanswer.trim();
    }

    public String getLettId() {
        return lettId;
    }

    public void setLettId(String lettId) {
        this.lettId = lettId == null ? null : lettId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter == null ? null : letter.trim();
    }

    public String getIsdrop() {
        return isdrop;
    }

    public void setIsdrop(String isdrop) {
        this.isdrop = isdrop == null ? null : isdrop.trim();
    }
}