package com.test.mvideo.Test;

import com.mvideo.MVideoApplication;
import com.mvideo.admin.dal.dao.AdminMapper;
import com.mvideo.admin.dal.po.Admin;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by admin on 16/12/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MVideoApplication.class)
public class TestCRUD {

    @Autowired
    AdminMapper adminMapper;

    @org.junit.Test
    public void testAdd() {
        Admin admin = new Admin();
        admin.setLoginName("admin");
        admin.setLoginPassword("admin");
        System.out.println(adminMapper.insert(admin));
    }

    @org.junit.Test
    public void testFind() {
        Admin admin = adminMapper.selectByPrimaryKey(4);
        System.out.println(admin);
    }

    @org.junit.Test
    public void testUpdate() {
        Admin admin = adminMapper.selectByPrimaryKey(4);
        admin.setLoginName("root");
        admin.setLoginPassword("root");
        System.out.println(adminMapper.update(admin));
    }

    @org.junit.Test
    public void testDelete() {
        System.out.println(adminMapper.deleteByPrimaryKey(4));
    }

}
