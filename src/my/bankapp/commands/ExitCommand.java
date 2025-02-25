package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class ExitCommand implements Command{
    @Override
    public String getCommand() {
        return "exit";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
        return false;
    }
}
