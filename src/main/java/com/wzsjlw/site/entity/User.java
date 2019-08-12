package com.wzsjlw.site.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 *
 * @author: ll
 * @version: 1.0 2019/07/01
 * @see:
 * @since:
 */
public  @Data class  User implements Serializable {

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
     * 0、超级管理员 admin
     * 1、普通管理员 user
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
}
