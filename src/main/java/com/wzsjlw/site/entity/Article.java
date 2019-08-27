package com.wzsjlw.site.entity;

import lombok.Data;

import java.util.Date;

/**
 * Article 实体类
 *
 * @author: ll
 * @version: 1.0 2019-08-20
 * @see:
 * @since:
 */
@Data
public class Article {
    /**
     * 唯一标识
     */
    private int pkId;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图片
     */
    private String cover;

    /**
     * 作者
     */
    private String author;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布日期
     */
    private Date pushTime;

    /**
     * 浏览次数
     */
    private int views;

    /**
     * 文章分类
     */
    private String category;

    /**
     * 状态：0、未发布
     * 1、已发布
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 类型 0、原创
     * 1、转载
     */
    private int type;


}
