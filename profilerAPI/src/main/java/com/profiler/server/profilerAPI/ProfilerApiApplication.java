package com.profiler.server.profilerAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//Please consider removing (exclude = {SecurityAutoConfiguration.class}) and its import at a later date
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ProfilerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfilerApiApplication.class, args);
	}

}
