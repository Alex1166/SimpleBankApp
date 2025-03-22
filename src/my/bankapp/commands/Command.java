package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputService;

public interface Command {
    String getCommand();
    String process(BankApp ba, InputOutputService ioh) throws Exception;
}
