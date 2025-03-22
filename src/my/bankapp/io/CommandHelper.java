package my.bankapp.io;

import my.bankapp.BankApp;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHelper {

//    List<Command> commandList = new ArrayList<>();
    Map<String, Command> commandMap = new HashMap<>();

    public CommandHelper() {
//        this.commandList.add(new ShowHelpCommand());
//        this.commandList.add(new ShowInfoCommand());
//        this.commandList.add(new CreateNewAccountCommand());
//        this.commandList.add(new PutMoneyCommand());
//        this.commandList.add(new SetDefaultAccountCommand());
//        this.commandList.add(new TransferMoneyCommand());
//        this.commandList.add(new WithdrawMoneyCommand());
        this.commandMap.put("help", new ShowHelpCommand());
        this.commandMap.put("info", new ShowInfoCommand());
        this.commandMap.put("register", new RegisterUserCommand());
        this.commandMap.put("newacc", new CreateNewAccountCommand());
        this.commandMap.put("send", new TransferMoneyCommand());
        this.commandMap.put("get", new WithdrawMoneyCommand());
        this.commandMap.put("put", new PutMoneyCommand());
        this.commandMap.put("setdefacc", new SetDefaultAccountCommand());
        this.commandMap.put("exit", new ExitCommand());

//        this.commandList.add(new ChangePasswordCommand());
//        this.commandList.add(new RegisterUserCommand());
//        this.commandList.add(new LoginUserCommand());
//        this.commandList.add(new LogoffUserCommand());

//        this.commandList.add(new ExitCommand());
        this.commandMap.put("default", new DefaultCommand());
    }

    public String processCommand(BankApp ba, InputOutputHelper ioh, String input) throws Exception {

        return commandMap.getOrDefault(input, commandMap.get("default")).process(ba, ioh);

//        return commandList.stream()
//                .filter(command -> command.getCommand().equals(input))
//                .findFirst()
//                .orElse(new DefaultCommand())
//                .process(ba, ioh);

//        for (Command command : commandList) {
//            if (command.getCommand().equals(input)) {
//                return command.process(ba, ioh);
//            }
//        }
//
//        return new DefaultCommand().process(ba, ioh);
    }
}
