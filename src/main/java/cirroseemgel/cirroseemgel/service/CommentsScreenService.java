package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.model.dao.ComentarioDao;
import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.entities.Comentario;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.util.LocalDateTimeFormatter;
import cirroseemgel.cirroseemgel.util.InvalidOptionScreen;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CommentsScreenService {

    public static void commentScreen(String textId) {
        Scanner scanner = new Scanner(System.in);
        ComentarioDao comentarioDao = DaoFactory.createComentarioDao();
        List<Comentario> comments = comentarioDao.findByTextoId(textId);
        boolean hasLoggedUser = LoginScreenService.hasLoggedUser;
        boolean loggedUserHasComments = false;
        int userAction;

        if (hasLoggedUser) {
            loggedUserHasComments = comments.stream().anyMatch(comment -> comment.getAutor().getId().equals(LoginScreenService.loggedUser.getId()));
        }

        showComments(comments);

        if (hasLoggedUser) {
            System.out.println("|                                   |");
            System.out.println("|    1 - Adicionar comentário       |");
            if (loggedUserHasComments) {
                System.out.println("|    2 - Ver meus comentários       |");
                System.out.println("|    3 - Voltar                     |");
            } else {
                System.out.println("|    2 - Voltar                     |");
            }
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");
        } else {
            System.out.println("|    1 - Voltar                     |");
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");
        }
        userAction = scanner.nextInt();

        if (hasLoggedUser) {
            if (userAction == 1) {
                Comentario comentarioToBeAdded = addCommentScreen(textId);
                comentarioDao.insert(comentarioToBeAdded);
                CommentsScreenService.commentScreen(textId);
            }
            if (loggedUserHasComments) {
                if (userAction == 2) {
                    List<Comentario> userLoggedComments = comments.stream().filter(comentario ->
                            comentario.getAutor().getId().equals(LoginScreenService.loggedUser.getId())).collect(Collectors.toList());
                    listLoggedUserComments(userLoggedComments);

                } else if (userAction == 3) {
                    TextCompleteScreenService.textCompleteScreen(textId);
                } else {
                    InvalidOptionScreen.OpcaoInvalidaScreen();
                    commentScreen(textId);
                }
            }

        } else {
            if (userAction == 1) {
                TextCompleteScreenService.textCompleteScreen(textId);
            } else {
                InvalidOptionScreen.OpcaoInvalidaScreen();
                commentScreen(textId);
            }
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

    public static Comentario addCommentScreen(String textId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|      Insira seu comentario!       |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        String commentContent = scanner.nextLine();

        Comentario comment = new Comentario();
        comment.setData(LocalDateTime.now());
        comment.setAutor(LoginScreenService.loggedUser);
        comment.setConteudo(commentContent);
        comment.setTexto(new Texto(textId));

        return comment;
    }

    public static void commentAddedOrUpdatedOrDeletedSucessfully(String addedOrUpdatedOrDeleted) {
        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("      Comentario " + addedOrUpdatedOrDeleted + "!");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");
    }


    public static void listLoggedUserComments(List<Comentario> commentsOfUser) {
        Scanner scanner = new Scanner(System.in);
        String textId = commentsOfUser.get(0).getTexto().getId();
        int userAction;

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|    Seus comentarios:              |");
        System.out.println("|                                   |");
        for (int i = 0; i < commentsOfUser.size(); i++) {
            Comentario comment = commentsOfUser.get(i);
            int indexToBeShown = i + 1;
            String dateOfThisCommentFormatted = LocalDateTimeFormatter.format(comment.getData());
            System.out.println("     " + indexToBeShown + " - " + comment.getAutor().getNome() + "       " + dateOfThisCommentFormatted);
            System.out.println();
            System.out.println("     " + comment.getConteudo());
            System.out.println();
        }

        System.out.println("|    Se desejar editar ou excluir   |");
        System.out.println("|    um comentario digite o número  |");
        System.out.println("|    correspondente a ele!          |");
        System.out.println("|                                   |");
        System.out.println("|    0 - Voltar                     |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        userAction = scanner.nextInt();

        if (userAction == 0) {
            commentScreen(textId);
        } else if (userAction > 0 && userAction < commentsOfUser.size() + 1) {
            Comentario commentToBeViewed = commentsOfUser.get(userAction - 1);
            editOrDeleteComment(commentToBeViewed);
        } else {
            InvalidOptionScreen.OpcaoInvalidaScreen();
            listLoggedUserComments(commentsOfUser);
        }
    }

    public static void editOrDeleteComment(Comentario comment) {
        Scanner scanner = new Scanner(System.in);
        ComentarioDao commentDao = DaoFactory.createComentarioDao();
        int userAction;

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println();
        String dateOfThisCommentFormatted = LocalDateTimeFormatter.format(comment.getData());
        System.out.println("     " + comment.getAutor().getNome() + "       " + dateOfThisCommentFormatted);
        System.out.println();
        System.out.println("     " + comment.getConteudo());
        System.out.println();
        System.out.println("|    1 - Editar                     |");
        System.out.println("|    2 - Excluir                    |");
        System.out.println("|    3 - Voltar                     |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        userAction = scanner.nextInt();

        if (userAction == 1) {
            String newMessage;
            System.out.println("+ --------------------------------- +");
            System.out.println("|          Cirrose em Gel           |");
            System.out.println("|                                   |");
            System.out.println("|     Qual será a nova mensagem?    |");
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");
            newMessage = scanner.nextLine();
            comment.setConteudo(newMessage);
            commentDao.updateCommentContent(comment);
        } else if (userAction == 2) {
            commentDao.deleteById(comment.getId());
        } else if (userAction != 3) {
            InvalidOptionScreen.OpcaoInvalidaScreen();
        }
        commentScreen(comment.getTexto().getId());
    }
}
