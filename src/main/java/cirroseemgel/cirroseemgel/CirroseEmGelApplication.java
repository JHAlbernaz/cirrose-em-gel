package cirroseemgel.cirroseemgel;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.TextoDao;
import cirroseemgel.cirroseemgel.model.dao.UsuarioDao;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.model.entities.Usuario;
import cirroseemgel.cirroseemgel.service.EmailService;
import cirroseemgel.cirroseemgel.service.MainScreenService;
import cirroseemgel.cirroseemgel.util.EmailValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

@SpringBootApplication
public class CirroseEmGelApplication {

	public static void main(String[] args) {
//		SpringApplication.run(CirroseEmGelApplication.class, args);

		MainScreenService.mainMenuScreen();
	}
}
