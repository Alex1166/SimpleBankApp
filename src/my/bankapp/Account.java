package my.bankapp;

import java.util.LinkedHashSet;

public class Account {
    private final String name;
    private long wallet;
    private final long accountNumber;
    private static long lastAccNum = 0;
    public static final LinkedHashSet<Long> accountSet = new LinkedHashSet<>();

    public Account(String name) {

        this.accountNumber = ++lastAccNum;
        this.name = name;
        this.wallet = 0;

        accountSet.add(lastAccNum);
        System.out.println("Your account number is : " + this.accountNumber);
    }

    public void getAccountInfo() {
        System.out.println("Your account:");
        System.out.println("Name:" + this.name);
        System.out.println("Wallet:" + this.wallet);
        System.out.println("Number:" + this.getAccountNumber());
    }

    public void addMoney(long money) {
        this.wallet = this.wallet + money;
    }

    public boolean subtractMoney(long money) {
        if (this.wallet < money) {
            System.out.println("You don't have enough money");
            return false;
        } else {
            this.wallet = this.wallet - money;
            return true;
        }
    }

    public long getAccountNumber() {
        return this.accountNumber;
    }
}
