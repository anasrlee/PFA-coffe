package com.coffee.machine.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redirections par rôle après login
        registry.addViewController("/redirect-by-role").setViewName("redirect:/role-check");
        registry.addViewController("/role-check").setViewName("auth/role-redirect");

        // Routes de base
        registry.addViewController("/").setViewName("redirect:/login");
        registry.addViewController("/login").setViewName("auth/login");
        registry.addViewController("/access-denied").setViewName("errors/403");
    }
}
