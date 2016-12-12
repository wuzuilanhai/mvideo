package com.mvideo.configuration.dal.dao;

import com.mvideo.common.dao.BaseMapper;
import com.mvideo.configuration.dal.po.Configuration;

/**
 * Created by admin on 16/12/5.
 */
public interface ConfigurationMapper extends BaseMapper<Configuration, Integer> {
    Configuration getConfigurationByName(String name) throws Exception;
}
