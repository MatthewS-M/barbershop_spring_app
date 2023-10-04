/**
 The MvcConfig class represents a configuration class for Spring MVC web applications.
 @Configuration indicates that it is a configuration class that is managed by the Spring container.
 It implements the WebMvcConfigurer interface, which provides methods for configuring Spring MVC web applications.
 addViewControllers adds a view controller that maps the root URL of the application to the index.html file.
 This method uses a ViewControllerRegistry object to register the view controller for the application.
 This class is a crucial part of the Spring MVC framework, and it is responsible for configuring the web application
 and providing the necessary resources for the application to function correctly.

 @author Matthew Suprunov
 @version 1.0
 @since 20/04/2023
 */
// конфигурационный класс для объединени всех методов в одно Spring boot приложение. Все зависимости будут взаимодействовать во всех классах
package com.example;
import org.springframework.context.annotation.Configuration; // for creating the bean for this class
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry; // allows to create simple automated controllers pre-configured with status code and/or a view.
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     Adds a view controller that maps the root URL of the application to the index.html file.
     @param registry A ViewControllerRegistry object that is used to register the view controller for the application.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/");
    }
}
