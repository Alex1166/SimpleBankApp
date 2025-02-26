package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class WithdrawMoneyCommand implements Command{
    @Override
    public String getCommand() {
        return "get";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) {
            String login = ioh.readInput("Enter login:");

            ioh.printResult(ba.getInfo(login));

            long accountNumber = ioh.readLongInput("Choose account:");

            int money = ioh.readPositiveIntegerInput("Enter money to withdraw:");

            if (ba.withdrawMoney(login, accountNumber, money)) {
                return InputOutputHelper.SUCCESS_MESSAGE;
            } else {
                throw new IllegalArgumentException("Money transfer aborted");
            }
    }
}
