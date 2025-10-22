package com.formation.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.formation.config.OpenApiManualConfig;
import com.formation.config.PersistenceConfig;
import com.formation.config.SecurityConfig;

import org.springframework.context.annotation.ComponentScan;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = { "com.formation.web", "com.formation.api" })
@Import({ PersistenceConfig.class, OpenApiManualConfig.class, SecurityConfig.class })
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-ui/");
    }

    /*
     * @Override
     * public void addViewControllers(ViewControllerRegistry registry) {
     * registry.addViewController("/swagger-ui").setViewName(
     * "forward:/swagger-ui/index.html");
     * registry.addViewController("/swagger-ui/").setViewName(
     * "forward:/swagger-ui/index.html");
     * }
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}