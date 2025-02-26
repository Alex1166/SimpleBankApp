package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class ShowInfoCommand implements Command{
    @Override
    public String getCommand() {
        return "info";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) {
        return ba.getInfo(ioh.readInput("Enter login:"));
    }
}
