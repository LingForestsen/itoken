package com.sen.itoken.service.sso.controller;

import com.sen.itoken.common.domain.TbSysUser;
import com.sen.itoken.common.utils.CookieUtils;
import com.sen.itoken.common.utils.MapperUtils;
import com.sen.itoken.service.sso.service.LoginService;
import com.sen.itoken.service.sso.service.consumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Auther: Sen
 * @Date: 2019/8/24 03:52
 * @Description: 微服务单点登录业务
 */
@Controller
public class SSOController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private LoginService loginService;

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model,@RequestParam(required = false) String url) {
        String cookieValue = CookieUtils.getCookieValue(request, "token");
        //cookie的值不为空，可能已经登录
        if (StringUtils.isNotBlank(cookieValue)) {
            String loginCode = redisService.get(cookieValue);
            String json = redisService.get(loginCode);
            //验证是否真的已经登录
            if (StringUtils.isNotBlank(json)) {
                try {
                    model.addAttribute("tbSysUser", MapperUtils.json2pojo(json, TbSysUser.class));
                    if (StringUtils.isNotBlank(url)) {
                        return "redirect:" + url;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        if (StringUtils.isNotBlank(url)) {
            model.addAttribute("url", url);
        }
        return "login";
    }

    /**
     * 登录业务
     *
     * @param loginCode
     * @param plantPassword
     * @param url 用户访问的目标地址
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String loginCode, @RequestParam(required = false)String url,
                        String plantPassword, HttpServletRequest request, HttpServletResponse response,
                        RedirectAttributes redirectAttributes) {

        TbSysUser tbSysUser = loginService.login(loginCode, plantPassword);
        //登录成功
        if (tbSysUser != null) {
            String token = UUID.randomUUID().toString();
            String info = redisService.pull(token, loginCode, 60 * 60 * 24);
            //ok成功操作否则就熔断了
            if (info.equals("ok")) {
                CookieUtils.setCookie(request, response, "token", token,60 * 60 * 24);
                if (StringUtils.isNotBlank(url)) {
                    //重定向会目标地址
                    return "redirect:" + url;
                }
            }
            //操作失败，触发熔断
            else {
                redirectAttributes.addFlashAttribute("failMessage", "服务器内部发生错误，请稍后再试");
                return "redirect:/login";
            }
        }
        //登录失败
        else {
            redirectAttributes.addFlashAttribute("failMessage", "用户名或密码有误，登录失败");
        }
        return "redirect:/login";
    }

    /**
     * 注销
     * @param request
     * @param url
     * @return
     */
    @RequestMapping(value = "loginout",method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request,HttpServletResponse response,
                           @RequestParam(required = false) String url) {
        CookieUtils.deleteCookie(request,response,"token");
        if (StringUtils.isNotBlank(url)) {
            return "redirect:/login?url=" + url;
        }
        return "redirect:/login";
    }
}
