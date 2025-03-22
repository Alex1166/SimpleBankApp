package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputService;

public class TransferMoneyCommand implements Command {
    @Override
    public String getCommand() {
        return "send";
    }

    @Override
    public String process(BankApp ba, InputOutputService ioh) throws RuntimeException {
        String login = ioh.readInput("Enter login:");

        ioh.printResult(ba.getInfo(login));

        long accountNumber = ioh.readLongInput("Choose account:");

        String recipient = ioh.readInput("Enter recipient:");

        String money = ioh.readInput("Enter money to transfer:");

        if (ba.transferMoney(login, accountNumber, money, recipient)) {
            return InputOutputService.SUCCESS_MESSAGE;
        } else {
            throw new IllegalArgumentException("Money transfer aborted");
        }
    }
}
