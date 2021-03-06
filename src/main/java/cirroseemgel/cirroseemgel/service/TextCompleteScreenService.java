package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.model.dao.ComentarioDao;
import cirroseemgel.cirroseemgel.model.dao.CurtidaDao;
import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.TextoDao;
import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Curtida;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import sun.rmi.runtime.Log;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextCompleteScreenService {

    public static void textCompleteScreen(String textoId) {

        boolean hasUserLikedThisText = false;
        CurtidaDao curtidaDao = DaoFactory.createCurtidaDao();
        ComentarioDao comentarioDao = DaoFactory.createComentarioDao();
        List<Curtida> likes = curtidaDao.findByTextoId(textoId);
        List<Comentario> comments = comentarioDao.findByTextoId(textoId);
        TextoDao textoDao = DaoFactory.createTextoDao();
        Texto text = textoDao.findById(textoId);

        if (LoginScreenService.hasLoggedUser) {

            hasUserLikedThisText = likes.stream().anyMatch(curtida -> curtida.getAutor().getId().equals(LoginScreenService.loggedUser.getId()));

        }
        showTextoWithCompleteInfo(text, comments.size(), likes.size(), hasUserLikedThisText);

        int acaoTomada = showPossibleActions(hasUserLikedThisText);

        if (LoginScreenService.hasLoggedUser) {

            String userId = LoginScreenService.loggedUser.getId();
            boolean userIsOwner = userId.equals(LoginScreenService.ownerID);

            if (acaoTomada == 1) {

                if (hasUserLikedThisText) {

                    String likeIdToBeDeleted = likes.stream().filter(curtida -> curtida.getAutor().getId().equals(LoginScreenService.loggedUser.getId())).collect(Collectors.toList()).get(0).getId();
                    curtidaDao.deleteById(likeIdToBeDeleted);
                    showLikedOrDislkedMessage("Curtido!");

                } else {

                    Curtida curtida = new Curtida();
                    curtida.setTexto(text);
                    curtida.setAutor(LoginScreenService.loggedUser);
                    curtida.setData(LocalDateTime.now());
                    curtidaDao.insert(curtida);
                    showLikedOrDislkedMessage("Descurtido!");

                }

                textCompleteScreen(textoId);

            } else if (acaoTomada == 2) {

                CommentsScreenService.commentScreen(textoId);

            } else if (acaoTomada == 3) {

                if (!userIsOwner) {

                    LatestTextsScreenService.LastestTextsScreen(3);

                } else {

                    TextEditorScreenService.editionOptionsScreen(text);

                }

            } else if (acaoTomada == 4 && userIsOwner) {

                TextDeletionScreenService.textDeletionMainScreen(text);

            } else if (acaoTomada == 5 && userIsOwner) {

                LatestTextsScreenService.LastestTextsScreen(3);

            }

        } else {
            if (acaoTomada == 1) {
                CommentsScreenService.commentScreen(textoId);
            } else {
                LatestTextsScreenService.LastestTextsScreen(3);
            }
        }
    }

    public static void showTextoWithCompleteInfo(Texto text, int numberOfComments, int numberOfCurtidas, boolean hasUserLikedThisText) {
        String hasLiked = "";
        if (hasUserLikedThisText) {
            hasLiked = "(Curtido)";
        }
        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("    " + text.getTitulo());
        System.out.println();
        System.out.println("    " + text.getDescricao());
        System.out.println();
        System.out.println("    " + text.getConteudo());
        System.out.println();
        System.out.println("   " + text.getNumeroVisualizacoes() + " Visualiza????es     "  + numberOfCurtidas + " Curtidas " + hasLiked +
                "     " + numberOfComments + " Comentarios");
        System.out.println("|                                   |");
    }

    public static int showPossibleActions(boolean hasUserLikedThisText) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("|   O que desejas fazer?            |");
        System.out.println("|                                   |");

        int numberOfInitialActions = 1;

        if (hasUserLikedThisText && LoginScreenService.hasLoggedUser) {

            System.out.println("|   1 - Descurtir                   |");

            numberOfInitialActions++;

        } else if (LoginScreenService.hasLoggedUser) {

            System.out.println("|   1 - Curtir                      |");

            numberOfInitialActions++;

        }

        System.out.println("|   2 - Ver comentarios             |");

        numberOfInitialActions++;

        String loggedUserId = LoginScreenService.loggedUser.getId();

        if (loggedUserId.equals(LoginScreenService.ownerID)) {
            System.out.println("|   3 - Editar texto.                |");
            System.out.println("|   4 - Deletar texto.               |");
            numberOfInitialActions+=2;
        }
        System.out.println("|   " + numberOfInitialActions + " - Sair                        |");
        System.out.println("|                                   |");
        System.out.println("|   Digite o n??mero da a????o         |");
        System.out.println("|   Desejada                        |");
        System.out.println("+ --------------------------------- +");

        return scanner.nextInt();
    }

    public static void showLikedOrDislkedMessage(String likedOrDisliked) {
        System.out.println("+ --------------------------------- +");
        System.out.println("|                                   |");
        System.out.println("     Texto " + likedOrDisliked + "   ");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");
    }
}
