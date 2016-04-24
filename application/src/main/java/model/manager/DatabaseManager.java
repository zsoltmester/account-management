package model.manager;

import model.entity.Account;
import model.entity.Customer;
import model.entity.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO Wait the database integration with this.

/**
 * Contains Database related actions for other managers.
 */
abstract class DatabaseManager {

    protected DatabaseManager() {
        throw new UnsupportedOperationException("You cannot instantiate a manager class.");
    }

    //
    // TODO Dummy datas to simulate the database
    //

    protected static final int NUM_OF_ACCOUNTS = 9;

    protected static Set<Customer> customers;

    static {
        // create the accounts
        List<Account> accounts = new ArrayList<>(NUM_OF_ACCOUNTS);
        for (int i = 0; i < NUM_OF_ACCOUNTS; i++) {
            accounts.add(new Account(Integer.toString(i), new BigDecimal((i + 1) * 100), i % (NUM_OF_ACCOUNTS / 2) != 0,
                    new ArrayList<Transaction>()));
        }

        // create the transactions
        for (int i = 0; i < NUM_OF_ACCOUNTS; i++) {
            for (int j = 0; j < i; j++) {
                Transaction transaction = new Transaction(i * NUM_OF_ACCOUNTS + j, Integer.toString(i),
                        Integer.toString(j), new BigDecimal(i * j));
                accounts.get(i).getTransactions().add(transaction);
                accounts.get(j).getTransactions().add(transaction);
            }
        }

        // create the customers
        customers = new HashSet<>();
        customers.add(new Customer(0, "Andrew", "1-address", "1-phone", accounts.subList(0, 3)));
        customers.add(new Customer(1, "Ben", "2-address", "2-phone", accounts.subList(4, 6)));
        customers.add(new Customer(2, "Daniel", "3-address", "3-phone", accounts.subList(6, 9)));
    }
}
