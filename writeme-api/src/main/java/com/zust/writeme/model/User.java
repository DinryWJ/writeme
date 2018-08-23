package com.zust.writeme.model;

import javax.persistence.*;

public class User {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户账号
     */
    @Column(name = "user_account")
    private String userAccount;

    /**
     * 用户密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * 用户昵称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * M -男 F -女
     */
    private String sex;

    /**
     * 0为普通用户，1为管理员
     */
    @Column(name = "user_permission")
    private String userPermission;

    /**
     * 用户简介
     */
    @Column(name = "user_abstract")
    private String userAbstract;

    /**
     * 用户状态
     */
    private String status;

    @Column(name = "user_image")
    private String userImage;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    public String getUserAbstract() {
        return userAbstract;
    }

    public void setUserAbstract(String userAbstract) {
        this.userAbstract = userAbstract;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}