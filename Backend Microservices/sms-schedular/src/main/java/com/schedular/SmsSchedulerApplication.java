package com.schedular;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import com.schedular.commons.Logger;
import com.schedular.commons.StringUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
@EnableJpaRepositories
@EnableSwagger2
// @PropertySource(ignoreResourceNotFound = false,
// value = {"classpath:db.properties", "classpath:mailconfig.properties"})
@PropertySource(ignoreResourceNotFound = true,
    value = {
        "file:${" + SmsSchedulerApplication.PROPERTIES_LOCATION_ENV + "}/"
            + SmsSchedulerApplication.APPLICATION_PROPERTY + ".properties",
        "file:${" + SmsSchedulerApplication.PROPERTIES_LOCATION_ENV + "}/"
            + SmsSchedulerApplication.DB_PROPERTY + ".properties",
        "file:${" + SmsSchedulerApplication.PROPERTIES_LOCATION_ENV + "}/"
            + SmsSchedulerApplication.MAIL_PROPERTY + ".properties",
        "file:${" + SmsSchedulerApplication.PROPERTIES_LOCATION_ENV + "}/"
            + SmsSchedulerApplication.ERROR_MESSAGES_PROPERTY + ".properties"})
public class SmsSchedulerApplication {

  private static Logger log = Logger.getLogger(SmsSchedulerApplication.class);

  public static final String PROPERTIES_LOCATION_ENV = "spring.config.location";
  public static final String APPLICATION_PROPERTY = "sms-application";
  public static final String DB_PROPERTY = "sms-db";
  public static final String MAIL_PROPERTY = "sms-mailconfig";
  public static final String ERROR_MESSAGES_PROPERTY = "sms-error-messages";

  protected static final List<String> PROPERTY_FILES =
      Arrays.asList(APPLICATION_PROPERTY, DB_PROPERTY, MAIL_PROPERTY, ERROR_MESSAGES_PROPERTY);
  public static final String PROPERTIES_FILE_NAME = String.join(",", PROPERTY_FILES);

  public static void main(String[] args) {
    String configLocation =
        System.getProperty(SmsSchedulerApplication.PROPERTIES_LOCATION_ENV, "classpath:/");
    String configPath = configLocation + " - " + SmsSchedulerApplication.PROPERTIES_FILE_NAME;
    log.info("Configpath: {}", configPath);
    if (StringUtils.isNotBlank(configLocation)) {
      Properties props = new Properties();
      props.setProperty(SmsSchedulerApplication.PROPERTIES_LOCATION_ENV, configLocation);
      props.setProperty("spring.config.name", SmsSchedulerApplication.PROPERTIES_FILE_NAME);
      ConfigurableApplicationContext applicationContext =
          new SpringApplicationBuilder(SmsSchedulerApplication.class).properties(props).build()
              .run(args);
      applicationContext.getEnvironment();
    } else {
      SpringApplication.run(SmsSchedulerApplication.class, args);
    }
  }

}
