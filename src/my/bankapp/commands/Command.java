package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public interface Command {
    String getCommand();
    boolean process(BankApp ba, InputOutputHelper ioh);
}
