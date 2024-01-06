package com.distributed.MusicAppProject;

import com.distributed.MusicAppProject.Services.APIServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
public class MusicAppProjectApplication {

	public static String deezerCode;
	public static void main(String[] args) {

		SpringApplication.run(MusicAppProjectApplication.class, args);
		try {
			System.out.println("https://connect.deezer.com/oauth/auth.php?app_id=657351&redirect_uri=https://localhost/callback&perms=basic_access");
			System.out.print("Please enter code from deezer: ");

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(System.in));
			deezerCode = reader.readLine();
			APIServiceImpl.DeezerApiInit(deezerCode);
		}
		catch (Exception e) {
			System.out.println("Error Getting Deezer Code!");
			e.printStackTrace();
		}
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
