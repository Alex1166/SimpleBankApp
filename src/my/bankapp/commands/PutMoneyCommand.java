package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class PutMoneyCommand implements Command{
    @Override
    public String getCommand() {
        return "put";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) {
            String login = ioh.readInput("Enter login:");

            ioh.printResult(ba.getInfo(login));

            long accountNumber = ioh.readLongInput("Choose account:");

            int money = ioh.readPositiveIntegerInput("Enter money to put:");

            if (ba.putMoney(login, accountNumber, money)) {
                return InputOutputHelper.SUCCESS_MESSAGE;
            } else {
                throw new IllegalArgumentException("Password was not changed");
            }
    }
}
