package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class LogoffUserCommand implements Command{
    @Override
    public String getCommand() {
        return "logoff";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
        try {
            if (ba.logoffUser()) {
                ioh.printResult("Bye!");
            }
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
            return false;
        }
        return true;
    }
}
