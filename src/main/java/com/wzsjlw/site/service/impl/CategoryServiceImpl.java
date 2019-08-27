package com.wzsjlw.site.service.impl;

import com.github.pagehelper.PageHelper;
import com.wzsjlw.site.dao.CategoryDAO;
import com.wzsjlw.site.entity.Category;
import com.wzsjlw.site.service.CategoryService;
import com.wzsjlw.site.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ll
 * @version: 1.0 2019-08-27
 * @see:
 * @since:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public ResultUtil addCategory(String categoryName) {

        try {
            int result = categoryDAO.save(categoryName);
            if (result > 0) {
                return ResultUtil.success("添加成功");
            } else {
                return ResultUtil.fail("添加失败");
            }
        } catch (Exception e) {
            return ResultUtil.fail(e.getMessage());
        }
    }

    @Override
    public List<Category> selectAll(int pageSize, int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        return categoryDAO.selectAll();
    }

    @Override
    public ResultUtil updateCategory(Category category) {
        try {
            int result = categoryDAO.update(category);
            if (result > 0) {
                return ResultUtil.success("更新成功");
            } else {
                return ResultUtil.fail("更新失败");
            }
        } catch (Exception e) {
            return ResultUtil.fail(e.getMessage());
        }
    }

    @Override
    public ResultUtil deleteCategory(int categoryId) {

        try {
            int result = categoryDAO.delete(categoryId);
            if (result > 0) {
                return ResultUtil.success("删除成功");
            } else {
                return ResultUtil.fail("删除失败");
            }
        } catch (Exception e) {
            return ResultUtil.fail(e.getMessage());
        }
    }
}
