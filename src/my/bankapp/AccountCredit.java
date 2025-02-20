package my.bankapp;

public class AccountCredit extends AccountActive {
    public AccountCredit() {
        super();
    }

    @Override
    public String toString() {
        return "account:" + this.getAccountNumber() + "\nWallet:" + this.getWallet() + "\ndebt:" + (this.getWallet() > 0 ? 0 : Math.abs(this.getWallet())) + "\n";
    }
}
