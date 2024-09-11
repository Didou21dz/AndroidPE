package jkas.androidpe.logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author JKas
 */
public class LoggerRes {
    private static final List<LogListener> logListeners = new ArrayList<>();

    public static void initFromZero() {
        logListeners.clear();
    }

    public static void addLogListener(LogListener listener) {
        logListeners.add(Objects.requireNonNull(listener));
    }

    public static void removeLogListener(LogListener listener) {
        logListeners.remove(Objects.requireNonNull(listener));
    }

    public static void reloadResRef() {
        for (final var listener : logListeners) listener.reloadResRef();
    }

    public static void updateView() {
        for (final var listener : logListeners) listener.updateView();
    }

    public interface LogListener {
        default public void reloadResRef(){
            // must be implemented from others classes
        }

        default public void updateView() {
            // nothing
        }
    }
}
