package com.ld.ldapp.config;

import com.ld.ldapp.domain.User;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UserConfig {

    @Bean //默认方法名
    public User user(){
        User user =new User();

        return user;
    }

//    @Bean //默认方法名
//    public JwtToken jwtToken() {
//        JwtToken jwtToken=new JwtToken();
//        return jwtToken;
//    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.parse("102400KB")); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.parse("102400KB"));
        return factory.createMultipartConfig();
    }



    @Bean(name="user1") //使用别名

    public User user1(){
        User user =new User();
        return user;
    }



}
