package com.ssafit.config;

import com.ssafit.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration resourceHandlerRegistration = registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").maxAge(3600);
    }

    // interceptor
    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 로그인 요청시 & swagger 접근  토큰 없이 허용 (다른 요청 다 막음)
<<<<<<< HEAD
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/videoapi/review/**");
=======
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/videoapi/create/**");
>>>>>>> 35a471cb5c35712cef0bf41a67436aae9e71c9d3
    }
}
