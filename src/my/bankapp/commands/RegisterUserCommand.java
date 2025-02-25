package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class RegisterUserCommand implements Command{
    @Override
    public String getCommand() {
        return "reg";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
        try {
            String login = ioh.readInput("Enter login:");
            if (ba.registerUser(login,
                    ioh.readPassword("Enter password:"),
                    ioh.readPassword("Confirm password:"))) {
                ioh.printResult("User " + login + " is created");
            }
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
            return false;
        }
        return true;
    }
}
