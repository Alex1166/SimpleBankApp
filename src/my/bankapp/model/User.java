package my.bankapp.model;

public class User {
    private final String login;
    private final long id;
    private final long defaultAccountNumber;

    public User(long id, String login, long defaultAccountNumber) {
        this.id = id;
        this.login = login;
        this.defaultAccountNumber = defaultAccountNumber;
    }

    public String getLogin() {
        return this.login;
    }

    public long getId() {
        return this.id;
    }

    public long getDefaultAccountNumber() throws IllegalArgumentException {
        if (this.defaultAccountNumber == -1) {
            throw new IllegalArgumentException(String.format("User %s does not have a default account", login));
        }
        return this.defaultAccountNumber;
    }

    @Override
    public String toString() {
        return "User:" + this.login + "\n";
    }
}
