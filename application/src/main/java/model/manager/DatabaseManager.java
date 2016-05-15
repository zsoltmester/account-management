package model.manager;

import resource.Configs;

import java.sql.*;
import java.util.List;

/**
 * Contains Database related actions for other managers. This is the only class in the application which communicates
 * directly with the database.
 */
public class DatabaseManager {

    private static Connection connection;

    protected DatabaseManager() {
        throw new UnsupportedOperationException("You cannot instantiate a manager class.");
    }

    /**
     * Connect to the database.
     *
     * @return If the connection was successful or not.
     */
    public static boolean connect() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = DriverManager.getConnection(Configs.JDBC_CONNECTION_URL, Configs.JDBC_CONNECTION_USER,
                    Configs.JDBC_CONNECTION_PASSWORD);
            return connection != null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Executes the given select statement.
     *
     * @param sql The select statement to execute.
     * @return The result of the query. <code>null</code> in case of error.
     */
    protected static ResultSet executeSelect(String sql) {
        System.out.println("[INFO] Executing select: " + sql);
        try {
            connection.setAutoCommit(true);
            Statement statement = null;
            ResultSet resultSet;
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                statement.closeOnCompletion();
            } catch (SQLException e) {
                if (statement != null) {
                    statement.close();
                }
                throw e;
            }
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Executes the given update statement.
     *
     * @param sql The update statement to execute.
     * @return If the update was successful or not.
     */
    protected static boolean executeUpdate(String sql) {
        System.out.println("[INFO] Executing update: " + sql);
        try {
            connection.setAutoCommit(true);
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Executes the given statements in 1 transaction.
     *
     * @param statements The statements to execute.
     * @return If the transaction performed or not.
     */
    protected static boolean executeTransaction(List<String> statements) {
        try {
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            try (Statement statement = connection.createStatement()) {
                for (String sql : statements) {
                    System.out.println("[INFO] Executing statement in a transaction: " + sql);
                    statement.execute(sql);
                }
            } catch (SQLException e) {
                connection.rollback(savepoint);
                return false;
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
