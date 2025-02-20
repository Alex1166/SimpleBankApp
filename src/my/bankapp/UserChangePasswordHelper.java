package my.bankapp;

public class UserChangePasswordHelper {
    public boolean changeUserPassword(String login, String passwordCurrent, String passwordNew, String passwordConfirm) throws IllegalArgumentException, IllegalAccessException {
        if (!User.getUserLoginMap().containsKey(login)) {
            throw new IllegalArgumentException("User already exists");
        }
        User user = User.getUserIdMap().get(User.getUserLoginMap().get(login));

        if (user.checkPassword(passwordCurrent)) {
            return user.setPassword(passwordNew, passwordConfirm);
        } else {
            throw new IllegalAccessException("Password is incorrect");
        }
    }
}
