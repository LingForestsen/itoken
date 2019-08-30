package com.sen.itoken.service.sso.service;

import com.sen.itoken.common.domain.TbSysUser;

/**
 * @Auther: Sen
 * @Date: 2019/8/24 03:05
 * @Description:
 * 登录业务提供者
 */
public interface LoginService {
    /**
     * 登录
     * @param loginCode
     * @param plantPassword
     * @return
     */
    TbSysUser login(String loginCode, String plantPassword);
}
