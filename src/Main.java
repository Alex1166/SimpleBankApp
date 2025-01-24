import my.bankapp.Session;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Session currentSession = new Session();

        while (true) {

            System.out.println("Enter command:");
            String s = System.console().readLine();

            System.out.println(s);

            switch (s) {
                case "whoami":
                    if (currentSession.getUser() != null) {
                        System.out.println("You are " + currentSession.getUser().getLogin());
                    } else {
                        System.out.println("You are guest");
                    }
                    break;
                case "exit":
                    return;
                case "login":
                    if (currentSession.getUser() == null) {
                        System.out.println("Enter login:");
                        String login = System.console().readLine();
                        System.out.println("Enter password:");
                        String password = Arrays.toString(System.console().readPassword());
                        currentSession.doLogin(login, password);
                    } else {
                        System.out.println("You are authorized");
                    }
                    break;
                case "logoff":
                    if (currentSession.getUser() != null) {
                        System.out.println("Are you sure?");
                        if (System.console().readLine().equals("y")) {
                            currentSession.doLogoff();
                        }
                    } else {
                        System.out.println("You are not authorized");
                    }
                    break;
                case "reg":
                    if (currentSession.getUser() == null) {
                        System.out.println("Enter login:");
                        String login = System.console().readLine();
                        currentSession.doRegister(login);
                        System.out.println("Enter new password:");
                        String passwordNew = Arrays.toString(System.console().readPassword());
                        System.out.println("Confirm new password:");
                        String passwordConfirm = Arrays.toString(System.console().readPassword());
                        currentSession.getUser().setPassword(passwordNew, passwordConfirm);
                    }
                    break;
                case "chpwd":
                    if (currentSession.getUser() != null) {
                        System.out.println("Enter current password:");
                        String passwordCurrent = Arrays.toString(System.console().readPassword());
                        System.out.println("Enter new password:");
                        String passwordNew = Arrays.toString(System.console().readPassword());
                        System.out.println("Confirm new password:");
                        String passwordConfirm = Arrays.toString(System.console().readPassword());
                        currentSession.getUser().changePassword(passwordNew, passwordConfirm, passwordCurrent);
                    } else {
                        System.out.println("You are not authorized");
                    }
                    break;
                case "newacc":
                    if (currentSession.getUser() != null) {
                        if (currentSession.getUser().getAccount() == null) {
                            System.out.println("Enter account name:");
                            String name = System.console().readLine();
                            currentSession.getUser().createAccount(name);
                        } else {
                            System.out.println("You already have an account");
                        }
                    } else {
                        System.out.println("You are not authorized");
                    }
                    break;
                case "myacc":
                    if (currentSession.getUser() != null) {
                        if (currentSession.getUser().getAccount() != null) {
                            System.out.println("Your account:");
                            System.out.println("Name:" + currentSession.getUser().getAccount().getName());
                            System.out.println("Wallet:" + currentSession.getUser().getAccount().getWallet());
                        } else {
                            System.out.println("You don't have an account");
                        }
                    } else {
                        System.out.println("You are not authorized");
                    }
                    break;
                case "put":
                    if (currentSession.getUser() != null) {
                        if (currentSession.getUser().getAccount() != null) {
                            System.out.println("Enter money to put:");
                            long money = Long.parseLong(System.console().readLine());
                            currentSession.doPutMoney(money);
                        } else {
                            System.out.println("You don't have an account");
                        }
                    } else {
                        System.out.println("You are not authorized");
                    }
                    break;
                case "send":
                    if (currentSession.getUser() != null) {
                        if (currentSession.getUser().getAccount() != null) {
                            System.out.println("Enter recipient login:");
                            String login = System.console().readLine();
                            System.out.println("Enter money to send:");
                            long money = Long.parseLong(System.console().readLine());
                            currentSession.doSendMoney(login, money);
                        } else {
                            System.out.println("You don't have an account");
                        }
                    } else {
                        System.out.println("You are not authorized");
                    }
                    break;
                case "withdraw":
                    if (currentSession.getUser() != null) {
                        if (currentSession.getUser().getAccount() != null) {
                            System.out.println("Enter money to withdraw:");
                            long money = Long.parseLong(System.console().readLine());
                            currentSession.doGetMoney(money);
                        } else {
                            System.out.println("You don't have an account");
                        }
                    } else {
                        System.out.println("You are not authorized");
                    }
                    break;
                default:
                    break;

            }
        }

    }
}