package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputHelper;

public class ExitCommand implements Command{
    @Override
    public String getCommand() {
        return "exit";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) throws Exception {
        throw new InterruptedException("Closing app");
    }
}
