package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputService;

public class WithdrawMoneyCommand implements Command{
    @Override
    public String getCommand() {
        return "get";
    }

    @Override
    public String process(BankApp ba, InputOutputService ioh) {
            String login = ioh.readInput("Enter login:");

            ioh.printResult(ba.getInfo(login));

            long accountNumber = ioh.readLongInput("Choose account:");

            String money = ioh.readInput("Enter money to withdraw:");

            if (ba.withdrawMoney(login, accountNumber, money)) {
                return InputOutputService.SUCCESS_MESSAGE;
            } else {
                throw new IllegalArgumentException("Money withdrawal aborted");
            }
    }
}
