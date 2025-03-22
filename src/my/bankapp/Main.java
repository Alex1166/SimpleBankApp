package my.bankapp;

import my.bankapp.io.CommandHelper;
import my.bankapp.io.DaoBank;
import my.bankapp.io.DataBaseCredentials;
import my.bankapp.io.DataBaseService;
import my.bankapp.io.InputOutputHelper;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        InputOutputHelper ioh = new InputOutputHelper();
        CommandHelper ch = new CommandHelper();
        DaoBank db = new DataBaseService(DataBaseCredentials.URL.getValue(), DataBaseCredentials.USER.getValue(),
                DataBaseCredentials.PASSWORD.getValue());
        BankApp ba = new BankApp(db);

//        try {
//            ba.registerUser("Alex", "123", "123");
//            ba.createAccount("Alex");
//            ba.createAccount("Alex", 1);
//            ba.registerUser("Bob", "123", "123");
//            ba.createAccount("Bob");
//            ba.putMoney("Alex",1, "500");
//            ba.putMoney("Alex",2, "200");
//            ioh.printResult(ba.getInfo("Alex"));
//            ioh.printResult(ba.getInfo("Bob"));
//            ba.transferMoney("Alex",1, "200", "Bob");
//            ioh.printResult(ba.getInfo("Alex"));
//            ioh.printResult(ba.getInfo("Bob"));
//        } catch (Exception e) {
//            ioh.printResult(e.getMessage());
//        }

        ioh.printResult(InputOutputHelper.START_MESSAGE);
        while (true) {
//            ioh.printEnterMessage();
            try {
                ioh.printResult(ch.processCommand(ba, ioh, ioh.readInput("Enter command:")));
            } catch (InterruptedException e) {
                ioh.printResult(e.getMessage() + (e.getCause() != null ? " Cause - " + e.getCause().getMessage() : ""));
                return;
            } catch (Exception e) {
//                e.printStackTrace();
                ioh.printResult(e.getMessage() + (e.getCause() != null ? " Cause - " + e.getCause().getMessage() : ""));
            }
        }

    }
}