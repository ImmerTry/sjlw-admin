package com.wzsjlw.site.controller;

import com.wzsjlw.site.entity.Category;
import com.wzsjlw.site.service.CategoryService;
import com.wzsjlw.site.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: ll
 * @version: 1.0 2019-08-27
 * @see:
 * @since:
 */
@RestController
public class CategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/user/addCategory")
    @ApiOperation("添加分类信息")
    public ResultUtil addCategory(@RequestParam("categoryName") String categoryName) {
        LOGGER.info("分类名称：" + categoryName);
        return categoryService.addCategory(categoryName);
    }

    @PostMapping("/user/getTableData")
    @ApiOperation("填充表格数据")
    public ResultUtil getTableData(@RequestParam("pageSize") String pageSize,
                                   @RequestParam("pageIndex") String pageIndex) {
        LOGGER.info("每页显示 " + pageSize + " 条 " + ";当前第 " + pageIndex + " 页");
        List<Category> categoryList = categoryService.selectAll(Integer.parseInt(pageSize), Integer.parseInt(pageIndex));
        // 将查询到的结果封装成前台所需要的样式
        Map<String, Object> map = new HashMap<>(10);
        map.put("pageSize", pageSize);
        map.put("pageIndex", pageIndex);
        map.put("categoryList", categoryList);
        return ResultUtil.success(map);
    }

    @PostMapping("/user/updateCategory")
    public ResultUtil updateCategory(Category category) {
        return categoryService.updateCategory(category);
    }

    @PostMapping("/user/deleteCategory")
    public ResultUtil deleteCategory(@RequestParam("pkId") String pkId) {
        LOGGER.info("要删除的分类信息ID " + pkId);
        return categoryService.deleteCategory(Integer.parseInt(pkId));
    }
}
