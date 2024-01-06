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

//	@Bean
//	public CommandLineRunner demo(MovieService movieService) {
//		return args -> {
//			//if database has no movies, save movies to database
//			if (movieRepository.count() == 0) {
//				movieService.saveMovieToDB();
//			}
//			else {
//				System.out.println("Database has movies");
//			}
//		};
//	}

}
