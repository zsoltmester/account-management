package model.manager;

import model.entity.Transaction;
import resource.Configs;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Contains transaction related actions.
 */
public class TransactionManager extends DatabaseManager {

    private TransactionManager() {
        super();
    }

    private static String createInsertStatementForTransaction(long sourceAccount, long targetAccount, BigDecimal amount) {
        String creationDate = Configs.TRANSACTION_CREATION_DATE_FORMAT.format(new Date());
        return "insert into " + Configs.TRANSACTION_TABLE + " values " + "(NULL, " + sourceAccount
                + ", " + targetAccount + ", " + amount + ", TO_DATE('" + creationDate + "','DD-MM-YYYY HH24:MI:SS'))";
    }

    /**
     * Perform the given transaction.
     *
     * @param sourceAccount The source account.
     * @param targetAccount The target account.
     * @param amount        The amount.
     * @return <code>true</code>, if the transaction performed, otherwise <code>false</code>.
     */
    public static boolean performTransaction(long sourceAccount, long targetAccount, BigDecimal amount) {
        return executeTransaction(
                Collections.singletonList(createInsertStatementForTransaction(sourceAccount, targetAccount, amount)));
    }

    /**
     * Perform the given transactions at once.
     *
     * @param sourceAccounts The source accounts.
     * @param targetAccount  The target account.
     * @param amounts        The amounts.
     * @return <code>true</code>, if the transaction performed at once, otherwise <code>false</code>.
     */
    public static boolean performTransaction(List<Long> sourceAccounts, long targetAccount, List<BigDecimal> amounts) {
        if (sourceAccounts == null || amounts == null || sourceAccounts.size() != amounts.size()
                || sourceAccounts.size() == 0) {
            return false;
        }
        List<String> statements = new ArrayList<>(sourceAccounts.size());
        for (int i = 0; i < sourceAccounts.size(); ++i) {
            statements.add(createInsertStatementForTransaction(sourceAccounts.get(i), targetAccount, amounts.get(i)));
        }
        return executeTransaction(statements);
    }

    /**
     * Cancels the given transaction, if it's possible.
     *
     * @param transaction The transaction to cancel.
     * @return <code>true</code>, if the transaction successfully canceled, otherwise <code>false</code>.
     */
    public static boolean cancelTransaction(Transaction transaction) {
        if (transaction == null || transaction.getCreationDate() == null
                || transaction.getCreationDate().getTime() + 12 * 60 * 60 * 1000 - new Date().getTime()
                < 0) {
            return false;
        }

        return performTransaction(transaction.getTargetAccount(), transaction.getSourceAccount(), transaction.getAmount());
    }
}
