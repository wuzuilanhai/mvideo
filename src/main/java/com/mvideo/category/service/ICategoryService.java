package com.mvideo.category.service;

import com.mvideo.category.dal.po.Category;

import java.util.List;

/**
 * Created by haibiao.zhang on 16/12/25.
 */
public interface ICategoryService {

    List<Category> getParentCategorys();

    List<Category> getChildrenCategorys(Integer parentId);

}
