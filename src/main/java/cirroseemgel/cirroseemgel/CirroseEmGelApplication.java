package cirroseemgel.cirroseemgel;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.UsuarioDao;
import cirroseemgel.cirroseemgel.model.entities.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class CirroseEmGelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CirroseEmGelApplication.class, args);
		Usuario usuario = new Usuario();
		usuario.setNome("João Henrique");
		usuario.setEmail("joaohbalbernaz@gmail.com");
		usuario.setId("asd123");
		usuario.setSenha("1234");
		usuario.setEstaAssinando(false);
		UsuarioDao usuarioDao = DaoFactory.crateUsuarioDao();
		usuarioDao.insert(usuario);
	}
}
