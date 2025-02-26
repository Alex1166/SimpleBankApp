package my.bankapp;

import my.bankapp.accounts.AccountActive;

public class BankApp {

    private final UserHelper userHelperInst;
    private final AccountHelper accountHelperInst;
    private final AccountActiveHelper accountActiveHelperInst;
    private final MoneyHelper moneyHelperInst;

    public BankApp() {
        this.userHelperInst = new UserHelper();
        this.accountHelperInst = new AccountHelper();
        this.accountActiveHelperInst = new AccountActiveHelper();
        this.moneyHelperInst = new MoneyHelper();
    }

    public boolean loginUser(String login, String password) throws RuntimeException {
        User user = userHelperInst.getUserByLogin(login);
        return user.checkPassword(password);
    }

    public boolean logoffUser() {
        return true;
    }

    public boolean registerUser(String login, String password, String passwordConfirm) throws RuntimeException {
        return userHelperInst.createNewUser(login, password, passwordConfirm);
    }

    public boolean createAccount(String login) throws RuntimeException {
        return createAccount(login, 0);
    }

    public boolean createAccount(String login, int accountType) throws RuntimeException {
        User user = userHelperInst.getUserByLogin(login);
        return accountHelperInst.createAccount(user, accountType);
    }

    public boolean putMoney(String login, long accountNumber, int money) throws RuntimeException {
        if (checkUserHasAccount(login, accountNumber)) {
            AccountActive account = accountActiveHelperInst.getAccountByNumber(accountNumber);
            return moneyHelperInst.putMoney(account, money);
        }
        return false;
    }

    public boolean withdrawMoney(String login, long accountNumber, int money) throws RuntimeException {
        if (checkUserHasAccount(login, accountNumber)) {
            AccountActive account = accountActiveHelperInst.getAccountByNumber(accountNumber);
            return moneyHelperInst.withdrawMoney(account, money);
        }
        return false;
    }

    public boolean transferMoney(String login, long accountNumber, int money, String recipient) throws RuntimeException {
        if (checkUserHasAccount(login, accountNumber)) {
            AccountActive account = accountActiveHelperInst.getAccountByNumber(accountNumber);

            AccountActive recipientAccount = accountActiveHelperInst.getAccountByNumber(userHelperInst.getUserByLogin(recipient).getDefaultAccountNumber());
            return moneyHelperInst.transferMoney(account, recipientAccount, money);
        }
        return false;
    }

    public boolean changePasswordUser(String login, String passwordCurrent, String passwordNew, String passwordConfirm) throws RuntimeException {
        return userHelperInst.changeUserPassword(login, passwordCurrent, passwordNew, passwordConfirm);
    }

    public boolean setUserDefaultAccount(String login, long accountNumber) throws RuntimeException {
        User user = userHelperInst.getUserByLogin(login);
        return user.setDefaultAccountNumber(accountNumber);
    }

    public String getInfo(String login) throws RuntimeException {
        StringBuilder output = new StringBuilder();

        User user = userHelperInst.getUserByLogin(login);

        output.append(user.toString());
        output.append(accountHelperInst.getAccountList(user));

        return output.toString();
    }

    public boolean checkUserHasAccount(String login, long accountNumber) throws RuntimeException {
        User user = userHelperInst.getUserByLogin(login);
        if (!user.getAccountSet().contains(accountNumber)) {
            throw new RuntimeException("Invalid account number");
        }
        return true;
    }
}
