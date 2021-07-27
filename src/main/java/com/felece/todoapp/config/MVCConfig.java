package com.felece.todoapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
@EnableWebMvc
public class MVCConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        //ui tasarımlarının direkt olarak resources/templates yolundan alınması için yapılan ayarlar:
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("classpath:templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode("HTML5");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //eğer bir resource isteği gelirse kontrol edilmesi gereken yerlerin konfigurasyonu
        //registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        //registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }
}