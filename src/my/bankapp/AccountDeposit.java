package my.bankapp;

public class AccountDeposit extends AccountDebit {

    private long time;

    public AccountDeposit() {
        super();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
