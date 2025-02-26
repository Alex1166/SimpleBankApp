package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class TransferMoneyCommand implements Command {
    @Override
    public String getCommand() {
        return "send";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) {
        String login = ioh.readInput("Enter login:");

        ioh.printResult(ba.getInfo(login));

        long accountNumber = ioh.readLongInput("Choose account:");

        String recipient = ioh.readInput("Enter recipient:");

        int money = ioh.readPositiveIntegerInput("Enter money to transfer:");

        if (ba.transferMoney(login, accountNumber, money, recipient)) {
            return InputOutputHelper.SUCCESS_MESSAGE;
        } else {
            throw new IllegalArgumentException("Money transfer aborted");
        }
    }
}
