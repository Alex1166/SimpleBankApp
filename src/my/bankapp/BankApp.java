package my.bankapp;

import my.bankapp.services.AccountService;
import my.bankapp.services.MoneyService;
import my.bankapp.services.UserService;
import my.bankapp.io.DaoBank;
import my.bankapp.model.Account;
import my.bankapp.model.User;

import java.util.stream.Stream;

public class BankApp {

    private final UserService userServiceInst;
    private final AccountService accountServiceInst;
    private final MoneyService moneyServiceInst;
    private final DaoBank dataBaseServiceInst;

    public BankApp(DaoBank dataBaseServiceInst) {
        this.userServiceInst = new UserService();
        this.accountServiceInst = new AccountService();
        this.moneyServiceInst = new MoneyService();
        this.dataBaseServiceInst = dataBaseServiceInst;
    }

    public boolean registerUser(String login) throws RuntimeException {
        return userServiceInst.createNewUser(login, dataBaseServiceInst);
    }

    public boolean createAccount(String login) throws RuntimeException {
        return createAccount(login, 0);
    }

    public boolean createAccount(String login, int accountType) throws RuntimeException {
        User user = userServiceInst.getUserByLogin(login, dataBaseServiceInst);
        return accountServiceInst.createAccount(user, accountType, dataBaseServiceInst);
    }

    public boolean putMoney(String login, long accountNumber, String money) throws RuntimeException {
        if (checkUserHasAccount(login, accountNumber)) {
            Account account = accountServiceInst.getAccountById(accountNumber, dataBaseServiceInst);
            return moneyServiceInst.putMoney(account, money, dataBaseServiceInst);
        }
        return false;
    }

    public boolean withdrawMoney(String login, long accountNumber, String money) throws RuntimeException {
        if (checkUserHasAccount(login, accountNumber)) {
            Account account = accountServiceInst.getAccountById(accountNumber, dataBaseServiceInst);
            return moneyServiceInst.withdrawMoney(account, money, dataBaseServiceInst);
        }
        return false;
    }

    public boolean transferMoney(String login, long accountNumber, String money, String recipient) throws RuntimeException {
        if (checkUserHasAccount(login, accountNumber)) {
            Account account = accountServiceInst.getAccountById(accountNumber, dataBaseServiceInst);

            Account recipientAccount = accountServiceInst.getAccountById(
                    userServiceInst.getUserByLogin(recipient, dataBaseServiceInst).getDefaultAccountNumber(), dataBaseServiceInst);
            return moneyServiceInst.transferMoney(account, recipientAccount, money, dataBaseServiceInst);
        }
        return false;
    }

    public boolean setUserDefaultAccount(String login, long accountNumber) throws RuntimeException {
        User user = userServiceInst.getUserByLogin(login, dataBaseServiceInst);
        return userServiceInst.setDefaultAccountNumber(user.getId(), accountNumber, dataBaseServiceInst);
    }

    public String getInfo(String login) throws RuntimeException {
        StringBuilder output = new StringBuilder();

        User user = userServiceInst.getUserByLogin(login, dataBaseServiceInst);

        output.append(user.toString());
        output.append(accountServiceInst.getAccountList(user, dataBaseServiceInst));

        return output.toString();
    }

    public boolean checkUserHasAccount(String login, long accountNumber) throws RuntimeException {
        User user = userServiceInst.getUserByLogin(login, dataBaseServiceInst);
        Stream<Account> accountStream = dataBaseServiceInst.getAccountsByUser(user.getId());
        if (accountStream.anyMatch(account -> account.getAccountId() == accountNumber)) {
            return true;
        } else {
            throw new RuntimeException("Invalid account number");
        }
    }
}
