package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.model.dao.ComentarioDao;
import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.util.LocalDateTimeFormatter;

import java.util.List;
import java.util.Scanner;

public class CommentsScreenService {

    public static void commentScreen(String textId) {
        Scanner scanner = new Scanner(System.in);
        ComentarioDao comentarioDao = DaoFactory.createComentarioDao();
        List<Comentario> comments = comentarioDao.findByTextoId(textId);
        boolean hasLoggedUser = LoginScreenService.hasLoggedUser;
        int userAction;

        showComments(comments);

        if (hasLoggedUser) {
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");
        } else {
            System.out.println("|    1 - Voltar                     |");
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");
        }
        userAction = scanner.nextInt();

        if (userAction == 1 && hasLoggedUser) {

        } else if (userAction == 1) {
            TextCompleteScreenService.textCompleteScreen(textId);
        }

    }

    public static void showComments(List<Comentario> comments) {
        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|    Comentarios:                   |");
        System.out.println();
        for (int i = 0; i < comments.size(); i++) {
            Comentario comment = comments.get(i);
            String dateOfThisCommentFormatted = LocalDateTimeFormatter.format(comment.getData());
            System.out.println("     " + comment.getAutor().getNome() + "       " + dateOfThisCommentFormatted);
            System.out.println();
            System.out.println("     " + comment.getConteudo());
            System.out.println();
        }
    }
}
