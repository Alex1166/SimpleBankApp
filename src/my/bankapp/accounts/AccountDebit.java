package my.bankapp.accounts;

public class AccountDebit extends AccountActive {
    public AccountDebit() {
        super();
    }

    public boolean setWallet(int wallet) throws NumberFormatException {
        if (wallet < 0) {
            throw new NumberFormatException("Not enough money");
        }
        super.setWallet(wallet);
        return true;
    }
}
