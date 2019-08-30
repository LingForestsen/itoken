package com.sen.itoken.web.admin.service;

import com.sen.itoken.web.admin.fallback.WebAdminFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: Sen
 * @Date: 2019/8/23 02:25
 * @Description:
 */
@FeignClient(value = "itoken-service-admin",fallback = WebAdminFallback.class)
public interface WebAdminService {

}
