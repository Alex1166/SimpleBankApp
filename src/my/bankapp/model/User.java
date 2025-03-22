package my.bankapp.model;

import java.util.InputMismatchException;

public class User {
    private final String login;
    private final long id;
    private final long defaultAccountNumber;
    private String password;

    public User(long id, String login) {
        this(id, login, -1);
    }
    public User(long id, String login, long defaultAccountNumber) {
        this.id = id;
        this.login = login;
        this.password = "123";
        this.defaultAccountNumber = defaultAccountNumber;
    }

    public String getLogin() {
        return this.login;
    }

    public long getId() {
        return this.id;
    }

    public long getDefaultAccountNumber() throws IllegalArgumentException {
        if (this.defaultAccountNumber == -1) {
            throw new IllegalArgumentException(String.format("User %s does not have a default account", login));
        }
        return this.defaultAccountNumber;
    }

    public boolean checkPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        } else {
            throw new InputMismatchException("Password is incorrect");
        }
    }

//    public boolean setPassword(String password, String passwordConfirm) throws RuntimeException {
//        if (password.equals(passwordConfirm)) {
//            this.password = password;
//            return true;
//        } else {
//            throw new InputMismatchException("Password confirmation does not match");
//        }
//    }

    @Override
    public String toString() {
        return "User:" + this.login + "\n";
    }
}
