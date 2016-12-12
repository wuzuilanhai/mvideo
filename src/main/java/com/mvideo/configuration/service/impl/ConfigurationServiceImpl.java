package com.mvideo.configuration.service.impl;

import com.mvideo.configuration.dal.dao.ConfigurationMapper;
import com.mvideo.configuration.dal.po.Configuration;
import com.mvideo.configuration.service.IConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 16/12/12.
 */
@RestController
@RequestMapping("/configuration")
public class ConfigurationServiceImpl implements IConfigurationService {

    @Autowired
    private ConfigurationMapper configurationMapper;

    @Override
    public Configuration getConfigurationByName(String name) throws Exception {
        return configurationMapper.getConfigurationByName(name);
    }
}
