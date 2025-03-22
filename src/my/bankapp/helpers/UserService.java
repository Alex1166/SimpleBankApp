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

    public boolean createNewUser(String login, DaoBank dbh) throws RuntimeException {
        return dbh.createNewUser(login);
    }

    public boolean setDefaultAccountNumber(long userId, long defaultAccountNumber, DaoBank dbh) throws RuntimeException {
        return dbh.setUserDefaultAccount(userId, defaultAccountNumber);
    }
}
