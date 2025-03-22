package my.bankapp.io;

import java.util.Scanner;

public class InputOutputService {

    public static final String SUCCESS_MESSAGE = "The operation completed successfully";

    public static final String START_MESSAGE = "Hello. Type 'help' to show all available commands.";

    public String readInput(String requestLine) throws IllegalArgumentException {
        printResult(requestLine);

        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

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

    public void printResult(String input) {
        if (input != null) {
            System.out.println(input);
        }
    }

}
