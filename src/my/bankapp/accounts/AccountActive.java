package my.bankapp.accounts;

import java.util.HashMap;
import java.util.Map;

public abstract class AccountActive extends Account{

    private static final Map<Long, AccountActive> accountActiveMap = new HashMap<>();

    public AccountActive() {
        super();

        this.wallet = 0;
        accountActiveMap.put(this.getAccountNumber(), this);
    }

    public boolean setWallet(int wallet) {
        this.wallet = wallet;
        return true;
    }

    public static Map<Long, AccountActive> getAccountActiveMap() {
        return accountActiveMap;
    }
}
