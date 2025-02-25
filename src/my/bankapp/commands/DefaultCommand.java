package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class DefaultCommand implements Command{
    @Override
    public String getCommand() {
        return "default";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
        ioh.printResult("Unknown command");
        return true;
    }
}
