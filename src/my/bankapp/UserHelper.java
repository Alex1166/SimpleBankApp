package my.bankapp;

public class UserHelper {

    UserCreateHelper userCreateHelperInst = new UserCreateHelper();
    UserChangePasswordHelper userChangePasswordHelperInst = new UserChangePasswordHelper();

    public User getUserById(long id) throws IllegalArgumentException {
        if (!User.getUserIdMap().containsKey(id)) {
            throw new IllegalArgumentException("User not found");
        }

        return User.getUserIdMap().get(id);
    }

    public User getUserByLogin(String login) throws IllegalArgumentException {
        if (!User.getUserLoginMap().containsKey(login)) {
            throw new IllegalArgumentException("User not found");
        }

        return getUserById(User.getUserLoginMap().get(login));
    }

    public boolean createNewUser(String login, String password, String passwordConfirm) throws RuntimeException {
        return userCreateHelperInst.createNewUser(login, password, passwordConfirm);
    }

    public boolean changeUserPassword(String login, String passwordCurrent, String passwordNew, String passwordConfirm) throws RuntimeException {
        return userChangePasswordHelperInst.changeUserPassword(login, passwordCurrent, passwordNew, passwordConfirm);
    }

}
