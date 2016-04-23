package model.manager;

import model.entity.Customer;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the {@link UserManager} class.
 */
public class CustomerManagerTest {

    @Test
    public void testIntegrity() {
        Map<String, Long> strippedCustomers = CustomerManager.getStrippedCustomers();
        assertNotNull(strippedCustomers);
        assertTrue(strippedCustomers.size() > 0);
        strippedCustomers.forEach((name, id) -> {
            assertNotNull(name);
            assertTrue(id > 0);
            Customer customer = CustomerManager.getCustomer(id);
            assertNotNull(customer);
            assertTrue(customer.getId() == id);
        });
    }
}