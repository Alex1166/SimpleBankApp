package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputHelper;

public class CreateNewAccountCommand implements Command {
    @Override
    public String getCommand() {
        return "newacc";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) {
        if (ba.createAccount(ioh.readInput("Enter login:"), ioh.readPositiveIntegerInput("Choose account type:"))) {
            return InputOutputHelper.SUCCESS_MESSAGE;
        } else {
            throw new IllegalArgumentException("Account was not changed");
        }
    }
}
