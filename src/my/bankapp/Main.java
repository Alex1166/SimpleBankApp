package my.bankapp;

public class Main {
    public static void main(String[] args) {

        Session currentSession = new Session();

        System.out.println("Hello. Type 'help' to show all available commands.");

        while (true) {
            System.out.println("Enter command:");
            if (!CommandHelper.processCommand(currentSession, System.console().readLine())) {
                return;
            }
        }

    }
}