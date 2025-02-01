package my.bankapp;

import java.util.HashMap;
import java.util.Map;

public class User {
    private final String login;
    private String password;
    private Account account;
    public static Map<String, User> userMap = new HashMap<>();

    public User(String login) {
        this.login = login;
        User.userMap.put(login, this);
    }

    public String getLogin() {
        return login;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void changePassword(String password, String passwordConfirm, String passwordCurrent) {
        if (!passwordCurrent.equals(this.password)) {
            System.out.println("Current password is incorrect");
            return;
        }

        if (password.equals(passwordConfirm)) {
            this.password = password;
            System.out.println("New password is set");
        } else {
            System.out.println("Password confirmation is incorrect");
        }
    }

    public boolean setPassword(String password, String passwordConfirm) {
        if (this.password != null) {
            System.out.println("Password is already set");
            return false;
        }

        if (password.equals(passwordConfirm)) {
            this.password = password;
            System.out.println("New password is set");
            return true;
        } else {
            System.out.println("Password confirmation is incorrect");
            return false;
        }
    }

    public Account getAccount() {
        return account;
    }

    public void createAccount(String name) {
        this.account = new Account(name);
    }
}
