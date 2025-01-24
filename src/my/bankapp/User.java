package my.bankapp;

public class User extends AbstractUser {
    private String login;
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

    private void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String password, String passwordConfirm, String passwordCurrent) {
        if (passwordCurrent.equals(this.password)) {
            if (password.equals(passwordConfirm)) {
                this.password = password;
                System.out.println("New password is set");
            } else {
                System.out.println("Password confirmation is incorrect");
            }
        } else {
            System.out.println("Current password is incorrect");
        }
    }

    public void setPassword(String password, String passwordConfirm) {
        if (this.password == null) {
            if (password.equals(passwordConfirm)) {
                this.password = password;
                System.out.println("New password is set");
            } else {
                System.out.println("Password confirmation is incorrect");
            }
        } else {
            System.out.println("Password is already set");
        }
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void createAccount(String name) {
        this.account = new Account(name);
    }
}
