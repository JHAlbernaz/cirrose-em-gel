package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.TextoDao;
import cirroseemgel.cirroseemgel.model.entities.Texto;
import cirroseemgel.cirroseemgel.util.InvalidOptionScreen;

import java.util.Scanner;

public class TextEditorScreenService {

    public static void editionOptionsScreen(Texto textBeingEdited) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   O que gostaria de editar em     |");
        System.out.println("   |" + textBeingEdited.getTitulo() + " |");
        System.out.println("|                                   |");
        System.out.println("|   1 - Nome                        |");
        System.out.println("|   2 - Descrição                   |");
        System.out.println("|   3 - Conteúdo                    |");
        System.out.println("|   4 - Voltar                      |");
        System.out.println("|   5 - Sair                        |");
        System.out.println("|                                   |");
        System.out.println("|   Digite o número da ação         |");
        System.out.println("|   desejada                        |");
        System.out.println("+ --------------------------------- +");

        int acaoTomada = scanner.nextInt();

        switch (acaoTomada) {

            case 1:

                String newTitle = getNewTextTitleScreen();

                textBeingEdited.setTitulo(newTitle);

                TextoDao textoDao = DaoFactory.createTextoDao();

                textoDao.update(textBeingEdited);

                System.out.println("+ --------------------------------- +");
                System.out.println("|          Cirrose em Gel           |");
                System.out.println("|                                   |");
                System.out.println("|   Título alterado com sucesso!    |");
                System.out.println("|                                   |");
                System.out.println("+ --------------------------------- +");

                TextEditorScreenService.editionOptionsScreen(textBeingEdited);

                break;

            case 2:

                String newDescription = getNewDescriptionScreen();

                textBeingEdited.setDescricao(newDescription);

                textoDao = DaoFactory.createTextoDao();

                textoDao.update(textBeingEdited);

                System.out.println("+ --------------------------------- +");
                System.out.println("|          Cirrose em Gel           |");
                System.out.println("|                                   |");
                System.out.println("|  Descrição alterada com sucesso!  |");
                System.out.println("|                                   |");
                System.out.println("+ --------------------------------- +");

                TextEditorScreenService.editionOptionsScreen(textBeingEdited);

                break;

            case 3:

                String newContent = getNewContentScreen();

                textBeingEdited.setConteudo(newContent);

                textoDao = DaoFactory.createTextoDao();

                textoDao.update(textBeingEdited);

                System.out.println("+ --------------------------------- +");
                System.out.println("|          Cirrose em Gel           |");
                System.out.println("|                                   |");
                System.out.println("|   Conteúdo alterado com sucesso!  |");
                System.out.println("|                                   |");
                System.out.println("+ --------------------------------- +");

                TextEditorScreenService.editionOptionsScreen(textBeingEdited);

                break;

            case 4:

                TextCompleteScreenService.textCompleteScreen(textBeingEdited.getId());

                break;

            case 5:

                ExitScreenService.mainExitScreen();

                break;

            default:

                InvalidOptionScreen.OpcaoInvalidaScreen();

                editionOptionsScreen(textBeingEdited);

        }

    }

    private static String getNewDescriptionScreen() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|      Digite a nova descrição      |");
        System.out.println("|       do texto, por favor.        |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        String newContent = scanner.nextLine();

        return newContent;

    }

    private static String getNewContentScreen() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|    Digite o conteúdo do texto     |");
        System.out.println("|    atualizado, por favor.         |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        String newContent = scanner.nextLine();

        return newContent;

    }

    private static String getNewTextTitleScreen() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|  Digite o novo título, por favor  |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        String newTitle = scanner.nextLine();

        return newTitle;

    }
}
