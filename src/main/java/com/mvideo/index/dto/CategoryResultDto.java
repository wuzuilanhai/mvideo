package com.mvideo.index.dto;

import com.mvideo.category.dal.po.Category;

import java.util.List;

/**
 * Created by haibiao.zhang on 16/12/27.
 */
public class CategoryResultDto extends Category{

    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
