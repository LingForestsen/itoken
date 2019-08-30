package com.sen.itoken.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.itoken.common.domain.BaseDomain;
import com.sen.itoken.common.service.BaseService;
import tk.mybatis.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

/**
 * @Auther: Sen
 * @Date: 2019/8/26 00:55
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public abstract class BaseServiceImpl<T extends BaseDomain,D extends MyMapper<T>> implements BaseService<T> {
    @Autowired
    private D dao;

    @Override
    @Transactional(readOnly = false)
    public int insert(T t,String createBy) {
        t.setCreateBy(createBy);
        t.setUpdateDate(new Date());
        t.setUpdateBy(createBy);
        t.setCreateDate(new Date());
        return dao.insert(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(T t) {
        return dao.delete(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(T t,String updateBy) {
        t.setUpdateBy(updateBy);
        t.setUpdateDate(new Date());
        return dao.updateByPrimaryKey(t);
    }

    @Override
    public T selectOne(T t) {
        return dao.selectOne(t);
    }

    @Override
    public int count(T t) {
        return dao.selectCount(t);
    }

    @Override
    public PageInfo<T> findByPage(int start, int pageSize, T t) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(start, pageSize);
        PageInfo<T> pageInfo = new PageInfo<>(dao.select(t));
        return pageInfo;
    }
}
