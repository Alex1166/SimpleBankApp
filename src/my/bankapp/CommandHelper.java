package my.bankapp;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

    Map<String, Runnable> commandMap = new HashMap<>();

    public CommandHelper(BankApp ba, InputOutputHelper ioh) {
        this.commandMap.put("default", () -> processUnknownCommand(ba, ioh));
        this.commandMap.put("info", () -> processInfoCommand(ba, ioh));
        this.commandMap.put("help", () -> processHelpCommand(ba, ioh));
        this.commandMap.put("login", () -> processLoginCommand(ba, ioh));
        this.commandMap.put("logoff", () -> processLogoffCommand(ba, ioh));
        this.commandMap.put("reg", () -> processRegisterCommand(ba, ioh));
        this.commandMap.put("chpwd", () -> processChangePasswordCommand(ba, ioh));
        this.commandMap.put("newacc", () -> processCreateNewAccountCommand(ba, ioh));
        this.commandMap.put("setdefacc", () -> processSetDefaultAccountCommand(ba, ioh));
        this.commandMap.put("put", () -> processPutCommand(ba, ioh));
        this.commandMap.put("send", () -> processTransferCommand(ba, ioh));
        this.commandMap.put("withdraw", () -> processWithdrawCommand(ba, ioh));
    }

    public boolean processCommand(String command) {
        if (command.equals("exit")) {
            return false;
        }
        commandMap.getOrDefault(command, commandMap.getOrDefault("default", () -> {})).run();
        return true;
    }

    /*
    TODO:
    Все эти команды стоит разнести по отдельным классам и наследовать их от одного интерфейса
    Но пока оставил так, потому что есть другие вопросы

    TODO:
    Или создать отдельные методы для ввода под каждую команду, а в мапу поместить методы уже из BankApp
    Не уверен, как лучше это организовать
     */

    public void processUnknownCommand(BankApp ba, InputOutputHelper ioh) {
        ioh.printResult("Unknown command");
    }

    public void processInfoCommand(BankApp ba, InputOutputHelper ioh) {
        try {
            ioh.printResult(ba.getInfo(ioh.readInput("Enter login:")));
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
        }
    }

    public void processHelpCommand(BankApp ba, InputOutputHelper ioh) {
        ioh.printHelpMessage();
    }

    public void processLoginCommand(BankApp ba, InputOutputHelper ioh) {
        try {
            String login = ioh.readInput("Enter login:");
            if (ba.loginUser(login, ioh.readPassword("Enter password:"))) {
                ioh.printResult("Welcome, " + login);
            }
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
        }
    }

    public void processLogoffCommand(BankApp ba, InputOutputHelper ioh) {
        try {
            if (ba.logoffUser()) {
                ioh.printResult("Bye!");
            }
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
        }
    }

    public void processRegisterCommand(BankApp ba, InputOutputHelper ioh) {
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
    }

    public void processChangePasswordCommand(BankApp ba, InputOutputHelper ioh) {
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
    }

    public void processCreateNewAccountCommand(BankApp ba, InputOutputHelper ioh) {
        try {
            if (ba.createAccount(ioh.readInput("Enter login:"))) {
                ioh.printSuccessMessage();
            }
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
        }
    }

    public void processSetDefaultAccountCommand(BankApp ba, InputOutputHelper ioh) {
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
    }

    public void processPutCommand(BankApp ba, InputOutputHelper ioh) {
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
    }

    public void processTransferCommand(BankApp ba, InputOutputHelper ioh) {
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
    }

    public void processWithdrawCommand(BankApp ba, InputOutputHelper ioh) {
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
    }
}
