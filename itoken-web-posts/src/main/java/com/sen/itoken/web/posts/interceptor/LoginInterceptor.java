package com.sen.itoken.web.posts.interceptor;


import com.sen.itoken.common.utils.CookieUtils;
import com.sen.itoken.common.web.interceptor.BaseLoginInterceptor;
import com.sen.itoken.web.posts.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Sen
 * @Date: 2019/8/26 22:21
 * @Description: 文章消费者拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return BaseLoginInterceptor.checkPre(request, response);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String cookieValue = CookieUtils.getCookieValue(request, "token");
        if (StringUtils.isNotBlank(cookieValue)) {
            String LoginCode = redisService.get(cookieValue);
            if (StringUtils.isNotBlank(LoginCode)) {
                BaseLoginInterceptor.checkPro(request, response, modelAndView, redisService.get(LoginCode));
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
