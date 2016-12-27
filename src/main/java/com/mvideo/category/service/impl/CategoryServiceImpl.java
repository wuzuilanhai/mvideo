package com.mvideo.category.service.impl;

import com.mvideo.category.dal.dao.CategoryMapper;
import com.mvideo.category.dal.po.Category;
import com.mvideo.category.service.ICategoryService;
import com.mvideo.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haibiao.zhang on 16/12/25.
 */
@RestController
@RequestMapping("category")
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @RequestMapping("getParentCategory")
    public List<Category> getParentCategorys() {
        if (redisUtil.get("parentCategory") != null) {
            return (List<Category>) redisUtil.get("parentCategory");
        }
        List<Category> categoryList = categoryDao.getParentCategorys();
        redisUtil.set("parentCategory", categoryList);
        return categoryDao.getParentCategorys();
    }

    @Override
    @RequestMapping("getChildrenCategory")
    public List<Category> getChildrenCategorys(Integer parentId) {
        if (redisUtil.get("childrenCategory") != null) {
            return (List<Category>) redisUtil.get("childrenCategory");
        }
        List<Category> categoryList = categoryDao.getChildrenCategorys(parentId);
        redisUtil.set("childrenCategory", categoryList);
        return categoryList;
    }

}
