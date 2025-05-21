package beans;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class MissRatio implements MissRatioMBean, Serializable {
    private final AtomicInteger totalAttempts = new AtomicInteger();
    private final AtomicInteger totalMiss = new AtomicInteger();

    @Override
    public int getTotalAttempts() {
        return totalAttempts.get();
    }

    @Override
    public double getMissRatio() {
        if (totalAttempts.get() == 0) {
            return 0.0;
        }
        return (double) totalMiss.get() / totalAttempts.get() * 100.0;
    }

    public void updateData(boolean hit) {
        totalAttempts.incrementAndGet();
        if (!hit) {
            totalMiss.incrementAndGet();
        }
    }
}
