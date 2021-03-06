package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.model.dao.ComentarioDao;
import cirroseemgel.cirroseemgel.model.dao.CurtidaDao;
import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.TextoDao;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.util.LocalDateTimeFormatter;
import cirroseemgel.cirroseemgel.util.InvalidOptionScreen;

import java.util.List;
import java.util.Scanner;

public class LatestTextsScreenService {

    public static void LastestTextsScreen(int numberOfTextsToLoad) {

        Scanner scanner = new Scanner(System.in);

        int acaoUsuario;
        TextoDao textoDao = DaoFactory.createTextoDao();
        CurtidaDao curtidaDao = DaoFactory.createCurtidaDao();
        ComentarioDao comentarioDao = DaoFactory.createComentarioDao();
        List<Texto> latestTexts = textoDao.findLatestTexts(numberOfTextsToLoad);
        boolean hasLoadedAllTexts = numberOfTextsToLoad > latestTexts.size();
        boolean hasTexts = latestTexts.size() > 0;

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        if (hasTexts) {
            System.out.println("|   Ultimos textos:                 |");
            System.out.println("|                                   |");
            for (int i = 0; i < latestTexts.size(); i++) {
                int indexToBeShown = i + 1;
                Texto text = latestTexts.get(i);
                String formattedDate = LocalDateTimeFormatter.format(text.getDataPublicacao());
                int numberOfCurtidas = curtidaDao.getNumberOfCurtidasByTextoId(text.getId());
                int numberOfComentarios = comentarioDao.getNumberOfComentariosByTextoId(text.getId());
                System.out.println("   " + indexToBeShown +" - " +text.getTitulo() + "       " + formattedDate);
                System.out.println();
                System.out.println("   " + text.getDescricao());
                System.out.println();
                System.out.println("   " + text.getNumeroVisualizacoes() + " Visualiza????es     "  + numberOfCurtidas + " Curtidas     " +
                        numberOfComentarios + " Comentarios " );
                System.out.println();
            }
            System.out.println("|   Se desejar ver mais sobre um    |");
            System.out.println("|   dos textos digite o n??mero      |");
            System.out.println("|   correspondente a ele!           |");
            System.out.println("|                                   |");
        } else {
            System.out.println("|   N??o h?? textos cadastrados!      |");
            System.out.println("|                                   |");
        }
        System.out.println("|   0 - Digite para voltar          |");
        if (hasLoadedAllTexts && hasTexts) {
            System.out.println("|                                   |");
            System.out.println("|   Esses s??o todos os textos       |");
            System.out.println("|   Disponiveis!                    |");
        } else if (!hasLoadedAllTexts && hasTexts) {
            System.out.println("    " + (latestTexts.size() + 1)  + " - para procurar se existem");
            System.out.println("|    mais textos                    |");
        }
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        acaoUsuario = scanner.nextInt();

        if (acaoUsuario == 0) {
            if (LoginScreenService.hasLoggedUser) {
                MainScreenService.userLoggedMainMenuScreen();
            } else {
                MainScreenService.mainMenuScreen();
            }
        } else if (acaoUsuario == latestTexts.size() + 1 && hasTexts) {
            LastestTextsScreen(numberOfTextsToLoad + 3);
        } else if (acaoUsuario > 0 && acaoUsuario < latestTexts.size() + 1 && hasTexts) {
            Texto selectedText = latestTexts.get(acaoUsuario - 1);
            if (LoginScreenService.hasLoggedUser) {
                selectedText.addVisualizacao();
                textoDao.addVisualizacao(selectedText);
            }
            TextCompleteScreenService.textCompleteScreen(selectedText.getId());
        } else {
            InvalidOptionScreen.OpcaoInvalidaScreen();
            LastestTextsScreen(numberOfTextsToLoad);
        }
    }

}
