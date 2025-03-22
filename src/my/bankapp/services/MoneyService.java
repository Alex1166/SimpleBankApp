package my.bankapp.services;

import my.bankapp.io.DaoBank;
import my.bankapp.model.Account;
import my.bankapp.model.Money;

public class MoneyService {
    public boolean transferMoney(Account account, Account recipientAccount, String money, DaoBank dbh) throws IllegalArgumentException {
        if (withdrawMoney(account, money, dbh)) {
            return putMoney(recipientAccount, money, dbh);
        }
        return false;
    }

    public boolean putMoney(Account account, String money, DaoBank dbh) {
        Money addedMoney = new Money(money);
        if (addedMoney.isZeroOrLess()) {
            throw new IllegalArgumentException(String.format("Money to deposit must be greater than zero. %s provided", money));
        }
        return dbh.setAccountMoney(account.getAccountId(), this, addedMoney, Money::addValue);
    }

    public boolean withdrawMoney(Account account, String money, DaoBank dbh) throws IllegalArgumentException {
        Money subtractedMoney = new Money(money);
        if (subtractedMoney.isZeroOrLess()) {
            throw new IllegalArgumentException(String.format("Money to withdraw must be greater than zero. %s provided", money));
        }
        // нужна ли здесь проверка?
        if (subtractedMoney.compareTo(account.getWallet()) > 0) {
            throw new IllegalArgumentException("Not enough money on the account");
        }
        return dbh.setAccountMoney(account.getAccountId(), this, subtractedMoney, Money::subtractValue);
    }
}
