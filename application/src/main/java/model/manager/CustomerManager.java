package model.manager;

import model.entity.Account;
import model.entity.Customer;
import model.entity.Transaction;
import resource.Configs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contains customer related actions.
 */
public class CustomerManager extends DatabaseManager {

    /**
     * Returns all the customer names associated with the customer IDs from the database.
     *
     * @return All the customer names associated with the customer IDs from the database.
     */
    public static Map<Long, String> getStrippedCustomers() {
        Map<Long, String> strippedCustomers = new HashMap<>();
        try {
            String sql = "select " + Configs.CUSTOMER_TABLE_ID_FIELD + ", " + Configs.CUSTOMER_TABLE_NAME_FIELD
                    + " from " + Configs.CUSTOMER_TABLE;
            ResultSet results = executeSelect(sql);
            while (results.next()) {
                strippedCustomers.put(results.getLong(1), results.getString(2));
            }
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strippedCustomers;
    }

    /**
     * Returns the customer associated with the given id.
     *
     * @param id The id of the customer to query.
     * @return The customer associated with the given id. <code>null</code>, if no customer were found.
     */
    public static Customer getCustomer(long id) {
        Customer customer = null;
        try {
            String sql = "select * from ((select * from " + Configs.CUSTOMER_TABLE + " where " + Configs.CUSTOMER_TABLE
                    + "." + Configs.CUSTOMER_TABLE_ID_FIELD + "=" + Long.toString(id) + ") customer left join "
                    + Configs.ACCOUNT_TABLE + " account on customer." + Configs.CUSTOMER_TABLE_ID_FIELD
                    + " = account." + Configs.ACCOUNT_TABLE_CUSTOMER_ID_FIELD + ") left join "
                    + Configs.TRANSACTION_TABLE + " transaction on (account." + Configs.ACCOUNT_TABLE_ID_FIELD
                    + " = transaction." + Configs.TRANSACTION_TABLE_SOURCE_ACCOUNT_FIELD + " or account."
                    + Configs.ACCOUNT_TABLE_ID_FIELD + " = transaction."
                    + Configs.TRANSACTION_TABLE_TARGET_ACCOUNT_FIELD + ")";
            ResultSet resultSet = executeSelect(sql);
            while (resultSet.next()) {
                if (customer == null) {
                    customer = new Customer(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), new HashSet<>());
                }

                Set<Account> accounts = customer.getAccounts();
                addAccountIfNew(accounts, resultSet);

                long transactionId = resultSet.getLong(11);
                long sourceAccountId = resultSet.getLong(12);
                long targetAccountId = resultSet.getLong(13);
                addTransactionIfNew(accounts, sourceAccountId, transactionId, resultSet);
                addTransactionIfNew(accounts, targetAccountId, transactionId, resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    private static void addAccountIfNew(Set<Account> accounts, ResultSet resultSet) throws SQLException {
        long accountId = resultSet.getLong(5);
        if (!accounts.contains(new Account(accountId, null, null, null, false, null))) {
            accounts.add(new Account(accountId, resultSet.getString(7), resultSet.getBigDecimal(8),
                    resultSet.getDate(9), resultSet.getInt(10) == 0, new HashSet<>()));
        }
    }

    private static void addTransactionIfNew(Set<Account> accounts, long accountId, long transactionId,
                                            ResultSet resultSet) throws SQLException {
        if (accounts.contains(new Account(accountId, null, null, null, false, null))) {
            Set<Transaction> transactions = accounts.stream()
                    .filter(account -> account.getId() == accountId).findAny().get().getTransactions();
            if (!transactions.contains(new Transaction(transactionId, 0, 0, null, null))) {
                transactions.add(new Transaction(transactionId, resultSet.getLong(12), resultSet.getLong(13),
                        resultSet.getBigDecimal(14), resultSet.getDate(15)));
            }
        }
    }
}
