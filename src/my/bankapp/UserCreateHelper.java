package my.bankapp;

public class UserCreateHelper {
    public boolean createNewUser(String login, String password, String passwordConfirm) throws IllegalArgumentException, IllegalAccessException {
        User newUser;

        if (User.getUserLoginMap().containsKey(login)) {
            throw new IllegalArgumentException("User already exists");
        }
        newUser = new UserDefault(login);

        if (!newUser.setPassword(password, passwordConfirm)) {
            throw new IllegalAccessException("Password confirmation does not match");
        }
        return true;

    }
}
