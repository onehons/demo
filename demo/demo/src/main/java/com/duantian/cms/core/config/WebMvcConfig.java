package com.duantian.cms.core.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan("com.duantian.cms.controller")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/res/**").addResourceLocations("/res/");
	}

	@Bean
	public FreeMarkerConfig freeMarkerConfig() {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/");
		freeMarkerConfigurer.setDefaultEncoding("UTF-8");
		Properties properties = new Properties();
		properties.setProperty("template_update_delay", "10");
		properties.setProperty("locale", "zh_CN");
		properties.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
		properties.setProperty("date_format", "yyyy-MM-dd");
		properties.setProperty("number_format", "#.##");
		freeMarkerConfigurer.setFreemarkerSettings(properties);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("domain", env.getProperty("website.domain"));
		freeMarkerConfigurer.setFreemarkerVariables(variables);
		return freeMarkerConfigurer;
	}

	@Bean
	public ViewResolver viewResolver() {
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setSuffix(".html");
		viewResolver.setRequestContextAttribute("request");
		viewResolver.setContentType("text/html;charset=UTF-8");
		return viewResolver;
	}

	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(
				Charset.forName("UTF-8"));
		List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
		list.add(stringHttpMessageConverter);
		requestMappingHandlerAdapter.setMessageConverters(list);
		return requestMappingHandlerAdapter;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/message");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setCacheSeconds(5);
		return messageSource;
	}

}
