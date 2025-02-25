package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class ShowHelpCommand implements Command {
    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
        ioh.printHelpMessage();
        return true;
    }
}
