package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class RegisterUserCommand implements Command {
    @Override
    public String getCommand() {
        return "reg";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) {
        String login = ioh.readInput("Enter login:");
        if (ba.registerUser(login,
                ioh.readPassword("Enter password:"),
                ioh.readPassword("Confirm password:"))) {
            return "User " + login + " is created";
        } else {
            throw new IllegalArgumentException("Registration failed");
        }
    }
}
