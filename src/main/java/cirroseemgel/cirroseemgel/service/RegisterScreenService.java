package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.UsuarioDao;
import cirroseemgel.cirroseemgel.model.entities.Usuario;
import cirroseemgel.cirroseemgel.util.EmailValidator;
import cirroseemgel.cirroseemgel.util.InvalidOptionScreen;

import java.util.Scanner;

public class RegisterScreenService {

    public static void registerScreenService() {

        String name = nameRegisteringScreen();
        String email = emailRegisteringScreen();
        String password = passwordRegisteringScreen();
        boolean estaAssinando = signEmailNotificationsScreen();

        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

        Usuario newUser = new Usuario(name, email, password, estaAssinando);

        newUser.setId(usuarioDao.insert(newUser));

        LoginScreenService.loggedUser = newUser;
        LoginScreenService.hasLoggedUser = true;

        MainScreenService.userLoggedMainMenuScreen();

    }

    private static String nameRegisteringScreen() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Que bom que vai se inscrever!   |");
        System.out.println("|                                   |");
        System.out.println("|   Primeiro, qual seu nome?        |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        return scanner.nextLine();

    }

    private static String emailRegisteringScreen() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Legal! Agora...                 |");
        System.out.println("|                                   |");
        System.out.println("|   Insira seu email:               |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        String email = scanner.nextLine();
        boolean isValidEmail = EmailValidator.isValidEmailAddress(email);

        if (isValidEmail) {

            return email;

        }

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Email inserido ?? invalido :(    |");
        System.out.println("|                                   |");
        System.out.println("|   Tente novamente.                |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        return emailRegisteringScreen();


    }

    private static String passwordRegisteringScreen() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Legal! Agora...                 |");
        System.out.println("|                                   |");
        System.out.println("|   Digite uma senha:               |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        String password = scanner.nextLine();

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Legal! Agora...                 |");
        System.out.println("|                                   |");
        System.out.println("|   Repita a senha:                 |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        String passwordConfirmation = scanner.nextLine();

        if (password.equals(passwordConfirmation)) {

            return password;

        }

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   As senhas n??o bateram :(        |");
        System.out.println("|                                   |");
        System.out.println("|   Tente novamente.                |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        return passwordRegisteringScreen();

    }

    private static boolean signEmailNotificationsScreen() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Gostaria de ser notificado      |");
        System.out.println("|   sempre que um novo texto        |");
        System.out.println("|   for publicado?                  |");
        System.out.println("|                                   |");
        System.out.println("|   1 - Sim, eu adoraria.           |");
        System.out.println("|   2 - Na verdade, eu n??o quero.   |");
        System.out.println("|                                   |");
        System.out.println("|   Digite o n??mero da a????o         |");
        System.out.println("|   desejada                        |");
        System.out.println("+ --------------------------------- +");

        int escolhaDoUsuario = scanner.nextInt();

        if (escolhaDoUsuario <= 0 || escolhaDoUsuario >= 3) {

            InvalidOptionScreen.OpcaoInvalidaScreen();

            return signEmailNotificationsScreen();

        } else if (escolhaDoUsuario == 1) {

            return true;

        }

        return false;

    }

}
