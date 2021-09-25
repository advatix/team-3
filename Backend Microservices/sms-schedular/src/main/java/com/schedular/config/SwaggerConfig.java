/*
 * @author Advatix
 * @since 2019
 * @version 1.0
 */
package com.schedular.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * The Class SwaggerConfig.
 */
@Configuration
public class SwaggerConfig {

  /**
   * Produce api.
   *
   * @return the docket
   */
  @Bean
  public Docket produceApi() {

    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
        .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
        .build();
  }

  // Describe your apis

  /**
   * Api info.
   *
   * @return the api info
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("EZR OMS Api's")
        .description("This page lists all the rest apis for App.").version("1.0-SNAPSHOT").build();
  }

  /**
   * Paths.
   *
   * @return the predicate
   */
  // Only select API's that matches the given Predicates.
  @SuppressWarnings("unused")
  private Predicate<String> paths() {
    // Match all paths except /error
    return Predicates.and(PathSelectors.regex("/.*"),
        Predicates.not(PathSelectors.regex("/error.*")));
  }

}
