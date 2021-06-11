package cirroseemgel.cirroseemgel;

import cirroseemgel.cirroseemgel.db.DB;
import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.TextoDao;
import cirroseemgel.cirroseemgel.model.dao.UsuarioDao;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.model.entities.Usuario;
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
		SpringApplication.run(CirroseEmGelApplication.class, args);
		TextoDao textoDao = DaoFactory.createTextoDao();
//		Texto texto = new Texto();
//		texto.setTitulo("Teste numero 1");
//		texto.setDescricao("Esse eh um baita teste!");
//		texto.setDataPublicacao(LocalDateTime.now());
//		texto.setConteudo("ESSA EH A PARTE QUE PODE DAR RUIM!");
//		texto.setNumeroVisualizacoes(0);

//		textoDao.findLatestTexts(1).forEach(texto -> {
//			System.out.println(texto.getId() + " " + texto.getTitulo() + " " + texto.getDescricao() + " " +
//					texto.getDescricao() + " " + texto.getDataPublicacao());
//		});
//
//		textoDao.deleteById("7cb5f6fa-bfdc-4d1e-998c-5932be8149bc");

//		textoDao.findAll().forEach(texto -> {
//			System.out.println(texto.getId() + " " + texto.getTitulo() + " " + texto.getDescricao() + " " +
//					texto.getDescricao() + " " + texto.getDataPublicacao());
//		});
//
//		System.out.println(textoDao.findById("7cb5f6fa-bfdc-4d1e-998c-5932be8149bc").getTitulo());


//		usuario = new Usuario();
//		usuario.setNome("Otto Gorl");
//		usuario.setEmail("otto.gorl@gmail.com");
//		if (!EmailValidator.isValidEmailAddress(usuario.getEmail())) {
//			System.out.println("Deu errado");
//		} else {
//			usuario.setNome("Otto Gorl");
//			usuario.setSenha("1234");
//			usuario.setEstaAssinando(true);
//			UsuarioDao usuarioDao = DaoFactory.crateUsuarioDao();
//			usuarioDao.insert(usuario);
//			usu
//		}
//
	}
}
