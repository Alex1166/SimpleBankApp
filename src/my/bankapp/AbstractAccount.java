package my.bankapp;

import java.util.TreeSet;

public class AbstractAccount {
    private static final TreeSet<Long> accountSet = new TreeSet<>();

    public AbstractAccount() {
        long newAccNum = 0;
        if (!accountSet.isEmpty()) {
            newAccNum = accountSet.last() + 1;
        }
        accountSet.add(newAccNum);
        System.out.println(getAccountSet().last());
    }

    public static TreeSet<Long> getAccountSet() {
        return accountSet;
    }
}
