package com.wzsjlw.site.dao;

import com.wzsjlw.site.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 发布文章
 * @author: ll
 * @version: 1.0 2019-08-26
 * @see:
 * @since:
 */
@Mapper
public interface IssueDAO {
    /**
     * 添加文章
     * @param article 存储文章信息 map
     * @return 受影响行数
     */
    int save(@Param("article") Article article);
}
