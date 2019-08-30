package com.sen.itoken.web.admin.interceptor;

import com.sen.itoken.common.domain.TbSysUser;
import com.sen.itoken.common.utils.CookieUtils;
import com.sen.itoken.common.utils.MapperUtils;
import com.sen.itoken.web.admin.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: Sen
 * @Date: 2019/8/25 19:25
 * @Description: 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cookieValue = CookieUtils.getCookieValue(request, "token");
        //cookie里面没有值就一定没有登录
        if (StringUtils.isBlank(cookieValue)) {
            response.sendRedirect("http://localhost:8503/login?url=http://localhost:8601/index");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        TbSysUser targetUser = (TbSysUser) request.getSession().getAttribute("admin");
        //未登录
        if (targetUser == null) {
            String cookieValue = CookieUtils.getCookieValue(request, "token");
            if (StringUtils.isNotBlank(cookieValue)) {
                String LoginCode = redisService.get(cookieValue);
                if (StringUtils.isNotBlank(LoginCode)) {
                    String json = redisService.get(LoginCode);
                    try {
                        targetUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        //确认已经登录
                        if (targetUser != null) {
                            //创建局部会话
                            request.getSession().setAttribute("admin", targetUser);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        //已登录
        else {
            if (modelAndView != null) {
                modelAndView.addObject("admin", targetUser);
            }
        }
        //二次确认是否登录（特别时临界点）
        if (targetUser == null) {
            try {
                response.sendRedirect("http://localhost:8503/login?url=http://localhost:8601/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
