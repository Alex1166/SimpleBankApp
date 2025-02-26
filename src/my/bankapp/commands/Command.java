package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public interface Command {
    String getCommand();
    String process(BankApp ba, InputOutputHelper ioh) throws Exception;
}
