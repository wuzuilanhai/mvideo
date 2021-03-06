package com.mvideo.category.dal.dao;

import com.mvideo.category.dal.po.Category;
import com.mvideo.common.dao.BaseMapper;

import java.util.List;

/**
 * Created by admin on 16/12/5.
 */
public interface CategoryMapper extends BaseMapper<Category,Integer> {

    List<Category> getParentCategorys();

    List<Category> getChildrenCategorys(Integer parentId);

}
