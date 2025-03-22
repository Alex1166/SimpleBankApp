package my.bankapp;

import my.bankapp.io.CommandHelper;
import my.bankapp.io.DaoBank;
import my.bankapp.io.DataBaseCredentials;
import my.bankapp.io.DataBaseService;
import my.bankapp.io.InputOutputHelper;

public class Main {
    public static void main(String[] args) {

        InputOutputHelper ioh = new InputOutputHelper();
        CommandHelper ch = new CommandHelper();
        DaoBank db = new DataBaseService(DataBaseCredentials.URL.getValue(), DataBaseCredentials.USER.getValue(),
                DataBaseCredentials.PASSWORD.getValue());
        BankApp ba = new BankApp(db);

        ioh.printResult(InputOutputHelper.START_MESSAGE);
        while (true) {
            try {
                ioh.printResult(ch.processCommand(ba, ioh, ioh.readInput("Enter command:")));
            } catch (InterruptedException e) {
                ioh.printResult(e.getMessage() + (e.getCause() != null ? " Cause - " + e.getCause().getMessage() : ""));
                return;
            } catch (Exception e) {
                ioh.printResult(e.getMessage() + (e.getCause() != null ? " Cause - " + e.getCause().getMessage() : ""));
            }
        }

    }
}