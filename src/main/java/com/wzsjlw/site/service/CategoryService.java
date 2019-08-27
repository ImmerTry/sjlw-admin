package com.wzsjlw.site.service;

import com.wzsjlw.site.entity.Category;
import com.wzsjlw.site.utils.ResultUtil;

import java.util.List;

/**
 * @author: ll
 * @version: 1.0 2019-08-27
 * @see:
 * @since:
 */
public interface CategoryService {
    /**
     * 添加分类信息
     *
     * @param categoryName
     * @return
     */
    ResultUtil addCategory(String categoryName);

    /**
     * 根据条件查询所有分类信息
     *
     * @param pageSize  每页多少条
     * @param pageIndex 当前第几页
     * @return 分类信息
     */
    List<Category> selectAll(int pageSize, int pageIndex);

    /**
     * 更新分类信息
     * @param category 分类信息实体类
     * @return 返回更新结果
     */
    ResultUtil updateCategory(Category category);

    /**
     * 删除分类信息
     * @param categoryId 唯一标识
     * @return 返回结果集
     */
    ResultUtil deleteCategory(int categoryId);
}
