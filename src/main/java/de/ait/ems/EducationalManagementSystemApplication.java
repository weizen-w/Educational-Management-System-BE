package de.ait.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EducationalManagementSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(EducationalManagementSystemApplication.class, args);
  }
}
