package com.duantian.cms.core.config;

import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "com.duantian.cms", excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
		Controller.class }) })
@EnableCaching(proxyTargetClass = true)
public class AppConfig extends CachingConfigurerSupport {

	@Bean
	public CacheManager cacheManager() {
		javax.cache.CacheManager cacheManager = Caching.getCachingProvider()
				.getCacheManager();
		MutableConfiguration<Object, Object> mutableConfiguration = new MutableConfiguration<Object, Object>();
		mutableConfiguration.setStoreByValue(false);
		cacheManager.createCache("default", mutableConfiguration);
		JCacheCacheManager jCacheCacheManager = new JCacheCacheManager(
				cacheManager);
		return jCacheCacheManager;
	}

	@Bean
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Bean
	protected JCacheManagerFactoryBean defaultCacheManagerFactoryBean() {
	return new JCacheManagerFactoryBean();
	}

}
