package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class ShowInfoCommand implements Command{
    @Override
    public String getCommand() {
        return "info";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
        try {
            ioh.printResult(ba.getInfo(ioh.readInput("Enter login:")));
            return true;
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
            return false;
        }
    }
}
