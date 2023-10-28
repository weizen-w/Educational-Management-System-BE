package de.ait.ems.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.documentation.OpenApiDocumentation;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@org.springframework.context.annotation.Configuration
public class AppConfig {

  @Bean
  public AmazonS3 amazonS3(S3ConfigurationProperties properties) {
    AWSCredentials credentials = new BasicAWSCredentials(properties.getAccessKey(),
        properties.getSecretKey());
    AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
        properties.getEndpoint(), properties.getRegion());
    AmazonS3ClientBuilder amazonS3ClientBuilder = AmazonS3ClientBuilder
        .standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials));
    amazonS3ClientBuilder.setEndpointConfiguration(endpointConfiguration);
    return amazonS3ClientBuilder.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public OpenAPI openAPI(@Value("${base.url}") String baseUrl) {
    ResolvedSchema resolvedSchema = ModelConverters.getInstance()
        .resolveAsResolvedSchema(new AnnotatedType(StandardResponseDto.class).resolveAsRef(false));
    return new OpenAPI().servers(
            Arrays.asList(new Server().url("http://localhost:8080"), new Server().url(baseUrl)))
        .components(new Components()
            .addSchemas("EmailAndPassword", OpenApiDocumentation.emailAndPassword())
            .addSecuritySchemes("cookieAuth", OpenApiDocumentation.securityScheme())
            .addSchemas("StandardResponseDto",
                resolvedSchema.schema.description("StandardResponseDto")))
        .addSecurityItem(OpenApiDocumentation.buildSecurity())
        .paths(OpenApiDocumentation.buildAuthenticationPath())
        .info(new Info().title("Template Project API").version("0.1"));
  }

  @Bean
  public WebMvcConfigurer cors() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
      }
    };
  }

  @Bean
  public Configuration freemarkerConfiguration() {
    Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
    configuration.setDefaultEncoding("UTF-8");
    configuration.setTemplateLoader(new ClassTemplateLoader(AppConfig.class, "/mails/"));
    return configuration;
  }
}
