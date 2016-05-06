package model.manager;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Base64;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the {@link UserManager} class.
 */
public class UserManagerTest {

    @BeforeClass
    public static void connectDatabase() throws SQLException {
        DatabaseManager.connect();
    }

    @Test
    public void isValid() throws SQLException {
        assertFalse(UserManager.isValid(null, null));
        assertFalse(UserManager.isValid("", Base64.getEncoder().encode("".getBytes())));
        assertTrue(UserManager.isValid("admin", Base64.getEncoder().encode("admin".getBytes())));
    }
}