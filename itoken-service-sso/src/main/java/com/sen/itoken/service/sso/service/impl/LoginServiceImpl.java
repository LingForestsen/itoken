package com.sen.itoken.service.sso.service.impl;

import com.sen.itoken.common.domain.TbSysUser;
import com.sen.itoken.common.utils.MapperUtils;
import com.sen.itoken.service.sso.mapper.TbSysUserMapper;
import com.sen.itoken.service.sso.service.LoginService;
import com.sen.itoken.service.sso.service.consumer.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @Auther: Sen
 * @Date: 2019/8/24 03:07
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {
    //日志功能
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private TbSysUserMapper tbSysUserMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {

        TbSysUser tbSysUser = null;
        String info = redisService.get(loginCode);

        //缓存里面没数据，即没登陆
        if (info == null) {

            Example example = new Example(TbSysUser.class);
            example.createCriteria().andEqualTo("loginCode", loginCode);
            tbSysUser = tbSysUserMapper.selectOneByExample(example);
            String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());

            if (tbSysUser != null && password.equals(tbSysUser.getPassword())) {
                try {
                    //把数据放入缓存中
                    redisService.pull(loginCode, MapperUtils.obj2json(tbSysUser), 60 * 60 * 24);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return tbSysUser;
            }
        }
        //已登录缓存里面有数据
        else {
            try {
                return tbSysUser = MapperUtils.json2pojo(info, TbSysUser.class);
            } catch (Exception e) {
                logger.warn("熔断：{}",e.getMessage());
            }
        }
        return null;
    }
}
