package com.sen.itoken.common.mapper;

import com.sen.itoken.common.domain.TbPostsPost;
import tk.mybatis.MyMapper;
import com.sen.itoken.common.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

@CacheNamespace(implementation = RedisCache.class)
public interface TbPostsPostMapper extends MyMapper<TbPostsPost> {
}