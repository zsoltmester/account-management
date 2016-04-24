package resource;

import java.awt.*;

/**
 * Contains the dimension resources of this application.
 */
public class Dimensions {

    public static final int WINDOW_WIDTH = 400;
    public static final int COMPONENT_HEIGHT = 50;
    public static final Dimension GAP = new Dimension(5, 5);

    public static final Dimension WINDOW_MINIMUM_SIZE = new Dimension(WINDOW_WIDTH / 2, WINDOW_WIDTH / 2);

    public static final Dimension LOGIN_WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_WIDTH / 2);
    public static final Dimension LOGIN_WINDOW_COMPONENT_SIZE = new Dimension(WINDOW_WIDTH / 2, COMPONENT_HEIGHT);

    public static final Dimension CUSTOMER_SEARCH_WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_WIDTH);
    public static final Dimension CUSTOMER_SEARCH_WINDOW_COMPONENT_SIZE =
            new Dimension(WINDOW_WIDTH / 2, COMPONENT_HEIGHT);
    public static final Dimension CUSTOMER_SEARCH_WINDOW_OPTION_SIZE =
            new Dimension(WINDOW_WIDTH / 2, COMPONENT_HEIGHT / 2);

    public static final Dimension CUSTOMER_MANAGER_WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_WIDTH);
    public static final Dimension CUSTOMER_MANAGER_INFO_COMPONENT_SIZE =
            new Dimension(WINDOW_WIDTH / 3, COMPONENT_HEIGHT);
    public static final Insets CUSTOMER_MANAGER_INFO_COMPONENT_BORDER =
            new Insets(GAP.height, GAP.width, GAP.height, GAP.width);

    public static final Dimension ACCOUNT_COMPONENT_SIZE = new Dimension(WINDOW_WIDTH / 2, COMPONENT_HEIGHT);

    public static final Dimension TRANSACTION_COMPONENT_SIZE = ACCOUNT_COMPONENT_SIZE;

    public static final Dimension TRANSACTION_HISTORY_WINDOW_SIZE = new Dimension(WINDOW_WIDTH / 2, WINDOW_WIDTH * 2);

    public static final Dimension CREATE_TRANSACTION_WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_WIDTH);
    public static final Dimension CREATE_TRANSACTION_WINDOW_COMPONENT_SIZE =
            new Dimension(WINDOW_WIDTH / 2, COMPONENT_HEIGHT);
    public static final Dimension CREATE_TRANSACTION_WINDOW_COMPONENT_SMALLER_SIZE =
            new Dimension(CREATE_TRANSACTION_WINDOW_COMPONENT_SIZE.width,
                    CREATE_TRANSACTION_WINDOW_COMPONENT_SIZE.height / 3);
}
