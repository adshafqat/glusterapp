package com.zaynsolutions.glusterapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.zaynsolutions.glusterapp"})
public class GlusterappApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlusterappApplication.class, args);
	}

}
