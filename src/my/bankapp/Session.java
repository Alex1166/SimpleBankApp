package my.bankapp;

import java.util.Arrays;

public class Session {
    private User user;

    public void doLogin() {
        if (this.user != null) {
            System.out.println("You are already authorized");
            return;
        }
        System.out.println("Enter login:");
        String login = System.console().readLine();
        System.out.println("Enter password:");
        String password = Arrays.toString(System.console().readPassword());

        if (!AbstractUser.userMap.containsKey(login)) {
            System.out.println("User not found");
            return;
        }

       if( AbstractUser.userMap.get(login).checkPassword(password)) {
            this.user = AbstractUser.userMap.get(login);
            System.out.println("Welcome, " + login);
        } else {
            System.out.println("Password is incorrect");
        }
    }

    public void doRegister(String login) {
        this.user = new User(login);
        System.out.println("New User is created: " + login);
    }

    public void doLogoff() {
        if (this.user == null) {
            System.out.println("You are not authorized");
        }

        System.out.println("Are you sure?");
        if (System.console().readLine().equals("y")) {
            System.out.println("Bye, "  + this.user.getLogin());
            this.user = null;
        }
    }

    public void doSendMoney() {

        if (this.user == null) {
            System.out.println("You are not authorized");
            return;
        }

        if (this.user.getAccount() == null) {
            System.out.println("You don't have an account");
            return;
        }

        System.out.println("Enter recipient login:");
        String login = System.console().readLine();

        if (!AbstractUser.userMap.containsKey(login)) {
            System.out.println("Recipient not found");
            return;
        }

        User recipient = AbstractUser.userMap.get(login);
        if (recipient.getAccount() == null) {
            System.out.println("Recipient don't have an account");
            return;
        }

        System.out.println("Enter money to send:");
        long money = inputAmount();

        if (money == 0) {
            System.out.println("Putting is aborted");
            return;
        }

        if (this.user.getAccount().subtractMoney(money)) {
            recipient.getAccount().addMoney(money);
            System.out.println("transfer is complete");
        } else {
            System.out.println("transfer aborted");
        }
    }

    public void doGetMoney() {
        if (this.user == null) {
            System.out.println("You are not authorized");
            return;
        }

        if (this.user.getAccount() == null) {
            System.out.println("You don't have an account");
            return;
        }

        System.out.println("Enter money to withdraw:");
        long money = inputAmount();

        if (money == 0) {
            System.out.println("Putting is aborted");
            return;
        }

        if (this.user.getAccount().subtractMoney(money)) {
            System.out.println("withdraw is complete");
        } else {
            System.out.println("withdraw is aborted");
        }
    }

    public void doPutMoney() {

        if (this.user == null) {
            System.out.println("You are not authorized");
            return;
        }

        if (this.user.getAccount() == null) {
            System.out.println("You don't have an account");
            return;
        }

        System.out.println("Enter money to put:");
        long money = inputAmount();

        if (money == 0) {
            System.out.println("Putting is aborted");
            return;
        }

        this.user.getAccount().addMoney(money);
        System.out.println("put is complete");
    }

    public void doChangePassword() {
        if (this.user == null) {
            System.out.println("You are not authorized");
            return;
        }

        System.out.println("Enter current password:");
        String passwordCurrent = Arrays.toString(System.console().readPassword());
        System.out.println("Enter new password:");
        String passwordNew = Arrays.toString(System.console().readPassword());
        System.out.println("Confirm new password:");
        String passwordConfirm = Arrays.toString(System.console().readPassword());

        this.user.changePassword(passwordNew, passwordConfirm, passwordCurrent);
    }

    public void doCreateAccount() {
        if (this.user == null) {
            System.out.println("You are not authorized");
            return;
        }

        if (this.user.getAccount() != null) {
            System.out.println("You already have an account");
            return;
        }

        System.out.println("Enter account name:");

        this.user.createAccount(System.console().readLine());
    }

    public void doRegister() {
        if (this.user != null) {
            System.out.println("You are already authorized");
            return;
        }

        System.out.println("Enter login:");
        String login = System.console().readLine();
        this.doRegister(login);
        System.out.println("Enter new password:");
        String passwordNew = Arrays.toString(System.console().readPassword());
        System.out.println("Confirm new password:");
        String passwordConfirm = Arrays.toString(System.console().readPassword());

        this.user.setPassword(passwordNew, passwordConfirm);
    }

    public void doGetAccountInfo() {
        if (this.user == null) {
            System.out.println("You are not authorized");
            return;
        }

        if (this.user.getAccount() == null) {
            System.out.println("You don't have an account");
            return;
        }

        this.user.getAccount().getAccountInfo();
    }

    private long inputAmount() {
        while (true) {
            String s = System.console().readLine();

            try {
                long money = Long.parseLong(s);
                if (money > 0) {
                    return money;
                } else {
                    System.out.println("Enter a number more than 0 or abort operation");
                }
            } catch (NumberFormatException ex) {
                if (s.equals("abort")) {
                    return 0;
                }
                System.out.println("Enter a correct number");
            }
        }
    }

    private void doShowHelp() {
        System.out.println("available commands:");
        System.out.println(" whoami - show current user");
        System.out.println(" login - authorize in system");
        System.out.println(" logoff - logoff to switch user");
        System.out.println(" reg - create new user");
        System.out.println(" chpwd - change your password");
        System.out.println(" newacc - create new account");
        System.out.println(" myacc - show your account info");
        System.out.println(" put - put money on your account");
        System.out.println(" send - transfer money to another user");
        System.out.println(" withdraw - get money from your account");
    }

    public boolean processCommand(String command) {
        return switch (command) {
            case "exit":
                yield false;
            case "whoami":
                if (this.user != null) {
                    System.out.println("You are " + this.user.getLogin());
                } else {
                    System.out.println("You are not authorized");
                }
                yield true;
            case "login":
                this.doLogin();
                yield true;
            case "help":
                this.doShowHelp();
                yield true;
            case "logoff":
                this.doLogoff();
                yield true;
            case "reg":
                this.doRegister();
                yield true;
            case "chpwd":
                this.doChangePassword();
                yield true;
            case "newacc":
                this.doCreateAccount();
                yield true;
            case "myacc":
                this.doGetAccountInfo();
                yield true;
            case "put":
                this.doPutMoney();
                yield true;
            case "send":
                this.doSendMoney();
                yield true;
            case "withdraw":
                this.doGetMoney();
                yield true;
            default:
                System.out.println("Unknown command");
                yield true;

        };
    }
}
