package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.util.EmailValidator;

import java.util.Scanner;

public class RegisterScreenService {

    public static void registerScreenService() {

        String nome = nameRegisteringScreen();
        String email = emailRegisteringScreen();
        String senha;
        boolean estaAssinando;


    }

    public static String nameRegisteringScreen() {

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
    public static String emailRegisteringScreen() {

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

            return emailRegisteringScreen();

        }
    }
}
