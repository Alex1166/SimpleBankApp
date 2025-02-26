package my.bankapp;

import java.util.InputMismatchException;

public class UserCreateHelper {
    public boolean createNewUser(String login, String password, String passwordConfirm) throws RuntimeException {
        User newUser;

        if (User.getUserLoginMap().containsKey(login)) {
            throw new IllegalArgumentException("User already exists");
        }
        newUser = new UserDefault(login);

        if (!newUser.setPassword(password, passwordConfirm)) {
            throw new InputMismatchException("Password confirmation does not match");
        }
        return true;

    }
}
