package com.mvideo.admin.service;

import com.mvideo.admin.dal.po.Admin;
import com.mvideo.admin.dto.AdminQuery;

/**
 * Created by admin on 16/12/6.
 */
public interface IAdminService {
    Admin login(AdminQuery adminQuery) throws Exception;
}
