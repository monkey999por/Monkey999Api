package com.monkey999;

import com.monkey999.validate.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WevMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    CommonValidator commonValidator;

    // インターセプタの登録
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonValidator).addPathPatterns("/**");
    }
}
