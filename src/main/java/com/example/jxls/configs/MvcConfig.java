package com.example.jxls.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        //--позже оказалось что регистрировать эти Контроллеры и связывать их с Видами НЕ нужно
        //  ..непонятно как это работает..
        //  ..без этого класса и первичной регистрации у меня не открывался даже index.jsp по URL
        //    http://localhost:8081/webapp/
        //registry.addViewController("/").setViewName("index");
        //registry.addViewController("/hello/en").setViewName("greet/helloEng");
        //registry.addViewController("/hello/rus").setViewName("greet/helloRus");
        //registry.addViewController("/hello/esp").setViewName("greet/helloEsp");
    }

}
