package model;

import org.junit.Before;
import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.*;

/**
 * Test cases for the {@link Session} class.
 */
public class SessionTest {

    private Session session;

    @Before
    public void initSession() {
        session = Session.login("admin", Base64.getEncoder().encode("admin".getBytes()));
        assertNotNull(session);
        assertTrue(session.isActive());
    }

    @Test
    public void login() {
        Session session = Session.login(null, null);
        assertNull(session);
        
        session = Session.login("", Base64.getEncoder().encode("".getBytes()));
        assertNull(session);
        
        session = Session.login("asd", Base64.getEncoder().encode("asd".getBytes()));
        assertNull(session);
    }

    @Test
    public void revoke() throws InterruptedException {
        Thread.sleep(10);
        long timeLeft = session.getTimeLeft();
        session.revoke();
        assertTrue(session.getTimeLeft() - timeLeft > 0);
    }

    @Test
    public void logout() {
        session.logout();
        assertFalse(session.isActive());
        session.revoke();
        assertFalse(session.isActive());
    }

    @Test
    public void getUser() {
        assertEquals("admin", session.getUser());
        session.logout();
        assertEquals("admin", session.getUser());
    }

    @Test
    public void isActive() {
        session.logout();
        assertFalse(session.isActive());
    }

    @Test
    public void getTimeLeft() {
        assertTrue(session.getTimeLeft() > 0);
        session.logout();
        assertTrue(session.getTimeLeft() <= 0);
    }
}