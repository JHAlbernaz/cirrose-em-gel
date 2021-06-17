package cirroseemgel.cirroseemgel;

import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.TextoDao;
import cirroseemgel.cirroseemgel.model.dao.impl.TextoDaoJDBC;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.service.MainScreenService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class CirroseEmGelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CirroseEmGelApplication.class, args);

		MainScreenService.mainMenuScreen();
	}
}
