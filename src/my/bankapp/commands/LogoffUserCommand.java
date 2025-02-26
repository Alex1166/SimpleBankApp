package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class LogoffUserCommand implements Command {
    @Override
    public String getCommand() {
        return "logoff";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) {
        if (ba.logoffUser()) {
            return "Bye!";
        } else {
            throw new IllegalArgumentException("Password was not changed");
        }
    }
}
