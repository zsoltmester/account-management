package model;

import model.manager.UserManager;

import java.sql.SQLException;
import java.util.*;

/**
 * This class manages a session. One instance manage only one session.
 */
public class Session {

    private static final long TIMEOUT = 1000 * 60 * 10; // 10 minutes

    private final Object lock;

    private String user;

    private List<SessionListener> listeners;

    private boolean isActive;

    private Timer timer;
    private TimerTask timerTask;
    private long nextRun;

    private Session(String user) {
        lock = new Object();
        this.user = user;
        listeners = new ArrayList<>();
        isActive = true;
        timer = new Timer(true);
        revoke();
    }

    /**
     * Revokes the session, if it is active.
     */
    public void revoke() {
        synchronized (lock) {
            if (!isActive) {
                return;
            }
            if (timerTask != null) {
                timerTask.cancel();
            }
            timer.purge();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    logout();
                    listeners.forEach(SessionListener::onEnd);
                }
            };
            nextRun = System.currentTimeMillis() + TIMEOUT;
            timer.schedule(timerTask, TIMEOUT);
        }
    }

    /**
     * Validates the credentials and creates a session for the user if those are valid.
     *
     * @param user     The user to log in.
     * @param password The password in base 64 encoding.
     * @return The created session if the credentials are valid, otherwise <code>null</code>.
     */
    public static Session login(String user, byte[] password) {
        return UserManager.isValid(user, password) ? new Session(user) : null;
    }

    /**
     * Invalidates this session.
     */
    public void logout() {
        synchronized (lock) {
            if (!isActive) {
                return;
            }
            isActive = false;
            timerTask.cancel();
            timer.purge();
            timer.cancel();
        }
    }

    /**
     * Returns the user associated with this session.
     *
     * @return The user associated with this session.
     */
    public String getUser() {
        return user;
    }

    /**
     * Returns if the session is active.
     * @return The session is active or not.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Returns the time left for this session.
     *
     * @return The time left for this session. <=0, if this session is invalid.
     */
    public long getTimeLeft() {
        synchronized (lock) {
            return isActive ? nextRun - System.currentTimeMillis() : 0;
        }
    }

    /**
     * Associate listener for this {@link Session}.
     *
     * @param listener The listener to add.
     */
    public void addListener(SessionListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove a listener for this {@link Session}.
     *
     * @param listener The listener to remove.
     */
    public void removeListener(SessionListener listener) {
        listeners.remove(listener);
    }

    /**
     * Class must implements this to handle {@link Session} related events. Use {@link #addListener(SessionListener)}
     * and {@link #removeListener(SessionListener)} to associate the listener with a {@link Session}.
     */
    public interface SessionListener {

        /**
         * Calls on {@link Session} end.
         */
        void onEnd();
    }
}
