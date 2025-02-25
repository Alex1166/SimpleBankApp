package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class LoginUserCommand implements Command{
    @Override
    public String getCommand() {
        return "login";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
        try {
            String login = ioh.readInput("Enter login:");
            if (ba.loginUser(login, ioh.readPassword("Enter password:"))) {
                ioh.printResult("Welcome, " + login);
            }
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
            return false;
        }
        return true;
    }
}
