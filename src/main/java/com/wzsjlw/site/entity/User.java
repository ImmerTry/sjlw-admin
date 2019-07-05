package com.wzsjlw.site.entity;

import java.io.Serializable;

/**
 * 用户
 *
 * @author: ll
 * @version: 1.0 2019/07/01
 * @see:
 * @since:
 */
public class User implements Serializable {

    private static final long serialVersionUID = -5426787845352548307L;
    /**
     * 主键唯一标识
     */
    private Integer pkId;
    /**
     * 用户名/登录账号
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 用户权限
     * 0、超级管理员
     * 1、普通管理员
     * 2、其它
     */
    private Integer access;

    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 备注/说明
     */
    private String remark;


    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "pkId=" + pkId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", access=" + access +
                ", avatar='" + avatar + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
