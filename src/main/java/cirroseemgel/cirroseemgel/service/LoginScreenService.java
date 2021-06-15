package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.UsuarioDao;
import cirroseemgel.cirroseemgel.model.entities.Usuario;
import cirroseemgel.cirroseemgel.util.EmailValidator;

import java.util.Scanner;

public class LoginScreenService {

    private static Usuario loggedUser;

    public static void mainLoginScreen() {
        Scanner scanner = new Scanner(System.in);
        int acaoUsuario;
        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Login:                          |");
        System.out.println("|                                   |");
        System.out.println("|     Email:  ?                     |");
        System.out.println("|     Senha:  ?                     |");
        System.out.println("|                                   |");
        System.out.println("|   1 - Para digitar dados e        |");
        System.out.println("|       entrar                      |");
        System.out.println("|   2 - Para fazer cadastro         |");
        System.out.println("|   3 - Para voltar                 |");
        System.out.println("|   4 - Sair                        |");
        System.out.println("|                                   |");
        System.out.println("|   Digite o número da ação         |");
        System.out.println("|   desejada.                       |");
        System.out.println("+ --------------------------------- +");

        acaoUsuario = scanner.nextInt();

        switch (acaoUsuario) {
            case 1:
                loginScreen();
                break;
            case 2:
                RegisterScreenService.registerScreenService();
                break;
            case 3:
                MainScreenService.mainMenuScreen();
                break;
            case 4:
                ExitScreenService.mainExitScreen();
                break;
        }
    }

    private static void loginScreen() {
        Scanner scanner = new Scanner(System.in);
        String email;
        String password;

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Login:                          |");
        System.out.println("|                                   |");
        System.out.println("|     Email:  ?                     |");
        System.out.println("|                                   |");
        System.out.println("|     Insira o seu email:           |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        email = scanner.nextLine();
        boolean isValidEmail = EmailValidator.isValidEmailAddress(email);

        if (isValidEmail) {
            System.out.println("+ --------------------------------- +");
            System.out.println("|          Cirrose em Gel           |");
            System.out.println("|                                   |");
            System.out.println("|   Login:                          |");
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");
            System.out.println("     Email: " + email);
            System.out.println("+ --------------------------------- +");
            System.out.println("|                                   |");
            System.out.println("|     Senha:  ?                     |");
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");
            System.out.println("|                                   |");
            System.out.println("|     Insira sua senha:             |");
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");

            password = scanner.nextLine();
            //todo create password validation (like email one)
            UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

            Usuario user = usuarioDao.findByEmailAndPassword(email, password);

            if (user != null) {
                System.out.println("+ --------------------------------- +");
                System.out.println("|                                   |");
                System.out.println("    Bem vindo " + user.getNome() + " !");
                System.out.println("|                                   |");
                System.out.println("+ --------------------------------- +");
                loggedUser = user;
                MainRouterScreenService.mainRouterScreen();

            } else {

                System.out.println("Nenhum usuario encontrado com esse login! Tente novamente!");
                scanner.close();
                loginScreen();

            }
        } else {
            System.out.println("+ --------------------------------- +");
            System.out.println("|                                   |");
            System.out.println("|    Email inserido é invalido!     |");
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");
            scanner.close();
            loginScreen();
        }
    }
}
