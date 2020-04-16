package com.shopmanage.entity;

public class OrderInfo {
    private String mPayCode;
    private float mPrice;
    private int mId;
    private String mSubject;
    private String mDesc;

    public OrderInfo() {
    }

    public OrderInfo(String mPayCode, float mPrice, int mId, String mSubject, String mDesc) {
        this.mPayCode = mPayCode;
        this.mPrice = mPrice;
        this.mId = mId;
        this.mSubject = mSubject;
        this.mDesc = mDesc;
    }

    public String getmPayCode() {
        return mPayCode;
    }

    public void setmPayCode(String mPayCode) {
        this.mPayCode = mPayCode;
    }

    public float getmPrice() {
        return mPrice;
    }

    public void setmPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }
}
