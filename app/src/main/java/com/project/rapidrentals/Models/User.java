package com.project.rapidrentals.Models;

public class User {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public User(String email, String userName, String password, String NIC, String phoneNumber) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.NIC = NIC;
        PhoneNumber = phoneNumber;
    }

    public User() {
    }

    String email,userName,password,NIC,PhoneNumber;
}
