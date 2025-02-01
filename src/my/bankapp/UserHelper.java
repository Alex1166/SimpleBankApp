package my.bankapp;

import java.util.Arrays;

public class UserHelper {

    public static void doLogin(Session session) {
        if (session.getUser() != null) {
            System.out.println("You are already authorized");
            return;
        }
        System.out.println("Enter login:");
        String login = System.console().readLine();

        if (!User.userMap.containsKey(login)) {
            System.out.println("User not found");
            return;
        }

        System.out.println("Enter password:");
        String password = Arrays.toString(System.console().readPassword());

        if (User.userMap.get(login).checkPassword(password)) {
            session.setUser(User.userMap.get(login));
            System.out.println("Welcome, " + login);
        } else {
            System.out.println("Password is incorrect");
        }
    }

    private static User doCreateNewUser(String login) {
        if (User.userMap.containsKey(login)) {
            System.out.println("User already exists");
            return null;
        }
        return new User(login);
    }

    public static void doRegister(Session session) {
        if (session.getUser() != null) {
            System.out.println("You are already authorized");
            return;
        }

        User newUser;

        System.out.println("Enter login:");
        String login = System.console().readLine();
        newUser = doCreateNewUser(login);

        if (newUser == null) {
            return;
        }

        System.out.println("Enter new password:");
        String passwordNew = Arrays.toString(System.console().readPassword());
        System.out.println("Confirm new password:");
        String passwordConfirm = Arrays.toString(System.console().readPassword());

        if (newUser.setPassword(passwordNew, passwordConfirm)) {
            session.setUser(newUser);
        }

    }

    public static void doLogoff(Session session) {
        if (session.getUser() == null) {
            System.out.println("You are not authorized");
        }

        System.out.println("Are you sure? (y/n)");
        if (System.console().readLine().equals("y")) {
            System.out.println("Bye, " + session.getUser().getLogin());
            session.setUser(null);
        }
    }

    public static void doChangePassword(User user) {
        if (user == null) {
            System.out.println("You are not authorized");
            return;
        }

        System.out.println("Enter current password:");
        String passwordCurrent = Arrays.toString(System.console().readPassword());
        System.out.println("Enter new password:");
        String passwordNew = Arrays.toString(System.console().readPassword());
        System.out.println("Confirm new password:");
        String passwordConfirm = Arrays.toString(System.console().readPassword());

        user.changePassword(passwordNew, passwordConfirm, passwordCurrent);
    }

}
