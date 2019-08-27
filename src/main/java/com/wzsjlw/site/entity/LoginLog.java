package com.wzsjlw.site.entity;

import lombok.Data;

import java.util.Date;

/**
 * 登录日志
 *
 * @author: ll
 * @version: 1.0 2019-08-20
 * @see:
 * @since:
 */
@Data
public class LoginLog {
    /**
     * 唯一标识
     */
    private int pkId;

    /**
     * 登录名称
     */
    private String userName;

    /**
     * IP
     */
    private String ip;

    /**
     * 登录地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 登录设备
     */
    private String device;

}
