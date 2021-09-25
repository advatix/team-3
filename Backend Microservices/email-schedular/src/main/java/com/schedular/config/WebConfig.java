/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.config;

import java.util.List;
import java.util.Properties;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class WebConfig.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  /** The templates dir. */
  @Value("${templates.dir}")
  private String templatesDir;

  /** The logging interceptor. */
  @Autowired
  private LoggingInterceptor loggingInterceptor;

  /**
   * Velocity engine.
   *
   * @return the velocity engine
   */
  @Bean
  public VelocityEngine velocityEngine() {
    VelocityEngine ve = new VelocityEngine();
    ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templatesDir);
    Properties props = new Properties();
    props.put("runtime.log.logsystem.class",
        "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
    props.put("runtime.log.logsystem.log4j.category", "velocity");
    props.put("runtime.log.logsystem.log4j.logger", "velocity");
    ve.init(props);
    return ve;
  }


  /**
   * Cors filter.
   *
   * @return the filter registration bean
   */
  @Bean
  public FilterRegistrationBean<CorsFilter> corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(false);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    config.addExposedHeader("Authorization");
    config.addExposedHeader("Content-Type");
    config.addExposedHeader("X-AUTH-TOKEN");
    config.addExposedHeader("AUTH-TOKEN");
    config.addExposedHeader("Device-Type");
    config.addExposedHeader("AppVersionNo");
    config.setMaxAge(1L);
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
  }

  // /**
  // * Authenticated argument resolver.
  // *
  // * @return the authenticated argument resolver
  // */
  // @Bean
  // public AuthenticatedArgumentResolver authenticatedArgumentResolver() {
  // return new AuthenticatedArgumentResolver();
  // }
  //
  // /**
  // * Adds the argument resolvers.
  // *
  // * @param resolvers the resolvers
  // */
  // @Override
  // public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
  // resolvers.add(authenticatedArgumentResolver());
  // WebMvcConfigurer.super.addArgumentResolvers(resolvers);
  // }

  /**
   * Adds the interceptors.
   *
   * @param registry the registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loggingInterceptor);
  }

  /**
   * Adds the resource handlers.
   *
   * @param registry the registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  /**
   * Configure message converters.
   *
   * @param converters the converters
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.serializationInclusion(Include.NON_EMPTY);
    converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
  }

  @Bean(name = "processExecutor")
  public TaskExecutor workExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setThreadNamePrefix("SCHEDULAR-SERVICE-");
    threadPoolTaskExecutor.setCorePoolSize(10);
    threadPoolTaskExecutor.setMaxPoolSize(Integer.MAX_VALUE);
    threadPoolTaskExecutor.setQueueCapacity(Integer.MAX_VALUE);
    threadPoolTaskExecutor.afterPropertiesSet();
    return threadPoolTaskExecutor;
  }

}
