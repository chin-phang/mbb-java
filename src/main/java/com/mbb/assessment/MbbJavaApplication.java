package com.mbb.assessment;

import com.mbb.assessment.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class MbbJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MbbJavaApplication.class, args);
	}

}
