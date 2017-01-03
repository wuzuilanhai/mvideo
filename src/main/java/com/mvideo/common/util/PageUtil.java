package com.mvideo.common.util;

import com.github.pagehelper.Page;
import com.mvideo.common.dto.PageResultDto;

import java.util.List;

/**
 * Created by haibiao.zhang on 17/1/3.
 */
public class PageUtil {

    public static <T> PageResultDto<T> getPageResult(List<T> list) {
        PageResultDto<T> pageResultDto = new PageResultDto<T>();
        Page page = (Page) list;
        pageResultDto.setPageNum(page.getPageNum());
        pageResultDto.setPageSize(page.getPageSize());
        pageResultDto.setStartRow(page.getStartRow());
        pageResultDto.setEndRow(page.getEndRow());
        pageResultDto.setList(list);
        pageResultDto.setTotal(page.getPages());
        return pageResultDto;
    }

}
