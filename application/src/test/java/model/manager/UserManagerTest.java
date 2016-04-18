package model.manager;

import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the {@link UserManager} class.
 */
public class UserManagerTest {

    @Test
    public void isValid() {
        assertFalse(UserManager.isValid(null, null));
        assertFalse(UserManager.isValid("", Base64.getEncoder().encode("".getBytes())));
        assertTrue(UserManager.isValid("admin", Base64.getEncoder().encode("admin".getBytes())));
    }
}