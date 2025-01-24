package my.bankapp;

public class User extends AbstractUser {
    private final String login;
    private String password;
    private Account account;

    public User(String login) {
        super();

        this.login = login;
        AbstractUser.userMap.put(this.login, this);
    }

    public String getLogin() {
        return login;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void changePassword(String password, String passwordConfirm, String passwordCurrent) {
        if (!passwordCurrent.equals(this.password)) {
            System.out.println("Current password is incorrect");
            return;
        }

        if (password.equals(passwordConfirm)) {
            this.password = password;
            System.out.println("New password is set");
        } else {
            System.out.println("Password confirmation is incorrect");
        }
    }

    public void setPassword(String password, String passwordConfirm) {
        if (this.password != null) {
            System.out.println("Password is already set");
            return;
        }

        if (password.equals(passwordConfirm)) {
            this.password = password;
            System.out.println("New password is set");
        } else {
            System.out.println("Password confirmation is incorrect");
        }
    }

    public Account getAccount() {
        return account;
    }

    public void createAccount(String name) {
        this.account = new Account(name);
    }
}
