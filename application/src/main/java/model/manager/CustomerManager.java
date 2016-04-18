package model.manager;

import model.entity.Customer;

import java.util.Map;

/**
 * Contains customer related actions.
 */
public class CustomerManager {

    /**
     * Returns all the customer names associated with the customer IDs from the database.
     *
     * @return All the customer names associated with the customer IDs from the database.
     */
    public static Map<String, Long> getStrippedCustomers() {
        // TODO use the database for this.
        return null;
    }

    /**
     * Returns the customer associated with the given id.
     *
     * @param id The id of the customer to query.
     * @return The customer associated with the given id. <code>null</code>, if no customer were found.
     */
    public static Customer getCustomer(long id) {
        // TODO use the database for this.
        return null;
    }
}
