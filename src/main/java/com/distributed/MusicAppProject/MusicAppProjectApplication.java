package com.distributed.MusicAppProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
public class MusicAppProjectApplication {

	public static String deezerCode;
	public static void main(String[] args) {

		SpringApplication.run(MusicAppProjectApplication.class, args);
	}


	// Things that can be added
	// CLEAN UP RESPONSES
	// AUTHENTICATE ONLY USING ONE LINK
	// CLEAN UP CODE SOME BIT

}
