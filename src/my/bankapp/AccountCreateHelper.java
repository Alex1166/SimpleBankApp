package my.bankapp;

public class AccountCreateHelper {
    public boolean createAccount(User user, int accountType) {
        Account account = switch (accountType) {
            case 0 -> new AccountDebit();
            case 1 -> new AccountCredit();
            case 2 -> new AccountDeposit();
            default -> new AccountDebit();
        };
        user.addAccount(account.getAccountNumber());
        return true;
    }
}
