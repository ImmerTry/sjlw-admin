package com.wzsjlw.site.entity;

import lombok.Data;

import java.util.Date;

/**
 * Category 实体类
 *
 * @author: ll
 * @version: 1.0 2019-08-27
 * @see:
 * @since:
 */
@Data
public class Category {
    /**
     * 唯一标识
     */
    private int pkId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
