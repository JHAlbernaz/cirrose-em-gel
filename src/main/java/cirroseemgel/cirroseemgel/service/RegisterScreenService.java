package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.util.EmailValidator;

import java.util.Scanner;

public class RegisterScreenService {

    public static void registerScreenService() {

        String nome = nameRegisteringScreen();
        String email = emailRegisteringScreen();
        String senha = passwordRegisteringScreen();
        boolean estaAssinando;

        System.out.println("TESTES");
        System.out.println(nome);
        System.out.println(email);
        System.out.println(senha);

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

        } else {

            System.out.println("+ --------------------------------- +");
            System.out.println("|          Cirrose em Gel           |");
            System.out.println("|                                   |");
            System.out.println("|   Email inserido é invalido :(    |");
            System.out.println("|                                   |");
            System.out.println("|   Tente novamente.                |");
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");

            return emailRegisteringScreen();

        }
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

        } else {

            System.out.println("+ --------------------------------- +");
            System.out.println("|          Cirrose em Gel           |");
            System.out.println("|                                   |");
            System.out.println("|   As senhas não bateram :(        |");
            System.out.println("|                                   |");
            System.out.println("|   Tente novamente.                |");
            System.out.println("|                                   |");
            System.out.println("+ --------------------------------- +");

            return passwordRegisteringScreen();
        }
    }
}
