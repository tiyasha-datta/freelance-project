package com.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FleetManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FleetManagementSystemApplication.class, args);
	}

}
