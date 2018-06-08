package com.etsisi.sps.config;

import com.etsisi.sps.converter.RoleToUserRolesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({"com.etsisi.sps.controllers", "com.etsisi.sps.services", "com.etsisi.sps.daos",
		"com.etsisi.sps.exception", "com.etsisi.sps.validators","com.etsisi.sps.converter" })
//@ComponentScan(basePackages="com.etsisi.sps")
//@Import({ SecurityConfig.class,SpringDBConfig.class })
@Import({ SecurityConfig.class,HibernateConfiguration.class })

public class SpringWebConfig extends WebMvcConfigurerAdapter {
	@Autowired
    RoleToUserRolesConverter roleToUserRoleConverter;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserRoleConverter);
    }

    @Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/*@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames(new String[] { "messages/messages", "messages/ValidationMessages" });
		return messageSource;
	}*/

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages/ValidationMessages");

		return messageSource;
	}
}
