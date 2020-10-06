package com.ld.ldapp.config;

import com.ld.ldapp.Interceptor.AdminInterceptor;
import com.ld.ldapp.Interceptor.LogInterceptor;
import com.ld.ldapp.service.impl.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /**
         *  /source/xxx   指文件的访问方式  如：localhost:8080/source/abc.wav
         *  file:d/voice/  指静态文件存放在服务器上的位置
         */
        registry.addResourceHandler("/source/**").addResourceLocations("file:"+"imgs/");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }


    @Autowired
    LogService logService;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration registration2 = registry.addInterceptor(new LogInterceptor(logService));
        registration2.addPathPatterns("/**");
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new AdminInterceptor(logService));
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "/**/login",            //登录
                "/**/tourist",
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.css",             //css静态资源
                "/**/*.woff",
                "/**/*.ttf",
                "/**/test",
                "/**/error"
//                ,
//                "/properties/**",
//                "/source/**",
//                "/banner/**",
//                "/user/**"
        );


    }

}
