package com.sen.itoken.web.admin.controller;

import com.sen.itoken.web.admin.service.WebAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: Sen
 * @Date: 2019/8/23 02:38
 * @Description: 登录
 */
@Controller
public class WebAdminController {

    @Autowired
    private WebAdminService webAdminService;

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
