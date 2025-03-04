package my.bankapp;

import my.bankapp.accounts.AccountActive;

public class AccountActiveHelper extends AccountHelper{
    public AccountActive getAccountByNumber(long accountNumber) throws IllegalArgumentException {
        if (!AccountActive.getAccountActiveMap().containsKey(accountNumber)) {
            throw new IllegalArgumentException("not found");
        }
        return AccountActive.getAccountActiveMap().get(accountNumber);
    }
}
