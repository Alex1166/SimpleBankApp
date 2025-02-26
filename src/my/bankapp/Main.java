package my.bankapp;

public class Main {
    public static void main(String[] args) {

        BankApp ba = new BankApp();
        InputOutputHelper ioh = new InputOutputHelper();
        CommandHelper ch = new CommandHelper();

        try {
            ba.registerUser("Alex", "123", "123");
            ba.createAccount("Alex");
            ba.createAccount("Alex", 1);
            ba.registerUser("Bob", "123", "123");
            ba.createAccount("Bob");
            ba.putMoney("Alex",1, 500);
            ba.putMoney("Alex",2, 200);
            ioh.printResult(ba.getInfo("Alex"));
            ioh.printResult(ba.getInfo("Bob"));
            ba.transferMoney("Alex",1, 200, "Bob");
            ioh.printResult(ba.getInfo("Alex"));
            ioh.printResult(ba.getInfo("Bob"));
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
        }

        ioh.printResult(InputOutputHelper.START_MESSAGE);
        while (true) {
//            ioh.printEnterMessage();
            try {
                ioh.printResult(ch.processCommand(ba, ioh, ioh.readInput("Enter command:")));
            } catch (InterruptedException e) {
                ioh.printResult(e.getMessage());
                return;
            } catch (Exception e) {
                ioh.printResult(e.getMessage());
            }
        }

    }
}