package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CloudNativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudNativeApplication.class, args);
	}

}
