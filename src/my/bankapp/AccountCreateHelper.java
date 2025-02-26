package my.bankapp;

import my.bankapp.accounts.Account;
import my.bankapp.accounts.AccountCredit;
import my.bankapp.accounts.AccountDebit;
import my.bankapp.accounts.AccountDeposit;

public class AccountCreateHelper {
    public boolean createAccount(User user, int accountType) {
        Account account = switch (accountType) {
            case 0 -> new AccountDebit();
            case 1 -> new AccountCredit();
            case 2 -> new AccountDeposit();
            default -> throw new IllegalArgumentException("Wrong account type");
        };
        user.addAccount(account.getAccountNumber());
        return true;
    }
}
