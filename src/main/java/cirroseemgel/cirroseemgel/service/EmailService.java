package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.UsuarioDao;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import java.util.List;
import java.util.stream.Collectors;

public class EmailService {

    private static final String EMAIL = "CirroseEmGelIntegrador@gmail.com";
    private static final String SENHA = "Integrador2K";

    public static void SendEmails(Texto texto) {

        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
        List<String> usersEmails = usuarioDao.findAllUserAssinando().stream().map(usuario -> usuario.getEmail()
        ).collect(Collectors.toList());

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(EMAIL, SENHA));
        email.setSSLOnConnect(true);

        usersEmails.forEach(userEmail -> {
        try {
            email.setFrom(EMAIL);
            email.setSubject("Cirrose Em Gel - Nova Publicação");
            email.setMsg("O texto " + texto.getTitulo() + " esta disponivel no CeG!");
            email.addTo(userEmail);
            email.send();
            System.out.println("Email enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        });
    }
}
