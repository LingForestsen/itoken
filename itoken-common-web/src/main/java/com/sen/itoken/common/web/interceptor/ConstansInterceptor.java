package com.sen.itoken.common.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Sen
 * @Date: 2019/8/25 17:07
 * @Description: 初始化常量拦截器
 */
public class ConstansInterceptor implements HandlerInterceptor {
    private static final String HOST_ADDR = "http://192.168.161.136:81/";
    private static final String TEMPLATE_ADMINLTE = "adminlte/v2.4.3";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        //因为@RestController没有modelAndView存在
        if (modelAndView != null) {
            modelAndView.addObject("adminlte", HOST_ADDR + TEMPLATE_ADMINLTE);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
