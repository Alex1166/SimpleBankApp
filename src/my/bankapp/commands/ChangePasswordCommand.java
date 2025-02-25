package my.bankapp.commands;

import my.bankapp.BankApp;
import my.bankapp.InputOutputHelper;

public class ChangePasswordCommand implements Command{
    @Override
    public String getCommand() {
        return "chpwd";
    }

    @Override
    public boolean process(BankApp ba, InputOutputHelper ioh) {
        try {
            if (ba.changePasswordUser(ioh.readInput("Enter login:"),
                    ioh.readPassword("Enter current password:"),
                    ioh.readPassword("Enter new password:"),
                    ioh.readPassword("Confirm current password:"))) {
                ioh.printSuccessMessage();
            }
        } catch (Exception e) {
            ioh.printResult(e.getMessage());
            return false;
        }
        return true;
    }
}
