package com.dadao.feedback.entity;

/**
 * 意见反馈展示类
 *
 * @auther NFY niufuyang
 * @create 2018-1-10
 */
public class FeedbackEntity extends FeedbackPO {
    //处理人名称
    private String treatUserName;
    //投诉用户电话
    private String userPhone;
    //投诉人昵称
    private String nickname;

    public String getTreatUserName() {
        return treatUserName;
    }

    public void setTreatUserName(String treatUserName) {
        this.treatUserName = treatUserName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "FeedbackEntity{" +
                "treatUserName='" + treatUserName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
