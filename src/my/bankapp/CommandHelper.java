package my.bankapp;

public class CommandHelper {

    public boolean processCommand(BankApp ba, InputOutputHelper ioh, String command) {
        return switch (command) {
            case "exit":
                yield false;
            case "help":
                ioh.printHelpMessage();
                yield true;
            case "info":
                try {
                    ioh.printResult(ba.getInfo(ioh.readInput("Enter login:")));
                } catch (Exception e) {
                    ioh.printResult(e.getMessage());
                }
                yield true;
            case "login":

                try {
                    String login = ioh.readInput("Enter login:");
                    if (ba.loginUser(login, ioh.readPassword("Enter password:"))) {
                        ioh.printResult("Welcome, " + login);
                    }
                } catch (Exception e) {
                    ioh.printResult(e.getMessage());
                }
                yield true;
            case "logoff":
                try {
                    if (ba.logoffUser()) {
                        ioh.printResult("Bye!");
                    }
                } catch (Exception e) {
                    ioh.printResult(e.getMessage());
                }
                yield true;
            case "reg":
                try {
                    String login = ioh.readInput("Enter login:");
                    if (ba.registerUser(login,
                            ioh.readPassword("Enter password:"),
                            ioh.readPassword("Confirm password:"))) {
                        ioh.printResult("User " + login + " is created");
                    }
                } catch (Exception e) {
                    ioh.printResult(e.getMessage());
                }
                yield true;
            case "chpwd":
                try {
                    if (ba.changePasswordUser(ioh.readInput("Enter login:"),
                            ioh.readPassword("Enter current password:"),
                            ioh.readPassword("Enter new password:"),
                            ioh.readPassword("Confirm current password:"))) {
                        ioh.printSuccessMessage();
                    }
                } catch (Exception e) {
                    ioh.printResult(e.getMessage());
                }
                yield true;
            case "newacc":
                try {
                    if (ba.createAccount(ioh.readInput("Enter login:"))) {
                        ioh.printSuccessMessage();
                    }
                } catch (Exception e) {
                    ioh.printResult(e.getMessage());
                }
                yield true;
            case "setdefacc":
                try {
                    String login = ioh.readInput("Enter login:");

                    ioh.printResult(ba.getInfo(login));

                    long accountNumber = ioh.readLongInput("Choose account:");

                    if (ba.setUserDefaultAccount(login, accountNumber)) {
                        ioh.printSuccessMessage();
                    }
                } catch (Exception e) {
                    ioh.printResult(e.getMessage());
                }
                yield true;
            case "put":
                try {
                    String login = ioh.readInput("Enter login:");

                    ioh.printResult(ba.getInfo(login));

                    long accountNumber = ioh.readLongInput("Choose account:");

                    int money = ioh.readPositiveIntegerInput("Enter money to put:");

                    if (ba.putMoney(login, accountNumber, money)) {
                        ioh.printSuccessMessage();
                    }
                } catch (Exception e) {
                    ioh.printResult(e.getMessage());
                }
                yield true;
            case "send":
                try {
                    String login = ioh.readInput("Enter login:");

                    ioh.printResult(ba.getInfo(login));

                    long accountNumber = ioh.readLongInput("Choose account:");

                    String recipient = ioh.readInput("Enter recipient:");

                    int money = ioh.readPositiveIntegerInput("Enter money to transfer:");

                    if (ba.transferMoney(login, accountNumber, money, recipient)) {
                        ioh.printSuccessMessage();
                    }
                } catch (Exception e) {
                    ioh.printResult(e.getMessage());
                }
                yield true;
            case "withdraw":
                try {
                    String login = ioh.readInput("Enter login:");

                    ioh.printResult(ba.getInfo(login));

                    long accountNumber = ioh.readLongInput("Choose account:");

                    int money = ioh.readPositiveIntegerInput("Enter money to withdraw:");

                    if (ba.withdrawMoney(login, accountNumber, money)) {
                        ioh.printSuccessMessage();
                    }
                } catch (Exception e) {
                    ioh.printResult(e.getMessage());
                }
                yield true;
            default:
                ioh.printResult("Unknown command");
                yield true;
        };
    }
}
