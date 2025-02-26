package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class SetDefaultAccountCommand implements Command {
    @Override
    public String getCommand() {
        return "setdefacc";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) {
        String login = ioh.readInput("Enter login:");

        ioh.printResult(ba.getInfo(login));

        long accountNumber = ioh.readLongInput("Choose account:");

        if (ba.setUserDefaultAccount(login, accountNumber)) {
            return InputOutputHelper.SUCCESS_MESSAGE;
        } else {
            throw new IllegalArgumentException("Default account was not set");
        }
    }
}
