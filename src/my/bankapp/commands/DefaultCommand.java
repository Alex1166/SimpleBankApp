package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputService;

public class DefaultCommand implements Command{
    @Override
    public String getCommand() {
        return "default";
    }

    @Override
    public String process(BankApp ba, InputOutputService ioh) {
        return"Unknown command";
    }
}
