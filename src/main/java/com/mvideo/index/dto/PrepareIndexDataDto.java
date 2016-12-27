package com.mvideo.index.dto;

import java.util.List;

/**
 * Created by haibiao.zhang on 16/12/25.
 */
public class PrepareIndexDataDto {

    List<CategoryResultDto> categoryResultDtoList;

    public List<CategoryResultDto> getCategoryResultDtoList() {
        return categoryResultDtoList;
    }

    public void setCategoryResultDtoList(List<CategoryResultDto> categoryResultDtoList) {
        this.categoryResultDtoList = categoryResultDtoList;
    }
}
