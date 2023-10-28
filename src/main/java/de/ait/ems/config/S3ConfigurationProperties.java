package de.ait.ems.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Configuration
@ConfigurationProperties(prefix = "s3")
@Data
public class S3ConfigurationProperties {

  private String accessKey;

  private String secretKey;

  private String endpoint;

  private String region;
}
