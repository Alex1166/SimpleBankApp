package my.bankapp;

public class MoneyHelper {

    public static void doSendMoney(User user) {

        if (user == null) {
            System.out.println("You are not authorized");
            return;
        }

        if (user.getAccount() == null) {
            System.out.println("You don't have an account");
            return;
        }

        System.out.println("Enter recipient login:");
        String login = System.console().readLine();

        if (!User.userMap.containsKey(login) || login.equals(user.getLogin())) {
            System.out.println("Recipient not found");
            return;
        }

        User recipient = User.userMap.get(login);
        if (recipient.getAccount() == null) {
            System.out.println("Recipient don't have an account");
            return;
        }

        System.out.println("Enter money to send:");
        long money = inputAmount();

        if (money == 0) {
            System.out.println("transfer aborted");
            return;
        }

        System.out.println("Are you sure you want to send $" + money + " to " + recipient.getLogin() + "? (y/n)");
        if (!System.console().readLine().equals("y")) {
            System.out.println("transfer aborted");
            return;
        }

        if (user.getAccount().subtractMoney(money)) {
            recipient.getAccount().addMoney(money);
            System.out.println("transfer is complete");
        } else {
            System.out.println("transfer aborted");
        }
    }

    public static void doGetMoney(User user) {
        if (user == null) {
            System.out.println("You are not authorized");
            return;
        }

        if (user.getAccount() == null) {
            System.out.println("You don't have an account");
            return;
        }

        System.out.println("Enter money to withdraw:");
        long money = inputAmount();

        if (money == 0) {
            System.out.println("withdraw aborted");
            return;
        }

        if (user.getAccount().subtractMoney(money)) {
            System.out.println("withdraw is complete");
        } else {
            System.out.println("withdraw aborted");
        }
    }

    public static void doPutMoney(User user) {

        if (user == null) {
            System.out.println("You are not authorized");
            return;
        }

        if (user.getAccount() == null) {
            System.out.println("You don't have an account");
            return;
        }

        System.out.println("Enter money to put:");
        long money = inputAmount();

        if (money == 0) {
            System.out.println("Putting is aborted");
            return;
        }

        user.getAccount().addMoney(money);
        System.out.println("put is complete");
    }

    private static long inputAmount() {
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
}
