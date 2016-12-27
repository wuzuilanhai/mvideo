package com.mvideo.index.service;

import com.mvideo.category.dal.po.Category;
import com.mvideo.category.service.ICategoryService;
import com.mvideo.index.dto.CategoryResultDto;
import com.mvideo.index.dto.PrepareIndexDataDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haibiao.zhang on 16/12/25.
 */
@RestController
@RequestMapping("/")
public class IndexService {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("/index")
    public PrepareIndexDataDto index() {
        PrepareIndexDataDto prepareIndexDataDto = new PrepareIndexDataDto();
        List<CategoryResultDto> categoryResultDtoList = new ArrayList<>();
        List<Category> parentCategory = categoryService.getParentCategorys();
        for (Category category : parentCategory) {
            CategoryResultDto categoryResultDto = new CategoryResultDto();
            BeanUtils.copyProperties(category, categoryResultDto);
            categoryResultDto.setCategoryList(categoryService.getChildrenCategorys(category.getId()));
            categoryResultDtoList.add(categoryResultDto);
        }


        prepareIndexDataDto.setCategoryResultDtoList(categoryResultDtoList);
        return prepareIndexDataDto;
    }

}
