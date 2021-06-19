package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.model.dao.ComentarioDao;
import cirroseemgel.cirroseemgel.model.dao.CurtidaDao;
import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.TextoDao;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.util.InvalidOptionScreen;

import java.util.Scanner;

public class TextDeletionScreenService {

    public static void textDeletionMainScreen(Texto textToDelete) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Tem certeza que quer deletar    |");
        System.out.println("   |" + textToDelete.getTitulo() + "|");
        System.out.println("|                                   |");
        System.out.println("|   1 - Sim                         |");
        System.out.println("|   2 - Não                         |");
        System.out.println("|                                   |");
        System.out.println("|   Digite o número da ação         |");
        System.out.println("|   desejada                        |");
        System.out.println("+ --------------------------------- +");

        int acaoTomada = scanner.nextInt();

        if (acaoTomada == 1) {

            String textToDeleteId = textToDelete.getId();

            ComentarioDao comentarioDao = DaoFactory.createComentarioDao();
            comentarioDao.deleteTextComments(textToDeleteId);

            CurtidaDao curtidaDao = DaoFactory.createCurtidaDao();
            curtidaDao.deleteTextCurtidas(textToDeleteId);

            TextoDao textoDao = DaoFactory.createTextoDao();
            textoDao.deleteById(textToDeleteId);

            System.out.println("+ --------------------------------- +");
            System.out.println("|          Cirrose em Gel           |");
            System.out.println("|                                   |");
            System.out.println("|     Texto deletado com sucesso    |");
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");

            MainScreenService.ownerLoggedMainMenuScreen();

        } else if (acaoTomada == 2) {

            TextCompleteScreenService.textCompleteScreen(textToDelete.getId());

        } else  {

            InvalidOptionScreen.OpcaoInvalidaScreen();
            TextDeletionScreenService.textDeletionMainScreen(textToDelete);

        }



    }

}
