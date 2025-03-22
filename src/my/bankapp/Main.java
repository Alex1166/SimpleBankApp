package my.bankapp;

import my.bankapp.io.CommandService;
import my.bankapp.io.DaoBank;
import my.bankapp.io.DataBaseCredentials;
import my.bankapp.io.DataBaseService;
import my.bankapp.io.InputOutputService;

public class Main {
    public static void main(String[] args) {

        InputOutputService ioh = new InputOutputService();
        CommandService ch = new CommandService();
        DaoBank db = new DataBaseService(DataBaseCredentials.URL.getValue(), DataBaseCredentials.USER.getValue(),
                DataBaseCredentials.PASSWORD.getValue());
        BankApp ba = new BankApp(db);

        ioh.printResult(InputOutputService.START_MESSAGE);
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