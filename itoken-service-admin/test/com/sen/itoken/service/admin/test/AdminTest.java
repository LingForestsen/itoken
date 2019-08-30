package com.sen.itoken.service.admin.test;

import com.sen.itoken.service.admin.ServiceAdminApplication;
import com.sen.itoken.service.admin.domain.TbSysUser;
import com.sen.itoken.service.admin.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: Sen
 * @Date: 2019/8/22 19:13
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAdminApplication.class)
@ActiveProfiles(value = "prod")
@Transactional
@Rollback
public class AdminTest {
    @Autowired
    private AdminService adminService;
    @Test
    public void register() {
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUserCode(UUID.randomUUID().toString());
        tbSysUser.setLoginCode("sen@qq.com");
        tbSysUser.setUserName("sen");
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        tbSysUser.setPassword(password);
        tbSysUser.setUserType("0");
        tbSysUser.setMgrType("1");
        tbSysUser.setStatus("0");
        tbSysUser.setCreateBy("sen");
        tbSysUser.setCreateDate(new Date());
        tbSysUser.setUpdateDate(new Date());
        tbSysUser.setCreateBy("0");
        tbSysUser.setCorpName("iToken");
        tbSysUser.setCorpCode("0");
        tbSysUser.setUpdateBy("sen");
        adminService.Register(tbSysUser);
    }

    @Test
    public void login() {
        TbSysUser tbSysUser = adminService.login("sen@qq.com", "123456");
        Assert.assertNotNull(tbSysUser);
    }
}
