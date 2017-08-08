package com.taobao.demo.bean;


import cn.bmob.v3.BmobObject;

/*
* 作者：ProZoom
* 时间：2017/7/23-上午10:56
* 描述：
*/

public class H5QuestionBean extends BmobObject {

    private String id; //问题id,值越大问题难度越大

    private String question; //问题

    private String answer;  //答案

    private String option_a; //选择a

    private String option_b; //选择b

    private String option_c; //选择c

    private String option_d; //选择d


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String anwser) {
        this.answer = answer;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }
}
