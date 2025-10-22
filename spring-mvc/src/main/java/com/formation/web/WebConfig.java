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
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

import com.formation.config.DataInitializer;
import com.formation.config.OpenApiManualConfig;
import com.formation.config.PersistenceConfig;
import com.formation.config.SecurityConfig;

import javax.xml.crypto.Data;

import org.springframework.context.annotation.ComponentScan;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = { "com.formation.web", "com.formation.api" })
@Import({ PersistenceConfig.class, OpenApiManualConfig.class, SecurityConfig.class, DataInitializer.class })
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Sert tous les webjars (dont swagger-ui)
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .resourceChain(true)
                .addResolver(new WebJarsResourceResolver())
                .addResolver(new PathResourceResolver());

        // Alias pratique si tu veux /swagger-ui/** directement
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
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