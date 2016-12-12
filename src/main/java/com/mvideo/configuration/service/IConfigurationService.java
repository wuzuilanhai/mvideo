package com.mvideo.configuration.service;

import com.mvideo.configuration.dal.po.Configuration;

/**
 * Created by admin on 16/12/12.
 */
public interface IConfigurationService {
    Configuration getConfigurationByName(String name) throws Exception;
}
