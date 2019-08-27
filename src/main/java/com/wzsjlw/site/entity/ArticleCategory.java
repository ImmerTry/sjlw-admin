package com.wzsjlw.site.entity;

import lombok.Data;

import java.util.Date;

/**
 * ArticleCategory 实体类
 * @author: ll
 * @version: 1.0 2019-08-20
 * @see:
 * @since:
 */
@Data
public class ArticleCategory {
    /**
     * 唯一标识
     */
    private int pkId;

    /**
     * 分类标识
     */
    private int categoryId;

    /**
     * 文章标识
     */
    private int articleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
