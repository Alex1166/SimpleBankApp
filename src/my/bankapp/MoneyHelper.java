package my.bankapp;

public class MoneyHelper {
    public boolean transferMoney(AccountActive account, AccountActive recipientAccount, int money) throws IllegalArgumentException {
        if (withdrawMoney(account, money)) {
            return putMoney(recipientAccount, money);
        }
        return false;
    }

    public boolean putMoney(AccountActive account, int money) {
        return account.setWallet(account.getWallet() + money);
    }

    public boolean withdrawMoney(AccountActive account, int money) throws IllegalArgumentException {
        return account.setWallet(account.getWallet() - money);
    }
}
