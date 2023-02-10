package com.monkey999.validate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * https://b1san-blog.com/post/spring/spring-interceptor/
 */
@Component
public class CommonValidator implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("pre hander");
        System.out.println(request.getServletPath());

        return true;
    }


}
