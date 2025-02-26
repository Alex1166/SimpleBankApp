package my.bankapp;

import my.bankapp.commands.ChangePasswordCommand;
import my.bankapp.commands.Command;
import my.bankapp.commands.CreateNewAccountCommand;
import my.bankapp.commands.DefaultCommand;
import my.bankapp.commands.ExitCommand;
import my.bankapp.commands.LoginUserCommand;
import my.bankapp.commands.LogoffUserCommand;
import my.bankapp.commands.PutMoneyCommand;
import my.bankapp.commands.RegisterUserCommand;
import my.bankapp.commands.SetDefaultAccountCommand;
import my.bankapp.commands.ShowHelpCommand;
import my.bankapp.commands.ShowInfoCommand;
import my.bankapp.commands.TransferMoneyCommand;
import my.bankapp.commands.WithdrawMoneyCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandHelper {

    List<Command> commandList = new ArrayList<>();

    public CommandHelper() {
        this.commandList.add(new ShowHelpCommand());
        this.commandList.add(new ShowInfoCommand());
        this.commandList.add(new CreateNewAccountCommand());
        this.commandList.add(new PutMoneyCommand());
        this.commandList.add(new SetDefaultAccountCommand());
        this.commandList.add(new TransferMoneyCommand());
        this.commandList.add(new WithdrawMoneyCommand());
        this.commandList.add(new ChangePasswordCommand());
        this.commandList.add(new RegisterUserCommand());
        this.commandList.add(new LoginUserCommand());
        this.commandList.add(new LogoffUserCommand());
        this.commandList.add(new ExitCommand());
    }

    public String processCommand(BankApp ba, InputOutputHelper ioh, String input) throws Exception {

        return commandList.stream()
                .filter(command -> command.getCommand().equals(input))
                .findFirst()
                .orElse(new DefaultCommand())
                .process(ba, ioh);

//        for (Command command : commandList) {
//            if (command.getCommand().equals(input)) {
//                return command.process(ba, ioh);
//            }
//        }
//
//        return new DefaultCommand().process(ba, ioh);
    }
}
