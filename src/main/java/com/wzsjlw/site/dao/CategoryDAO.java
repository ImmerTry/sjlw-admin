package com.wzsjlw.site.dao;

import com.wzsjlw.site.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: ll
 * @version: 1.0 2019-08-27
 * @see:
 * @since:
 */
@Mapper
public interface CategoryDAO {
    /**
     * 添加分类信息
     *
     * @param categoryName 分类信息实体类
     * @return 受影响行数
     */
    int save(@Param("categoryName") String categoryName);

    /**
     * 更新分类信息
     *
     * @param category 分类信息实体类
     * @return 受影响行数
     */
    int update(@Param("category") Category category);

    /**
     * 删除分类信息
     *
     * @param categoryId 唯一标识
     * @return 受影响行数
     */
    int delete(@Param("categoryId") int categoryId);

    /**
     * 查询所有分类信息
     *
     * @return 分类信息
     */
    List<Category> selectAll();
}
