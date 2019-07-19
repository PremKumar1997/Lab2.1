package com.propertytax.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The Spring boot starter class Application.
 * 
 * @author Prem Kumar
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.propertytax")
@EnableJpaRepositories(basePackages = "com.propertytax.repository")
@EntityScan("com.propertytax.entity")
public class ResidentialPropertyTaxApplication extends SpringBootServletInitializer {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.web.servlet.support.SpringBootServletInitializer#
	 * configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplication) {
		return springApplication.sources(ResidentialPropertyTaxApplication.class);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ResidentialPropertyTaxApplication.class, args);
	}

}
