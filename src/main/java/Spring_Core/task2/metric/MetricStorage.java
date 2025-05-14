package Spring_Core.task2.metric;

import java.time.LocalDateTime;
import java.util.List;

public interface MetricStorage {
    void addMetric(Metric newMetric);
    List<Metric> getMetricsForPeriod(LocalDateTime from, LocalDateTime to);

    List<Metric> getMetricsForPeriodByMethodName(String methodName, LocalDateTime from, LocalDateTime to);
}
