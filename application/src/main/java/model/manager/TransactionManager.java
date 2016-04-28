package model.manager;

import model.entity.Transaction;

import java.util.List;
import java.util.Random;

// TODO add unit tests after database integration
/**
 * Contains transaction related actions.
 */
public class TransactionManager extends DatabaseManager {

    private TransactionManager() {
        super();
    }

    /**
     * Check if the given transaction can be performed.
     *
     * @param transaction The transaction to perform.
     * @return <code>true</code>, if the transaction can performed, otherwise <code>false</code>.
     */
    public static boolean canPerform(Transaction transaction) {
        // TODO logic requires database
        return new Random().nextInt() % 2 == 0;
    }

    /**
     * Check if the given transactions can be performed at once.
     *
     * @param transactions The transactions to perform at once.
     * @return <code>true</code>, if the transactions can performed at once, otherwise <code>false</code>.
     */
    public static boolean canPerform(List<Transaction> transactions) {
        // TODO logic requires database
        return new Random().nextInt() % 2 == 0;
    }

    /**
     * Perform the given transaction.
     *
     * @param transaction The transaction to perform.
     * @return <code>true</code>, if the transaction performed, otherwise <code>false</code>.
     */
    public static boolean performTransaction(Transaction transaction) {
        // TODO logic requires database
        return new Random().nextInt() % 2 == 0;
    }

    /**
     * Perform the given transactions at once.
     *
     * @param transactions The transaction to perform at once.
     * @return <code>true</code>, if the transaction performed at once, otherwise <code>false</code>.
     */
    public static boolean performTransaction(List<Transaction> transactions) {
        // TODO logic requires database
        return new Random().nextInt() % 2 == 0;
    }
}
