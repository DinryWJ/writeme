package com.zust.writeme.model;

import javax.persistence.*;

public class User {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private int userId;

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
     * M -男 F -女
     */
    private String sex;

    /**
     * 用户简介
     */
    @Column(name = "user_abstract")
    private String userAbstract;

    /**
     * 0为普通用户，1为管理员
     */
    @Column(name = "user_permission")
    private String userPermission;

    /**
     * 用户昵称
     */
    @Column(name = "user_name")
    private String userName;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 获取用户账号
     *
     * @return user_account - 用户账号
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 设置用户账号
     *
     * @param userAccount 用户账号
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * 获取用户密码
     *
     * @return user_password - 用户密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置用户密码
     *
     * @param userPassword 用户密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取M -男 F -女
     *
     * @return sex - M -男 F -女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置M -男 F -女
     *
     * @param sex M -男 F -女
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取用户简介
     *
     * @return user_abstract - 用户简介
     */
    public String getUserAbstract() {
        return userAbstract;
    }

    /**
     * 设置用户简介
     *
     * @param userAbstract 用户简介
     */
    public void setUserAbstract(String userAbstract) {
        this.userAbstract = userAbstract;
    }

    /**
     * 获取0为普通用户，1为管理员
     *
     * @return user_permission - 0为普通用户，1为管理员
     */
    public String getUserPermission() {
        return userPermission;
    }

    /**
     * 设置0为普通用户，1为管理员
     *
     * @param userPermission 0为普通用户，1为管理员
     */
    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    /**
     * 获取用户昵称
     *
     * @return user_name - 用户昵称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户昵称
     *
     * @param userName 用户昵称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}