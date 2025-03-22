package my.bankapp.io;

import my.bankapp.services.MoneyService;
import my.bankapp.services.MoneyOperationFunction;
import my.bankapp.model.Account;
import my.bankapp.model.Money;
import my.bankapp.model.User;

import java.util.stream.Stream;

public interface DaoBank {

    User getUser(String userLogin, long userId);

    boolean createNewUser(String login);

    Stream<Account> getAccountsByUser(long userId);

    Account getAccountById(long accountId);

    boolean createNewAccount(long userId, int accountType, Money money);

    boolean setAccountMoney(long accountId, MoneyService mh, Money money, MoneyOperationFunction<Money, Money> function);

    boolean setUserDefaultAccount(long userId, long defaultAccountNumber);
}
