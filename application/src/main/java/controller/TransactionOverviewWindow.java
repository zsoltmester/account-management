package controller;

import model.Session;
import model.entity.Transaction;
import model.manager.TransactionManager;
import resource.Dimensions;
import resource.Strings;
import view.TransactionView;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The transaction overview window of the application. Read more in the documentation.
 */
public class TransactionOverviewWindow extends Window implements TransactionView.OnTransactionClickedListener {

    private Set<Transaction> transactions;

    private List<TransactionView> transactionViews;

    private JPanel transactionsPanel;
    private JScrollPane scrollPane;

    /**
     * Displays a new transaction overview window based on the given params.
     *
     * @param session      The current session.
     * @param transactions The transaction to display.
     */
    public TransactionOverviewWindow(Session session, Set<Transaction> transactions) {
        super(Strings.TRANSACTION_HISTORY_WINDOW_TITLE, session);
        this.transactions = transactions;

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));

        transactionViews = new ArrayList<>(transactions.size() + 1);
        TransactionView infoView = new TransactionView();
        transactionViews.add(infoView);
        transactionsPanel.add(infoView);
        transactions.forEach(transaction -> {
            TransactionView transactionView = new TransactionView(transaction, this);
            transactionViews.add(transactionView);
            transactionsPanel.add(transactionView);
        });

        scrollPane = new JScrollPane(transactionsPanel);
        container.add(scrollPane, BorderLayout.CENTER);

        display(Dimensions.TRANSACTION_HISTORY_WINDOW_SIZE.width, Dimensions.TRANSACTION_HISTORY_WINDOW_SIZE.height);
    }

    @Override
    public void onCancelClicked(Transaction transaction) {
        try {
            if (TransactionManager.cancelTransaction(transaction)) {
                reopen(() -> {
                    // TODO
                });
            } else {
                JOptionPane.showMessageDialog(container, Strings.CREATE_TRANSACTION_WINDOW_ERROR,
                        Strings.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(container, Strings.CREATE_TRANSACTION_WINDOW_ERROR,
                    Strings.ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
}
