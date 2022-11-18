package com.ssafit.model.dto;

public class User {
    private String userId;
    private String password;
    private String userName;
    private String email;
    private String birthDate;
    private String sex;
    private String profile;

    public User() {
    }

    public User(String userId, String password, String userName, String email, String birthDate, String sex, String profile) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.birthDate = birthDate;
        this.sex = sex;
        this.profile = profile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", sex='" + sex + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}

