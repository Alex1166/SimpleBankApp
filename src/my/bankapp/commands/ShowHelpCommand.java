package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputService;

public class ShowHelpCommand implements Command {

    public static final String HELP_MESSAGE = """
                available commands:\s
                 help - show help\s
                 info - show current user info\s
                 reg - create new user\s
                 newacc - create new account\s
                 setdefacc - set account as default to receive money\s
                 put - put money on your account\s
                 send - transfer money to another user\s
                 withdraw - get money from your account""";

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String process(BankApp ba, InputOutputService ioh) {
        return HELP_MESSAGE;
    }
}
