package my.bankapp.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Account {

    private Money wallet;
    private final int type;
    private final long accountId;

    public Account(long accountId) {
        this(accountId, 0, "0");
    }

    public Account(long accountId, int type) {
        this(accountId, type, "0");
    }

    public Account(long accountId, int type, String wallet) {
        if (type > 3) {
            throw new IllegalArgumentException("Wrong account type: " + type);
        }
        this.setWallet(wallet);

        this.type = type;
        this.accountId = accountId;
    }

    public long getAccountId() {
        return this.accountId;
    }

    public Money getWallet() {
        return this.wallet;
    }

    public boolean setWallet(Money wallet) {
        this.wallet = wallet;
        return true;
    }

    public boolean setWallet(String wallet) {
        this.wallet = new Money(wallet);
        return true;
    }

    public int getType() {
        return type;
    }

    public boolean addMoney(String money) {
        Money addedMoney = new Money(money);
        return this.setWallet(this.getWallet().addValue(addedMoney));
    }

    public boolean subtractMoney(String money) {
        Money subtractedMoney = new Money(money);
        if (subtractedMoney.compareTo(this.wallet) > 0) {
            throw new IllegalArgumentException("Not enough money on the account");
        }
        return this.setWallet(this.getWallet().subtractValue(subtractedMoney));
    }

    @Override
    public String toString() {
        return "Account:" + this.getAccountId() + "\nWallet:" + this.getWallet() + "\n";
    }
}
