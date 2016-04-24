package model.manager;

import model.entity.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static model.manager.DatabaseManager.customers;

/**
 * Contains customer related actions.
 */
public class CustomerManager {

    /**
     * Returns all the customer names associated with the customer IDs from the database.
     *
     * @return All the customer names associated with the customer IDs from the database.
     */
    public static Map<Long, String> getStrippedCustomers() {
        // TODO just a temp logic until we have database
        Map<Long, String> strippedCustomers = new HashMap<>();
        customers.forEach(customer -> {
            strippedCustomers.put(customer.getId(), customer.getName());
        });
        return strippedCustomers;
    }

    /**
     * Returns the customer associated with the given id.
     *
     * @param id The id of the customer to query.
     * @return The customer associated with the given id. <code>null</code>, if no customer were found.
     */
    public static Customer getCustomer(long id) {
        // TODO just a temp logic until we have database
        try {
            return customers.stream().filter(customer -> customer.getId() == id).findAny().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
