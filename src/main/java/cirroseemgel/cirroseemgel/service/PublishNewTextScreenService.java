package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.TextoDao;
import cirroseemgel.cirroseemgel.model.dao.UsuarioDao;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.model.entities.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class PublishNewTextScreenService {

    public static void publishNewTextMainScreen() {

        String titulo = setNewTextTitleScreen();

        String descricao = setNewTextDescriptionScreen();

        String conteudo = setNewTextContentScreen();

        LocalDateTime dataPublicacao = LocalDateTime.now();

        TextoDao textoDao = DaoFactory.createTextoDao();
        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

        Texto texto = new Texto(titulo, descricao, conteudo, dataPublicacao);

        textoDao.insert(texto);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|    Texto publicado com sucesso!   |");
        System.out.println("+ --------------------------------- +");

        EmailService.SendEmails(texto);

        MainScreenService.ownerLoggedMainMenuScreen();

    }

    public static String setNewTextTitleScreen(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Legal! Teremos um novo texto!   |");
        System.out.println("|   Qual será o título?             |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        String titulo = scanner.nextLine();

        return titulo;

    }

    public static String setNewTextDescriptionScreen() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Qual a descrição do texto?      |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        String descricao = scanner.nextLine();

        return descricao;

    }

    public static String setNewTextContentScreen(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|  Agora, escritor, digite o texto! |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        String texto = scanner.nextLine();

        return texto;

    }

}
