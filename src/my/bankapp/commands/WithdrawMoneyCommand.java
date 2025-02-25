package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class WithdrawMoneyCommand implements Command{
    @Override
    public String getCommand() {
        return "get";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
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
            return false;
        }
        return true;
    }
}
