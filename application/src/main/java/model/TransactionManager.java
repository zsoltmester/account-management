package model;

import model.entity.Transaction;

import java.util.List;

/**
 * Contains transaction related actions.
 */
public class TransactionManager {

    /**
     * Check if the given transaction can be performed.
     *
     * @param transaction The transaction to perform.
     * @return <code>true</code>, if the transaction can performed, otherwise <code>false</code>.
     */
    public static boolean canPerform(Transaction transaction) {
        // TODO use the database for this
        return true;
    }

    /**
     * Check if the given transactions can be performed at once.
     *
     * @param transactions The transactions to perform at once.
     * @return <code>true</code>, if the transactions can performed at once, otherwise <code>false</code>.
     */
    public static boolean canPerform(List<Transaction> transactions) {
        // TODO use the database for this
        return true;
    }

    /**
     * Perform the given transaction.
     *
     * @param transaction The transaction to perform.
     * @return <code>true</code>, if the transaction performed, otherwise <code>false</code>.
     */
    public static boolean performTransaction(Transaction transaction) {
        // TODO use the database for this
        return true;
    }

    /**
     * Perform the given transactions at once.
     *
     * @param transactions The transaction to perform at once.
     * @return <code>true</code>, if the transaction performed at once, otherwise <code>false</code>.
     */
    public static boolean performTransaction(List<Transaction> transactions) {
        // TODO use the database for this
        return true;
    }
}
