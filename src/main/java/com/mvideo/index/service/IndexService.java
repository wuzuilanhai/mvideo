package com.mvideo.index.service;

import com.mvideo.category.dal.po.Category;
import com.mvideo.category.service.ICategoryService;
import com.mvideo.common.dto.PageQueryDto;
import com.mvideo.index.dto.CategoryResultDto;
import com.mvideo.index.dto.PrepareIndexDataDto;
import com.mvideo.video.dal.po.Video;
import com.mvideo.video.service.IVideoService;
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

    @Autowired
    private IVideoService videoService;

    @RequestMapping("/index")
    public PrepareIndexDataDto index(PageQueryDto pageQueryDto) {
        PrepareIndexDataDto prepareIndexDataDto = new PrepareIndexDataDto();

        List<CategoryResultDto> categoryResultDtoList = new ArrayList<>();
        List<Category> parentCategory = categoryService.getParentCategorys();
        for (Category category : parentCategory) {
            CategoryResultDto categoryResultDto = new CategoryResultDto();
            BeanUtils.copyProperties(category, categoryResultDto);
            categoryResultDto.setCategoryList(categoryService.getChildrenCategorys(category.getId()));
            categoryResultDtoList.add(categoryResultDto);
        }

        List<Video> recentlyVideos = videoService.getRecentlyVideos(pageQueryDto);

        List<Video> onlineVideos = videoService.getOnlineVideos();

        List<Video> upcomingChannels = videoService.getOnUpcomingChannels();

        prepareIndexDataDto.setRecentlyVideos(recentlyVideos);
        prepareIndexDataDto.setOnlineVideos(onlineVideos);
        prepareIndexDataDto.setUpcomingChannels(upcomingChannels);
        prepareIndexDataDto.setCategoryResultDtoList(categoryResultDtoList);
        return prepareIndexDataDto;
    }

}
