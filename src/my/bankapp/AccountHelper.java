package my.bankapp;

public class AccountHelper {
    public static void doCreateAccount(User user) {
        if (user  == null) {
            System.out.println("You are not authorized");
            return;
        }

        if (user.getAccount() != null) {
            System.out.println("You already have an account");
            return;
        }

        System.out.println("Enter account name:");

        user.createAccount(System.console().readLine());
    }

    public static void doGetAccountInfo(User user) {
        if (user == null) {
            System.out.println("You are not authorized");
            return;
        }

        if (user.getAccount() == null) {
            System.out.println("You don't have an account");
            return;
        }

        user.getAccount().getAccountInfo();
    }
}
