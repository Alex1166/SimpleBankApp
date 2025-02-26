package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class LoginUserCommand implements Command {
    @Override
    public String getCommand() {
        return "login";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) {
        String login = ioh.readInput("Enter login:");
        if (ba.loginUser(login, ioh.readPassword("Enter password:"))) {
            return "Welcome, " + login;
        } else {
            throw new IllegalArgumentException("Login failed");
        }
    }
}
