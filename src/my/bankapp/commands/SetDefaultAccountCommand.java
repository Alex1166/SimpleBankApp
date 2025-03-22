package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputService;

public class SetDefaultAccountCommand implements Command {
    @Override
    public String getCommand() {
        return "setdefacc";
    }

    @Override
    public String process(BankApp ba, InputOutputService ioh) throws RuntimeException {
        String login = ioh.readInput("Enter login:");

        ioh.printResult(ba.getInfo(login));

        long accountNumber = ioh.readLongInput("Choose account:");

        if (ba.setUserDefaultAccount(login, accountNumber)) {
            return InputOutputService.SUCCESS_MESSAGE;
        } else {
            throw new IllegalArgumentException("Default account was not set");
        }
    }
}
