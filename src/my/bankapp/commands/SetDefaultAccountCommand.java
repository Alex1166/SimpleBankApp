package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class SetDefaultAccountCommand implements Command{
    @Override
    public String getCommand() {
        return "setdefacc";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
        try {
            String login = ioh.readInput("Enter login:");

            ioh.printResult(ba.getInfo(login));

            long accountNumber = ioh.readLongInput("Choose account:");

            if (ba.setUserDefaultAccount(login, accountNumber)) {
                ioh.printSuccessMessage();
            }
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
            return false;
        }
        return true;
    }
}
