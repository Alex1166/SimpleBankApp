package my.bankapp;

public class Session {
    private User user;

    public User getUser() {
        return user;
    }

    public void doLogin(String login, String password) {
        if (AbstractUser.userMap.containsKey(login)) {
            if (AbstractUser.userMap.get(login).getPassword().equals(password)) {
                this.user = AbstractUser.userMap.get(login);
                System.out.println("Welcome, " + login);
            } else {
                System.out.println("Password is incorrect");
            }
        } else {
            System.out.println("User not found");
        }
    }

    public void doRegister(String login) {
        this.user = new User(login);
        System.out.println("New User is created: " + login);
    }

    public void doLogoff() {
        this.user = null;
    }

    public void doSendMoney(String login, long money) {
        if (this.user != null && AbstractUser.userMap.containsKey(login)) {
            User recipient = AbstractUser.userMap.get(login);
            if (getAccount() != null && recipient.getAccount() != null) {
                if (this.user.getAccount().subtractMoney(money)) {
                    recipient.getAccount().addMoney(money);
                    System.out.println("transfer is complete");
                }
            }
        }
    }

    public void doGetMoney(long money) {
        if (this.user != null) {
            if (getAccount() != null) {
                if (this.user.getAccount().subtractMoney(money)) {
                    System.out.println("withdraw is complete");
                }
            }
        }
    }

    public void doPutMoney(long money) {
        if (this.user != null) {
            if (getAccount() != null) {
                this.user.getAccount().addMoney(money);
                System.out.println("put is complete");
            }
        }
    }

    private Account getAccount() {
        return this.user.getAccount();
    }
}
