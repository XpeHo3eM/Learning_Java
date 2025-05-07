package Spring_Core.task2.metric;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MetricStatProviderService implements MetricStatProvider {
    private final MetricStorage storage;

    @Override
    public List<MethodMetricStat> getTotalStatForPeriod(LocalDateTime from, LocalDateTime to) {
        List<Metric> allMetrics = storage.getMetricsForPeriod(from, to);

        if (allMetrics.isEmpty()) {
            return Collections.emptyList();
        }

        Map<String, List<Metric>> metricsByMethod = allMetrics.stream()
                .collect(Collectors.groupingBy(Metric::methodName));

        return metricsByMethod.values().stream()
                .map(this::metricsByMethodNameToStat)
                .toList();
    }

    @Override
    public MethodMetricStat getTotalStatByMethodForPeriod(String method, LocalDateTime from, LocalDateTime to) {
        List<Metric> metrics = storage.getMetricsForPeriodByMethodName(method, from, to);

        if (metrics.isEmpty()) {
            return MethodMetricStat.builder()
                    .methodName(method)
                    .build();
        }

        return metricsByMethodNameToStat(metrics);
    }

    private MethodMetricStat metricsByMethodNameToStat(List<Metric> metrics) {
        String methodName = metrics.get(0).methodName();

        LongSummaryStatistics statistics = metrics.stream()
                .mapToLong(Metric::duration)
                .summaryStatistics();

        return MethodMetricStat.builder()
                .methodName(methodName)
                .minTime((int)statistics.getMin())
                .maxTime((int)statistics.getMax())
                .invocationsCount((int) statistics.getCount())
                .averageTime((int) statistics.getAverage())
                .build();
    }
}
