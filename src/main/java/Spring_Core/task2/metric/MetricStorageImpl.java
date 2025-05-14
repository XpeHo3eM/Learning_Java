package Spring_Core.task2.metric;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class MetricStorageImpl implements MetricStorage {
    private final Map<String, List<Metric>> storage = new ConcurrentHashMap<>();

    @Override
    public void addMetric(Metric newMetric) {
        storage.computeIfAbsent(newMetric.methodName(), key -> new LinkedList<>())
                .add(newMetric);
    }

    @Override
    public List<Metric> getMetricsForPeriod(LocalDateTime from, LocalDateTime to) {
        Collection<Metric> metrics = storage.values().stream()
                .flatMap(List::stream)
                .toList();

        return getMetricBetween(metrics, from, to);
    }

    @Override
    public List<Metric> getMetricsForPeriodByMethodName(String methodName, LocalDateTime from, LocalDateTime to) {
        Collection<Metric> metrics = storage.getOrDefault(methodName, Collections.emptyList());

        return getMetricBetween(metrics, from, to);
    }

    private List<Metric> getMetricBetween(Collection<Metric> collection, LocalDateTime from, LocalDateTime to) {
        return collection.stream()
                .filter(metric -> metric.timestamp().isAfter(from) && metric.timestamp().isBefore(to))
                .toList();
    }
}
