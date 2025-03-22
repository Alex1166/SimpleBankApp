package my.bankapp.model;

public class Account {
    private final Money wallet;
    private final int type;
    private final long accountId;

    public Account(long accountId, int type, String wallet) {
        if (type > 3) {
            throw new IllegalArgumentException("Wrong account type: " + type);
        }

        this.wallet = new Money(wallet);
        this.type = type;
        this.accountId = accountId;
    }

    public long getAccountId() {
        return this.accountId;
    }

    public Money getWallet() {
        return this.wallet;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Account:" + this.getAccountId() + "\nWallet:" + this.getWallet() + "\n";
    }
}
