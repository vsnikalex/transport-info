package com.tsystems.transportinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan("com.tsystems.transportinfo.controller")
public class ViewConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin/truck").setViewName("admin_truck");
        registry.addViewController("/admin/truck_add").setViewName("admin_truck_add");
        registry.addViewController("/admin/driver").setViewName("admin_driver");
        registry.addViewController("/admin/driver_add").setViewName("admin_driver_add");
        registry.addViewController("/admin/cargo").setViewName("admin_cargo");
        registry.addViewController("/admin/cargo_add").setViewName("admin_cargo_add");
        registry.addViewController("/admin/delivery_editor").setViewName("admin_delivery_editor");
        registry.addViewController("/admin/driver_app").setViewName("admin_driver_app");
        registry.addViewController("/driver/driver_app").setViewName("driver_app");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
