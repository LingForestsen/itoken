package com.sen.itoken.web.posts.fallback;

import com.sen.itoken.common.hystrix.Fallback;
import com.sen.itoken.web.posts.service.WebPostsService;
import org.springframework.stereotype.Component;

/**
 * @Auther: Sen
 * @Date: 2019/8/26 23:51
 * @Description: 熔断方法
 */
@Component
public class WebPostsFallback implements WebPostsService {
    @Override
    public String page(int pageNum, int pageSize, String tbPostsPostJson) {
        return Fallback.BadGateway();
    }

    @Override
    public String getPostsById(String postGuid) {
        return Fallback.BadGateway();
    }

    @Override
    public String save(String tbPostsJson, String optsBy) {
        return Fallback.BadGateway();
    }
}
