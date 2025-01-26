package my.bankapp;

public class Main {
    public static void main(String[] args) {

        Session currentSession = new Session();

        while (true) {
            System.out.println("Hello. Type 'help' to show all available commands.");
            System.out.println("Enter command:");
            if (!currentSession.processCommand(System.console().readLine())) {
                return;
            }
        }

    }
}