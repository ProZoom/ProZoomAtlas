package com.taobao.demo.bean;


import cn.bmob.v3.BmobObject;

/**
 * Created by liyang on 2017/7/16.
 */

public class userBean extends BmobObject {

    private String PhoneNumber; //选填的电话号码
    private String annex;  //附件

    private String feedback; //用户反馈的建议

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
