package my.bankapp.services;

import my.bankapp.io.DaoBank;
import my.bankapp.model.Money;
import my.bankapp.model.User;
import my.bankapp.model.Account;

public class AccountService {

    public boolean createAccount(User user, int accountType, DaoBank dbh) throws IllegalArgumentException {
        return dbh.createNewAccount(user.getId(), accountType, new Money("0"));
    }

    public Account getAccountById(long accountId, DaoBank dbh) throws IllegalArgumentException {
        return dbh.getAccountById(accountId);
    }

    public String getAccountList(User user, DaoBank dbh) {
        StringBuilder output = new StringBuilder();

        dbh.getAccountsByUser(user.getId()).forEach(account -> output.append(account.toString()));

        return output.toString();
    }
}
