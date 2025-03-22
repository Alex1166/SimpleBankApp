package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputService;

public class RegisterUserCommand implements Command {
    @Override
    public String getCommand() {
        return "reg";
    }

    @Override
    public String process(BankApp ba, InputOutputService ioh) throws RuntimeException {
        String login = ioh.readInput("Enter login:");
        if (ba.registerUser(login)) {
            return "User " + login + " is created";
        } else {
            throw new IllegalArgumentException("Registration failed");
        }
    }
}
