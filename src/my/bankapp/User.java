package my.bankapp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class User {
    private final String login;
    private final long id;
    private final Set<Long> accountSet;
    private long defaultAccountNumber;
    private String password;

    private static long userIds = 0;
    private static final Map<Long, User> userIdMap = new HashMap<>();
    private static final Map<String, Long> userLoginMap = new HashMap<>();

    public User(String login) {
        this.id = ++userIds;
        this.login = login;
        this.accountSet = new HashSet<>();
        User.userIdMap.put(id, this);
        User.userLoginMap.put(login, id);
    }

    public String getLogin() {
        return this.login;
    }

    public long getId() {
        return this.id;
    }

    public Set<Long> getAccountSet() {
        return this.accountSet;
    }

    public static Map<Long, User> getUserIdMap() {
        return userIdMap;
    }

    public static Map<String, Long> getUserLoginMap() {
        return userLoginMap;
    }

    public void addAccount(long accountNumber) {
        if (this.accountSet.isEmpty()) {
            this.defaultAccountNumber = accountNumber;
        }
        this.accountSet.add(accountNumber);
    }

    public long getDefaultAccountNumber() throws IllegalArgumentException {
        if (this.accountSet.isEmpty()) {
            throw new IllegalArgumentException("User does not have an account");
        }
        return this.defaultAccountNumber;
    }

    public boolean setDefaultAccountNumber(long defaultAccountNumber) {
        this.defaultAccountNumber = defaultAccountNumber;
        return true;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public boolean setPassword(String password, String passwordConfirm) throws IllegalAccessException {
        if (password.equals(passwordConfirm)) {
            this.password = password;
            return true;
        } else {
            throw new IllegalAccessException("Password confirmation does not match");
        }
    }

    @Override
    public String toString() {
        return "User:" + this.login + "\n";
    }
}
