package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class manages a session. One instance manage only one session.
 */
public class Session {

    private static final long TIMEOUT = 1000 * 60 * 10; // 10 minutes

    private final Object lock = new Object();

    private String user;

    private List<SessionListener> listeners;

    private boolean isActive;

    private Timer timer;
    private TimerTask timerTask;

    private Session(String user) {
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
            timer.scheduleAtFixedRate(timerTask, TIMEOUT, TIMEOUT);
        }
    }

    /**
     * Validates the credentials and creates a session for the user if those are valid.
     *
     * @param user     The user to log in.
     * @param password The password.
     * @return The created session if the credentials are valid, otherwise <code>null</code>.
     */
    public static Session login(String user, CharSequence password) {
        // TODO query from the database
        boolean isValidCredentials = true;
        return isValidCredentials ? new Session(user) : null;
    }

    /**
     * Invalidates this session.
     */
    public void logout() {
        synchronized (lock) {
            isActive = false;
            timerTask.cancel();
            timer.purge();
            timer.cancel();
        }
    }

    /**
     * Returns the user associated with this session.
     *
     * @return The user associated with this session. <code>null</code>, if this session is invalid.
     */
    public String getUser() {
        return isActive ? user : null;
    }

    /**
     * Returns the time left for this session.
     *
     * @return The time left for this session. <=0, if this session is invalid.
     */
    public long getTimeLeft() {
        return isActive ? timerTask.scheduledExecutionTime() - System.currentTimeMillis() : 0;
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
