package com.sen.itoken.web.backend.interceptor;


import com.sen.itoken.common.domain.TbSysUser;
import com.sen.itoken.common.utils.CookieUtils;
import com.sen.itoken.common.utils.MapperUtils;
import com.sen.itoken.web.backend.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: Sen
 * @Date: 2019/8/26 22:21
 * @Description: 文章消费者拦截器
 */
public class BackendInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cookieValue = CookieUtils.getCookieValue(request, "token");
        //cookie里面没有值肯定没有值
        if (StringUtils.isBlank(cookieValue)) {
            response.sendRedirect("http://localhost:8503/login?url=http://localhost:8603");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        TbSysUser tbSysUser = (TbSysUser) request.getSession().getAttribute("admin");
        //未登录
        if (tbSysUser == null) {
            String cookieValue = CookieUtils.getCookieValue(request, "token");
            if (StringUtils.isNotBlank(cookieValue)) {
                String LoginCode = redisService.get(cookieValue);
                if (StringUtils.isNotBlank(LoginCode)) {
                    String json = redisService.get(LoginCode);
                    try {
                        tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        //确认已经登录
                        if (tbSysUser != null) {
                            //创建局部会话
                            request.getSession().setAttribute("admin", tbSysUser);
                            if (modelAndView != null) {
                                modelAndView.addObject("admin", tbSysUser);
                            }
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
                modelAndView.addObject("admin", tbSysUser);
            }
        }
        //二次确认是否登录（特别时临界点）
        if (tbSysUser == null) {
            try {
                response.sendRedirect("http://localhost:8503/login?url=http://localhost:8603");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
