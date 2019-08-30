package com.sen.itoken.web.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: Sen
 * @Date: 2019/8/29 03:09
 * @Description:
 */
@Controller
public class BackendController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String main() {
        return "main";
    }

    @RequestMapping(value = "welcome",method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }
}
