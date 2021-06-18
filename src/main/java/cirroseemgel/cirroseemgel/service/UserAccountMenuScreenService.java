package cirroseemgel.cirroseemgel.service;

import cirroseemgel.cirroseemgel.model.dao.ComentarioDao;
import cirroseemgel.cirroseemgel.model.dao.CurtidaDao;
import cirroseemgel.cirroseemgel.model.dao.DaoFactory;
import cirroseemgel.cirroseemgel.model.dao.UsuarioDao;
import cirroseemgel.cirroseemgel.model.entities.Usuario;
import cirroseemgel.cirroseemgel.util.InvalidOptionScreen;

import java.util.Scanner;

public class UserAccountMenuScreenService {

    private static String newName;
    private static String newPassword;
    private static String newIsAssinando;

    public static void userAccountMenu() {
        Scanner scanner = new Scanner(System.in);
        int userAction;
        CurtidaDao curtidaDao = DaoFactory.createCurtidaDao();
        ComentarioDao comentarioDao = DaoFactory.createComentarioDao();
        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("     " + LoginScreenService.loggedUser.getNome() + ",");
        System.out.println("|                                   |");
        System.out.println("|    O que desejas fazer?           |");
        System.out.println("|                                   |");
        System.out.println("|    1 - Editar informações         |");
        System.out.println("|    2 - Excluir conta              |");
        System.out.println("|    3 - Voltar                     |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        userAction = scanner.nextInt();

        if (userAction == 1) {
            editarUsuario();
        } else if (userAction == 2) {
            boolean shouldDeleteAccount = confirmAccountDeletion();
            if (shouldDeleteAccount) {
                comentarioDao.deleteUserComments(LoginScreenService.loggedUser.getId());
                curtidaDao.deleteUserCurtidas(LoginScreenService.loggedUser.getId());
                usuarioDao.deleteById(LoginScreenService.loggedUser.getId());
                LoginScreenService.loggedUser = null;
                LoginScreenService.hasLoggedUser = false;
                MainScreenService.mainMenuScreen();
            }
        } else if (userAction == 3) {
            removeNewNameAndPassword();
            MainScreenService.userLoggedMainMenuScreen();
        } else {
            InvalidOptionScreen.OpcaoInvalidaScreen();
            userAccountMenu();
        }
    }

    private static boolean confirmAccountDeletion() {
        Scanner scanner = new Scanner(System.in);
        int userAction;
        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|     Tem certeza que desejas       |");
        System.out.println("|     Excluir a tua conta?          |");
        System.out.println("|                                   |");
        System.out.println("|     1 - Sim, desejo excluir.      |");
        System.out.println("|     2 - Não, quero voltar.        |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        userAction = scanner.nextInt();

        if (userAction == 1) {
            return true;
        } else if (userAction == 2) {
            return false;
        } else {
            InvalidOptionScreen.OpcaoInvalidaScreen();
            return false;
        }
    }

    public static void userEditedOrDeleted(String editedOrDeleted) {
        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("      Usuario " + editedOrDeleted);
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");
    }

    private static void editarUsuario() {
        Scanner scanner = new Scanner(System.in);
        int userAction;

        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|     O que desejas fazer?          |");
        System.out.println("|                                   |");
        System.out.println("|     1 - Mudar nome                |");
        System.out.println("|     2 - Mudar senha               |");
        System.out.println("|     3 - Mudar assinatura para     |");
        System.out.println("|         novos textos              |");
        System.out.println("|     4 - Salvar alterações         |");
        System.out.println("|     5 - Cancelar                  |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");

        userAction = scanner.nextInt();

        if (userAction == 1) {
            setNewName();
            editarUsuario();
        } else if (userAction == 2) {
            setNewPassword();
            editarUsuario();
        } else if (userAction == 3) {
            setNewIsAssinando();
            editarUsuario();
        } else if (userAction == 4) {
            if (newName != null || newPassword != null || newIsAssinando != null) {
                Usuario usuario = new Usuario();
                usuario.setId(LoginScreenService.loggedUser.getId());
                usuario.setNome(LoginScreenService.loggedUser.getNome());
                usuario.setSenha(LoginScreenService.loggedUser.getSenha());
                usuario.setEstaAssinando(LoginScreenService.loggedUser.isEstaAssinando());
                if (newName != null) {
                    usuario.setNome(newName);
                }
                if (newPassword != null) {
                    usuario.setSenha(newPassword);
                    System.out.println("Entrou");
                }
                if (newIsAssinando != null) {
                    if (newIsAssinando.equals("true")) {
                        usuario.setEstaAssinando(true);
                    } else {
                        usuario.setEstaAssinando(false);
                    }
                }
                saveUserNewInfo(usuario);
            }
        } else if (userAction == 5) {
            userAccountMenu();
            removeNewNameAndPassword();
        } else {
            InvalidOptionScreen.OpcaoInvalidaScreen();
            removeNewNameAndPassword();
            userAccountMenu();
        }
    }

    private static void setNewName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Qual será seu novo nome?        |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");
        newName = scanner.nextLine();
    }

    private static void setNewPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Qual será sua nova senha?       |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");
        newPassword = scanner.nextLine();
    }

    private static void setNewIsAssinando() {
        Scanner scanner = new Scanner(System.in);
        int userAction;
        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|   Desejas assinar ou cancelar?    |");
        System.out.println("|                                   |");
        System.out.println("|   1 - Assinar                     |");
        System.out.println("|   2 - Cancelar                    |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");
        userAction = scanner.nextInt();

        if (userAction == 1) {
            newIsAssinando = "true";
        } else if (userAction == 2){
            newIsAssinando = "false";
        } else {
            InvalidOptionScreen.OpcaoInvalidaScreen();
            removeNewNameAndPassword();
            userAccountMenu();
        }
    }

    private static void removeNewNameAndPassword() {
        newName = null;
        newPassword = null;
        newIsAssinando = null;
    }

    private static boolean getIfHasToSaveChanges(Usuario usuario) {
        boolean isNameEqualToOld = usuario.getNome().equals(LoginScreenService.loggedUser.getNome());
        boolean isPasswordEqualToOld = usuario.getSenha().equals(LoginScreenService.loggedUser.getSenha());
        boolean isAssinandoEqualToOld = usuario.isEstaAssinando() == LoginScreenService.loggedUser.isEstaAssinando();
        if (!isAssinandoEqualToOld || !isPasswordEqualToOld || !isNameEqualToOld) {
            return true;
        }
        return false;
    }

    private static void saveUserNewInfo(Usuario usuario) {
        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
        if (usuario != null) {
            if (getIfHasToSaveChanges(usuario)) {
                usuarioDao.update(usuario);
                LoginScreenService.updateLoggedUserInfo(usuario);
            } else {
                showNoneChangesToSaveMessage();
            }
            userAccountMenu();
        }
    }

    private static void showNoneChangesToSaveMessage() {
        System.out.println("+ --------------------------------- +");
        System.out.println("|          Cirrose em Gel           |");
        System.out.println("|                                   |");
        System.out.println("|    Nenhuma mudança para salvar.   |");
        System.out.println("|                                   |");
        System.out.println("+ --------------------------------- +");
    }
}
