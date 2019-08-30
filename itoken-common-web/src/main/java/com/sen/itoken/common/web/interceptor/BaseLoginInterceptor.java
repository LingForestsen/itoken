package com.sen.itoken.common.web.interceptor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sen.itoken.common.domain.TbSysUser;
import com.sen.itoken.common.utils.CookieUtils;
import com.sen.itoken.common.utils.MapperUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: Sen
 * @Date: 2019/8/30 02:14
 * @Description: 登录拦截器的通用方法
 */
@Component
public class BaseLoginInterceptor {

    private static String HOST_SSO = "http://localhost:8503";

    /**
     *  拦截器前检查受否登录
     * @param request
     * @param response
     * @return
     */
    public static boolean checkPre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cookieValue = CookieUtils.getCookieValue(request, "token");
        //cookie里面没有值肯定没有值
        if (StringUtils.isBlank(cookieValue)) {
            //获取请求的地址
            String servletPath ="http://localhost:8602" + request.getServletPath();
            String url = String.format("%s/login?url=%s",HOST_SSO , servletPath);
            response.sendRedirect(url);
            return false;
        }
        return true;
    }

    /**
     * 登录拦截之之后的通用方法
     * @param request
     * @param response
     * @param modelAndView
     */
    public static void checkPro(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView,String json) throws Exception {
        TbSysUser tbSysUser = (TbSysUser) request.getSession().getAttribute("admin");
        //未登录
        if (tbSysUser == null) {
            tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
            //确认已经登录
            if (tbSysUser != null) {
                //创建局部会话
                request.getSession().setAttribute("admin", tbSysUser);
                if (modelAndView != null) {
                    modelAndView.addObject("admin", tbSysUser);
                }
            }
        }
        //已登录
        else {
            if (modelAndView != null) {
                modelAndView.addObject("admin", tbSysUser);
            }
        }
        //二次确认是否登录（特别时临界点）
        if (tbSysUser == null) {
                String servletPath ="http://localhost:8602" + request.getServletPath();
                String url = String.format("%s/login?url=%s",HOST_SSO , servletPath);
                response.sendRedirect(url);
        }
    }
}
