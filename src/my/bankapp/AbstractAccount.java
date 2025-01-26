package my.bankapp;

import java.util.LinkedHashSet;
import java.util.TreeSet;

public class AbstractAccount {
    private static final LinkedHashSet<Long> accountSet = new LinkedHashSet<>();
    private static long lastAccNum = 0;

    public AbstractAccount() {
        accountSet.add(++lastAccNum);
        System.out.println(lastAccNum);
    }

    public static LinkedHashSet<Long> getAccountSet() {
        return accountSet;
    }
}
