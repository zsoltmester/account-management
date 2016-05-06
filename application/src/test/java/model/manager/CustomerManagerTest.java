package model.manager;

import model.entity.Customer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test cases for the {@link CustomerManager} class.
 */
public class CustomerManagerTest {

    @BeforeClass
    public static void connectDatabase() throws SQLException {
        DatabaseManager.connect();
    }

    @Test
    public void integrity() {
        Map<Long, String> strippedCustomers = CustomerManager.getStrippedCustomers();
        assertNotNull(strippedCustomers);
        assertTrue(strippedCustomers.size() > 0);
        strippedCustomers.forEach((id, name) -> {
            assertNotNull(name);
            assertTrue(id > 0);
            Customer customer = CustomerManager.getCustomer(id);
            assertNotNull(customer);
            assertTrue(customer.getId() == id);
        });
    }

    @Test
    public void getCustomer() {
        assertNull(CustomerManager.getCustomer(0));
        assertNotNull(CustomerManager.getCustomer(1));
    }
}