package cirroseemgel.cirroseemgel.service;

import java.util.Scanner;

public class MainScreenService {

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

        switch (acaoInicial) {
            case 1:
                break;
            case 2:
                LoginScreenService.mainLoginScreen();
                break;
            case 3:
                ExitScreenService.mainExitScreen();
                break;
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
        System.out.println("|   2 - Sair                        |");
        System.out.println("|                                   |");
        System.out.println("|   Digite o número da ação         |");
        System.out.println("|   Desejada                        |");
        System.out.println("+ --------------------------------- +");

        acaoInicial = scanner.nextInt();

        switch (acaoInicial) {

            case 1:
                break;
            case 2:
                ExitScreenService.mainExitScreen();
                break;

        }

        scanner.close();
    }
}
