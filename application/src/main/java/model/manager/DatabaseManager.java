package model.manager;

import resource.Configs;

import java.sql.*;

// TODO Wait the database integration with this.

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
     * Connect to the database. You should
     */
    public static void connect() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        connection = DriverManager.getConnection(Configs.JDBC_CONNECTION_URL, Configs.JDBC_CONNECTION_USER,
                Configs.JDBC_CONNECTION_PASSWORD);
    }


    /**
     * Executes the given select statement.
     *
     * @param sql The select statement to execute.
     * @return The result of the query. Never <code>null</code>.
     */
    protected static ResultSet executeSelect(String sql) throws SQLException {
        System.out.println("[INFO] Executing select: " + sql);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        statement.closeOnCompletion();
        return resultSet;
    }
}
