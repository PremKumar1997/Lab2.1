package com.propertytax.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * The class to configure the jsp file location.
 * 
 * @author Prem Kumar
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class JspConfiguration implements WebMvcConfigurer {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#
	 * configureViewResolvers(org.springframework.web.servlet.config.annotation.
	 * ViewResolverRegistry)
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		registry.viewResolver(resolver);
	}
}
