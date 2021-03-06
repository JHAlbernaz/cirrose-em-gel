package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.util.InvalidOptionScreen;

import java.util.Scanner;

public class MainScreenService {

    private static final int INITIAL_NUMBER_OF_TEXTS = 3;

    public static void mainMenuScreen() {
        Scanner scanner = new Scanner(System.in);

        int acaoInicial;

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   O que desejas fazer?            |");
        System.out.println("|                                   |");
        System.out.println("|   1 - Ver ultimos textos          |");
        System.out.println("|   2 - Fazer login ou cadastrar-se |");
        System.out.println("|   3 - Sair                        |");
        System.out.println("|                                   |");
        System.out.println("|   Digite o número da ação         |");
        System.out.println("|   Desejada                        |");
        System.out.println("+ --------------------------------- +");

        acaoInicial = scanner.nextInt();

        if (acaoInicial == 1) {
            LatestTextsScreenService.LastestTextsScreen(INITIAL_NUMBER_OF_TEXTS);
        } else if (acaoInicial == 2) {
            LoginScreenService.mainLoginScreen();
        } else if (acaoInicial == 3){
            ExitScreenService.mainExitScreen();
        } else {
            InvalidOptionScreen.OpcaoInvalidaScreen();
            mainMenuScreen();
        }

        scanner.close();
    }

    public static void userLoggedMainMenuScreen() {
        Scanner scanner = new Scanner(System.in);

        int acaoInicial;

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Você está logado!               |");
        System.out.println("|   O que desejas fazer?            |");
        System.out.println("|                                   |");
        System.out.println("|   1 - Ver ultimos textos          |");
        System.out.println("|   2 - Editar perfil               |");
        System.out.println("|   3 - Sair                        |");
        System.out.println("|                                   |");
        System.out.println("|   Digite o número da ação         |");
        System.out.println("|   Desejada                        |");
        System.out.println("+ --------------------------------- +");

        acaoInicial = scanner.nextInt();

        if (acaoInicial == 1) {
            LatestTextsScreenService.LastestTextsScreen(INITIAL_NUMBER_OF_TEXTS);
        } else if (acaoInicial == 2) {
            UserAccountMenuScreenService.userAccountMenu();
        } else if (acaoInicial == 3) {
            ExitScreenService.mainExitScreen();
        } else {
            InvalidOptionScreen.OpcaoInvalidaScreen();
            userLoggedMainMenuScreen();
        }

        scanner.close();
    }

    public static void ownerLoggedMainMenuScreen() {

        Scanner scanner = new Scanner(System.in);

        int acaoInicial;

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Você está logado, escritor!     |");
        System.out.println("|   O que desejas fazer?            |");
        System.out.println("|                                   |");
        System.out.println("|   1 - Ver ultimos textos          |");
        System.out.println("|   2 - Publicar novo texto         |");
        System.out.println("|   3 - Sair                        |");
        System.out.println("|                                   |");
        System.out.println("|   Digite o número da ação         |");
        System.out.println("|   Desejada                        |");
        System.out.println("+ --------------------------------- +");

        acaoInicial = scanner.nextInt();

        switch (acaoInicial) {

            case 1:
                LatestTextsScreenService.LastestTextsScreen(INITIAL_NUMBER_OF_TEXTS);
                break;
            case 2:
                PublishNewTextScreenService.publishNewTextMainScreen();
                break;

            case 3:
                ExitScreenService.mainExitScreen();

        }

        scanner.close();

    }
}
