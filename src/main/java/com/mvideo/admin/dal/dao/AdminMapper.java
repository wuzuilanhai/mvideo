package com.mvideo.admin.dal.dao;

import com.mvideo.admin.dal.po.Admin;
import com.mvideo.admin.dto.AdminQuery;
import com.mvideo.common.dao.BaseMapper;

/**
 * Created by admin on 16/12/5.
 */
public interface AdminMapper extends BaseMapper<Admin,Integer> {
    Admin findAdmin(AdminQuery adminQuery) throws Exception;
}
