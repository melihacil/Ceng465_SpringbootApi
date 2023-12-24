package com.distributed.MusicAppProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MusicAppProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicAppProjectApplication.class, args);
	}

}
