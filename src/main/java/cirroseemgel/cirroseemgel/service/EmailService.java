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

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Notificando usuários inscritos  |");
        System.out.println("|   que um novo texto foi lançado.  |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        for (int i = 0; i < usersEmails.size(); i++) {
            String userEmail = usersEmails.get(i);
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(EMAIL, SENHA));
            email.setSSLOnConnect(true);
            try {
                email.setFrom(EMAIL);
                email.setSubject("Cirrose Em Gel - Nova Publicação");
                email.setMsg("Olá! Um novo texto foi publicado no CeG! " + texto.getTitulo() + " já esta disponível!");
                email.addTo(userEmail);
                email.send();
                usersNotified(usersEmails.size(), i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void usersNotified(int totalUsers, int actualUser) {

        actualUser++;
        System.out.println("+ --------------------------------- +");
        System.out.println("|    Usuarios notificados : " + actualUser + "       |");
        System.out.println("|    Total a ser notificado: " + totalUsers + "      |");
        System.out.println("+ --------------------------------- +");

    }
}
