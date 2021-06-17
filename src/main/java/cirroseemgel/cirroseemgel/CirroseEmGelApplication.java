package cirroseemgel.cirroseemgel;

import cirroseemgel.cirroseemgel.service.MainScreenService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CirroseEmGelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CirroseEmGelApplication.class, args);

		MainScreenService.mainMenuScreen();
	}
}
