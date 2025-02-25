package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class CreateNewAccountCommand implements Command{
    @Override
    public String getCommand() {
        return "newacc";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
        try {
            if (ba.createAccount(ioh.readInput("Enter login:"))) {
                ioh.printSuccessMessage();
            }
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
            return false;
        }
        return true;
    }
}
