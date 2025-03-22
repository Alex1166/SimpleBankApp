package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputService;

public class ExitCommand implements Command{
    @Override
    public String getCommand() {
        return "exit";
    }

    @Override
    public String process(BankApp ba, InputOutputService ioh) throws Exception {
        throw new InterruptedException("Closing app");
    }
}
