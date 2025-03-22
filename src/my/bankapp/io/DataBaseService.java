package my.bankapp.io;

import my.bankapp.services.MoneyOperationFunction;
import my.bankapp.services.MoneyService;
import my.bankapp.model.Account;
import my.bankapp.model.Money;
import my.bankapp.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

public class DataBaseService implements DaoBank {
    private final String url;
    private final String username;
    private final String password;

    public DataBaseService(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username,
                password);
    }

    // нужно ли разделять на два метода - getUserByLogin и getUserById?
    public User getUser(String userLogin, long userId) throws RuntimeException {

        String field;
        if (userLogin == null && userId != -1) {
            field = "users.id";
        } else {
            field = "users.login";
        }

        User user = null;
        String sql = "SELECT users.id AS uid, users.login, accounts.id AS aid FROM users " +
                     "LEFT JOIN accounts ON accounts.user_id = users.id AND is_default = true " +
                     "WHERE " + field + " = ? " +
                     "LIMIT 1";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            if (userLogin == null && userId != -1) {
                statement.setLong(1, userId);
            } else {
                statement.setString(1, userLogin);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                long defaultAccount = resultSet.getLong("aid");
                if (resultSet.wasNull()) {
                    defaultAccount = -1;
                }

                user = new User(resultSet.getLong("uid"), resultSet.getString("login"), defaultAccount);
            }
        } catch (SQLException sqle) {
            throw new RuntimeException(String.format("Unable to get user %s", userLogin), sqle);
        }

        if (user == null) {
            throw new RuntimeException(String.format("User %s not found", userLogin));
        }

        return user;
    }

    public boolean createNewUser(String login) throws RuntimeException {

        long userId = -1;
        String sql = "INSERT INTO users(login) VALUES (?) ON CONFLICT DO NOTHING RETURNING id;";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userId = resultSet.getLong("id");
            }
        } catch (SQLException sqle) {
            throw new RuntimeException("Unable to create user", sqle);
        }

        if (userId == -1) {
            throw new RuntimeException(String.format("User %s was not created", userId));
        } else {
            return true;
        }
    }

    public boolean setUserDefaultAccount(long userId, long accountId) throws RuntimeException {

        Connection connection = null;
        RuntimeException mainException = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false); // Start transaction

            String sql = "UPDATE accounts SET is_default = FALSE WHERE user_id = ? AND is_default = TRUE;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, userId);

                statement.executeUpdate();

                if (statement.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException sqle) {
                throw new RuntimeException("Unable to set default account", sqle);
            }

            sql = "UPDATE accounts SET is_default = TRUE WHERE user_id = ? AND id = ?;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, userId);
                statement.setLong(2, accountId);

                statement.executeUpdate();

                if (statement.executeUpdate() > 0) {
                    connection.commit();
                } else {
                    throw new RuntimeException("No accounts were set as default");
                }
            } catch (SQLException sqle) {
                throw new RuntimeException("Unable to set default account", sqle);
            }

        } catch (SQLException | RuntimeException sqle) {
            mainException = new RuntimeException("Unable to perform operation", sqle);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    mainException.addSuppressed(rollbackEx);
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    if (mainException != null) {
                        mainException.addSuppressed(e);
                    }
                }
            }
        }

        if (mainException != null) {
            throw mainException;
        }

        if (accountId == -1) {
            throw new RuntimeException(String.format("Account %s was not set as default", accountId));
        } else {
            return true;
        }
    }

    public Stream<Account> getAccountsByUser(long userId) {

        Stream.Builder<Account> builder = Stream.builder();

        String sql = "SELECT id, type, money FROM accounts WHERE user_id = ?";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Account account = new Account(resultSet.getLong("id"), resultSet.getInt("type"), resultSet.getString("money"));
                builder.add(account);
            }
        } catch (SQLException sqle) {
            throw new RuntimeException(String.format("Unable to get accounts for user %s", userId), sqle);
        }

        return builder.build();
    }

    public Account getAccountById(long accountId) {

        Account account = null;
        String sql = "SELECT id, type, money FROM accounts WHERE id = ? LIMIT 1";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, accountId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                account = new Account(resultSet.getLong("id"), resultSet.getInt("type"), resultSet.getString("money"));
            }
        } catch (SQLException sqle) {
            throw new RuntimeException(String.format("Unable to get account %s", accountId), sqle);
        }

        if (account == null) {
            throw new RuntimeException(String.format("Account %s not found", accountId));
        }

        return account;
    }

    public boolean createNewAccount(long userId, int accountType, Money money) {

        long accountId = -1;
        String sql = """
                    INSERT INTO accounts(user_id, type, money, title, is_default)
                    VALUES (?, ?, ?, ?, NOT EXISTS(SELECT FROM accounts WHERE user_id = ?))
                    RETURNING id;
                """;

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, userId);
            statement.setInt(2, accountType);
            statement.setBigDecimal(3, money.getValue());
            statement.setString(4, "Новый счёт");
            statement.setLong(5, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                accountId = resultSet.getLong("id");
            }
        } catch (SQLException sqle) {
            throw new RuntimeException("Unable to create account", sqle);
        }

        if (accountId == -1) {
            throw new RuntimeException(String.format("Account for user %s was not created", userId));
        } else {
            return true;
        }
    }

    // нужно ли разделять на два метода - addAccountMoney и subtractAccountMoney?
    public boolean setAccountMoney(long accountId, MoneyService mh, Money money, MoneyOperationFunction<Money, Money> function) {

        Connection connection = null;
        Money currentWallet = null;
        RuntimeException mainException = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false); // Start transaction

            String selectSql = "SELECT money FROM accounts WHERE id=? FOR UPDATE;";

            try (PreparedStatement statement = connection.prepareStatement(selectSql)) {

                statement.setLong(1, accountId);

                ResultSet resultSet = statement.executeQuery();

                resultSet.next();
                currentWallet = new Money(resultSet.getBigDecimal("money"));

            } catch (SQLException sqle) {
                throw new RuntimeException("Unable to get account wallet", sqle);
            }

            String updateSql = "UPDATE accounts SET money=? WHERE id=?;";

            try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
                statement.setBigDecimal(1, function.applyAsMoney(currentWallet, money).getValue());
                statement.setLong(2, accountId);

                statement.executeUpdate();

                if (statement.executeUpdate() > 0) {
                    connection.commit();
                } else {
                    throw new RuntimeException("No account wallets were changed");
                }
            } catch (SQLException sqle) {
                throw new RuntimeException("Unable to change account wallet", sqle);
            }

        } catch (SQLException | RuntimeException sqle) {
            mainException = new RuntimeException("Unable to perform operation", sqle);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    mainException.addSuppressed(rollbackEx);
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    if (mainException != null) {
                        mainException.addSuppressed(e);
                    }
                }
            }
        }

        if (mainException != null) {
            throw mainException;
        }

        if (accountId == -1) {
            throw new RuntimeException(String.format("Account %s wallet was not changed", accountId));
        } else {
            return true;
        }
    }
}