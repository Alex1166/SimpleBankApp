package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.io.InputOutputHelper;

public class ChangePasswordCommand implements Command {
    @Override
    public String getCommand() {
        return "chpwd";
    }

    @Override
    public String process(BankApp ba, InputOutputHelper ioh) throws Exception {
//        if (ba.changePasswordUser(ioh.readInput("Enter login:"),
//                ioh.readPassword("Enter current password:"),
//                ioh.readPassword("Enter new password:"),
//                ioh.readPassword("Confirm current password:"))) {
//            return InputOutputHelper.SUCCESS_MESSAGE;
//        } else {
//            throw new IllegalArgumentException("Password was not changed");
//        }
        return "";
    }
}
