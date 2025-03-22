package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputHelper;

public class ShowHelpCommand implements Command {
    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) {
        return InputOutputHelper.HELP_MESSAGE;
    }
}
