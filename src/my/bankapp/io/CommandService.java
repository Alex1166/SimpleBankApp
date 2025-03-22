package my.bankapp.io;

import my.bankapp.BankApp;
import my.bankapp.commands.Command;
import my.bankapp.commands.CreateNewAccountCommand;
import my.bankapp.commands.DefaultCommand;
import my.bankapp.commands.ExitCommand;
import my.bankapp.commands.PutMoneyCommand;
import my.bankapp.commands.RegisterUserCommand;
import my.bankapp.commands.SetDefaultAccountCommand;
import my.bankapp.commands.ShowHelpCommand;
import my.bankapp.commands.ShowInfoCommand;
import my.bankapp.commands.TransferMoneyCommand;
import my.bankapp.commands.WithdrawMoneyCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandService {

    Map<String, Command> commandMap = new HashMap<>();

    public CommandService() {
        this.commandMap.put("help", new ShowHelpCommand());
        this.commandMap.put("info", new ShowInfoCommand());
        this.commandMap.put("register", new RegisterUserCommand());
        this.commandMap.put("newacc", new CreateNewAccountCommand());
        this.commandMap.put("send", new TransferMoneyCommand());
        this.commandMap.put("get", new WithdrawMoneyCommand());
        this.commandMap.put("put", new PutMoneyCommand());
        this.commandMap.put("setdefacc", new SetDefaultAccountCommand());
        this.commandMap.put("exit", new ExitCommand());
        this.commandMap.put("default", new DefaultCommand());
    }

    public String processCommand(BankApp ba, InputOutputService ioh, String input) throws Exception {
        return commandMap.getOrDefault(input, commandMap.get("default")).process(ba, ioh);
    }
}
