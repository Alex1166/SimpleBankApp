package my.bankapp;

import java.io.IOError;
import java.util.Arrays;

public class InputOutputHelper {

    public String readInput(String requestLine) throws IllegalArgumentException {
        printResult(requestLine);

        String s = System.console().readLine();

        if (s == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        return s;
    }

    public int readIntegerInput(String requestLine) throws IllegalArgumentException {
        try {
            return Integer.parseInt(readInput(requestLine));
        } catch (IllegalArgumentException e) {
            throw new NumberFormatException("Input number is invalid");
        }
    }

    public int readPositiveIntegerInput(String requestLine) throws IllegalArgumentException {
        int i = readIntegerInput(requestLine);

        if (i < 0) {
            throw new NumberFormatException("Input number is invalid");
        }
        return i;
    }

    public long readLongInput(String requestLine) throws NumberFormatException {
        try {
            return Long.parseLong(readInput(requestLine));
        } catch (IllegalArgumentException e) {
            throw new NumberFormatException("Input number is invalid");
        }
    }

    public String readPassword(String requestLine) throws Exception {
        printResult(requestLine);
        try {
            return Arrays.toString(System.console().readPassword());
        } catch (IOError e) {
            throw new Exception("Input is invalid");
        }
    }

    public void printStartMessage() {
        System.out.println("Hello. Type 'help' to show all available commands.");
    }

//    public void printEnterMessage() {
//        System.out.println("Enter command:");
//    }

    public void printSuccessMessage() {
        System.out.println("The operation completed successfully");
    }

    public void printHelpMessage() {
        System.out.println("""
                available commands:\s
                 whoami - show current user\s
                 login - authorize in system\s
                 logoff - logoff to switch user\s
                 reg - create new user\s
                 chpwd - change your password\s
                 newacc - create new account\s
                 myacc - show your account info\s
                 put - put money on your account\s
                 send - transfer money to another user\s
                 withdraw - get money from your account""");
    }

    public void printResult(String input) {
        System.out.println(input);
    }

}
