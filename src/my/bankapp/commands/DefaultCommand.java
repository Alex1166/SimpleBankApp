package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputHelper;

public class DefaultCommand implements Command{
    @Override
    public String getCommand() {
        return "default";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) {
        return"Unknown command";
    }
}
