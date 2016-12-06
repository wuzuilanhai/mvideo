package com.mvideo.admin.service.impl;

import com.mvideo.admin.dal.dao.AdminMapper;
import com.mvideo.admin.dal.po.Admin;
import com.mvideo.admin.dto.AdminQuery;
import com.mvideo.admin.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 16/12/6.
 */
@RestController
@RequestMapping("/adminService")
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    @RequestMapping("/login")
    public Admin login(@RequestBody AdminQuery adminQuery) throws Exception {
        return adminMapper.findAdmin(adminQuery);
    }

}
