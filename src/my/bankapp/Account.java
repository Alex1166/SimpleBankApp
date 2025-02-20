package my.bankapp;

import java.util.HashMap;
import java.util.Map;

public abstract class Account {
    protected int wallet;
    private final long accountNumber;

    private static long lastAccNum = 0;
    private static final Map<Long, Account> accountMap = new HashMap<>();

    public Account() {

        this.accountNumber = ++lastAccNum;
        this.wallet = 0;

        accountMap.put(lastAccNum, this);
    }

    public long getAccountNumber() {
        return this.accountNumber;
    }

    public int getWallet() {
        return this.wallet;
    }

    @Override
    public String toString() {
        return "Account:" + this.getAccountNumber() + "\nWallet:" + this.getWallet() + "\n";
    }

    public static Map<Long, Account> getAccountMap() {
        return accountMap;
    }
}
