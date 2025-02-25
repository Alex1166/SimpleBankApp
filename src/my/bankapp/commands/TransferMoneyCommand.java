package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class TransferMoneyCommand implements Command{
    @Override
    public String getCommand() {
        return "send";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
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
            return false;
        }
        return true;
    }
}
