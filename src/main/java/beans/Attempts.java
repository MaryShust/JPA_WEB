package beans;

import jakarta.enterprise.context.SessionScoped;
import javax.management.*;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@SessionScoped
public class Attempts implements AttemptsMBean, NotificationBroadcaster, Serializable {
    private final AtomicInteger totalAttempts = new AtomicInteger();
    private final AtomicInteger totalMisses = new AtomicInteger();

    private final NotificationBroadcasterSupport broadcaster = new NotificationBroadcasterSupport();

    @Override
    public int getTotalAttempts() {
        return totalAttempts.get();
    }

    @Override
    public int getTotalMisses() {
        return totalMisses.get();
    }

    public void updateData(boolean hit) {
        totalAttempts.incrementAndGet();
        if (!hit) {
            totalMisses.incrementAndGet();
            broadcaster.sendNotification(new Notification(
                    "miss",
                    this,
                    System.currentTimeMillis(),
                    "miss detected"
            ));
        }
    }

    @Override
    public void addNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback) throws IllegalArgumentException {
        broadcaster.addNotificationListener(listener, filter, handback);
    }

    @Override
    public void removeNotificationListener(NotificationListener listener) throws ListenerNotFoundException {
        broadcaster.removeNotificationListener(listener);
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[] { "miss" };
        String name = Notification.class.getName();
        String description = "Notification is sent when miss detected";
        return new MBeanNotificationInfo[] { new MBeanNotificationInfo(types, name, description) };
    }
}
