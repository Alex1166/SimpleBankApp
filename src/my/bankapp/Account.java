package my.bankapp;

public class Account extends AbstractAccount {
    private long accountNumber;
    private long wallet;
    private String name;

    public Account(String name) {
        super();

        this.name = name;
        this.wallet = 0;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getWallet() {
        return wallet;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
