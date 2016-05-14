package resource;

import java.text.SimpleDateFormat;

/**
 * Contains some general configuration.
 */
public class Configs {

    public static final SimpleDateFormat ACCOUNT_CREATION_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static final SimpleDateFormat TRANSACTION_CREATION_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static final String JDBC_CONNECTION_URL = "jdbc:oracle:thin:@tomx.inf.elte.hu:1522/ora11g";
    public static final String JDBC_CONNECTION_USER = "qt3qf8";
    public static final String JDBC_CONNECTION_PASSWORD = "1212marai010";

    public static final String USER_TABLE = "AM_USER";
    public static final String USER_TABLE_USER_FIELD = "name";
    public static final String USER_TABLE_PASSWORD_FIELD = "password";

    public static final String CUSTOMER_TABLE = "AM_CUSTOMER";
    public static final String CUSTOMER_TABLE_ID_FIELD = "ID";
    public static final String CUSTOMER_TABLE_NAME_FIELD = "NAME";
    public static final String CUSTOMER_TABLE_ADDRESS_FIELD = "ADDRESS";
    public static final String CUSTOMER_TABLE_PHONE_FIELD = "PHONE";

    public static final String ACCOUNT_TABLE = "AM_ACCOUNT";
    public static final String ACCOUNT_TABLE_ID_FIELD = "ID";
    public static final String ACCOUNT_TABLE_CUSTOMER_ID_FIELD = "CUSTOMER_ID";
    public static final String ACCOUNT_TABLE_ACCOUNT_NUMBER_FIELD = "ACCOUNT_NUMBER";
    public static final String ACCOUNT_TABLE_BALANCE_FIELD = "BALANCE";
    public static final String ACCOUNT_TABLE_CREATION_DATE_FIELD = "CREATION";
    public static final String ACCOUNT_TABLE_STATUS_FIELD = "STATUS";

    public static final String TRANSACTION_TABLE = "AM_TRANSACTION";
    public static final String TRANSACTION_TABLE_ID_FIELD = "ID";
    public static final String TRANSACTION_TABLE_SOURCE_ACCOUNT_FIELD = "SOURCE_ACCOUNT";
    public static final String TRANSACTION_TABLE_TARGET_ACCOUNT_FIELD = "TARGET_ACCOUNT";
    public static final String TRANSACTION_TABLE_AMOUNT_FIELD = "AMOUNT";
    public static final String TRANSACTION_TABLE_CREATION_DATE_FIELD = "CREATION";
}
