package com.sen.itoken.web.backend.service;

import com.sen.itoken.common.web.service.BaseClientService;
import com.sen.itoken.web.backend.fallback.WebPostsFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: Sen
 * @Date: 2019/8/26 23:50
 * @Description:
 */
@FeignClient(value = "itoken-service-posts",fallback = WebPostsFallback.class)
public interface WebPostsService extends BaseClientService {
    @RequestMapping(value = "v1/posts/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
     String page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "tbPostsPostJson") String tbPostsPostJson
    );

    @RequestMapping(value = "v1/psots/{postGuid}", method = RequestMethod.GET)
    String getPostsById(@PathVariable(value = "postGuid") String postGuid);

    @RequestMapping(value = "v1/posts",method = RequestMethod.POST)
    String save(@RequestParam(value = "tbPostsJson") String tbPostsJson,
                @RequestParam(value = "optsBy") String optsBy);
}
