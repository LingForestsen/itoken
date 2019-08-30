package com.sen.itoken.service.admin.service.impl;

import com.sen.itoken.common.domain.TbSysUser;
import com.sen.itoken.common.mapper.TbSysUserMapper;
import com.sen.itoken.common.service.impl.BaseServiceImpl;
import com.sen.itoken.service.admin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Sen
 * @Date: 2019/8/22 19:24
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements AdminService {

}
