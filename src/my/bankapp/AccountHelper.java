package my.bankapp;

public class AccountHelper {
    AccountCreateHelper accountCreateHelperInstance = new AccountCreateHelper();

    public boolean createAccount(User user, int accountType) {
        return accountCreateHelperInstance.createAccount(user, accountType);
    }

    public Account getAccountByNumber(long accountNumber) throws IllegalArgumentException {
        if (!Account.getAccountMap().containsKey(accountNumber)) {
            throw new IllegalArgumentException("not found");
        }
        return Account.getAccountMap().get(accountNumber);
    }

    public String getAccountList(User user) {
        StringBuilder output = new StringBuilder();
        for (long accountNumber : user.getAccountSet()) {
            try {
                output.append(getAccountByNumber(accountNumber).toString());
            } catch (Exception e) {
                output.append("Account ").append(accountNumber).append(" - ").append(e.getMessage());
            }
        }
        return output.toString();
    }
}
