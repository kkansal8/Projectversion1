package com.example.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
//	 @Bean
//	    public GoogleCredentials googleCredentials() throws IOException {
//	//        return GoogleCredentials.fromStream(new ClassPathResource("/modern-binder-419419-bc414143e3f2.json").getInputStream());
//	        
//	        ClassPathResource resource = new ClassPathResource("/login-config.json");
//	        System.out.println("helllo"+resource.getInputStream().available());
//	        return GoogleCredentials.fromStream(resource.getInputStream());
//	    }

}
