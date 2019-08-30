package com.sen.itoken.common.service;

import com.github.pagehelper.PageInfo;
import com.sen.itoken.common.domain.BaseDomain;

/**
 * @Auther: Sen
 * @Date: 2019/8/26 00:52
 * @Description:
 */
public interface BaseService<T extends BaseDomain> {
    /**
     * 增加
     * @param t
     * @return
     */
    int insert(T t,String createBy);

    /**
     * 删除
     * @param t
     * @return
     */
    int delete(T t);

    /**
     * 更新
     * @param t
     * @return
     */
    int update(T t, String updateBy);

    /**
     * 根据id查询
     * @param t
     * @return
     */
    T selectOne(T t);

    /**
     * 查询总笔数
     * @return
     */
    int count(T t);

    /**
     * 分页查询
     *
     * @return
     */
    PageInfo<T> findByPage(int start, int pageSize, T t);
}
