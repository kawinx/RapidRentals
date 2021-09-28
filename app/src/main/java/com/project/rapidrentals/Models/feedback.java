package com.project.rapidrentals.Models;

public class feedback {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public feedback(String userName, String feedback) {
        this.userName = userName;
        this.feedback = feedback;
    }

    public feedback() {
    }

    String userName,feedback;
}
