package com.sen.itoken.service.posts.service.impl;

import com.sen.itoken.common.domain.TbPostsPost;
import com.sen.itoken.common.mapper.TbPostsPostMapper;
import com.sen.itoken.common.service.impl.BaseServiceImpl;
import com.sen.itoken.service.posts.service.ServicePosts;
import org.springframework.stereotype.Service;

/**
 * @Auther: Sen
 * @Date: 2019/8/26 16:18
 * @Description:
 */
@Service
public class ServicePostsImpl extends BaseServiceImpl<TbPostsPost, TbPostsPostMapper> implements ServicePosts {
}
