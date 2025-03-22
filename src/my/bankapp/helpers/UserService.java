package my.bankapp.helpers;

import my.bankapp.io.DaoBank;
import my.bankapp.model.User;

public class UserService {

    public User getUserById(long id, DaoBank dbh) throws IllegalArgumentException {
        return dbh.getUser(null, id);
    }

    public User getUserByLogin(String login, DaoBank dbh) throws RuntimeException {
        return dbh.getUser(login, -1);
    }

    public boolean createNewUser(String login, String password, String passwordConfirm, DaoBank dbh) throws RuntimeException {
        return dbh.createNewUser(login);
    }

    public boolean setDefaultAccountNumber(long userId, long defaultAccountNumber, DaoBank dbh) throws RuntimeException {
        return dbh.setUserDefaultAccount(userId, defaultAccountNumber);
    }

//    public boolean changeUserPassword(String login, String passwordCurrent, String passwordNew, String passwordConfirm) throws RuntimeException {
//        if (User.getUserLoginMap().containsKey(login)) {
//            throw new IllegalArgumentException("User already exists");
//        }
//        User user = User.getUserIdMap().get(User.getUserLoginMap().get(login));
//
//        if (user.checkPassword(passwordCurrent)) {
//            return user.setPassword(passwordNew, passwordConfirm);
//        } else {
//            return false;
//        }
//    }

}
