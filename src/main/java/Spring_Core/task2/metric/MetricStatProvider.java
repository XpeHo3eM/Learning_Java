package Spring_Core.task2.metric;

import Spring_Core.task2.metric.MethodMetricStat;

import java.time.LocalDateTime;
import java.util.List;

public interface MetricStatProvider {
    /**
     * Получить статистику по метрикам по всем методам за указанный период
     */
    List<MethodMetricStat> getTotalStatForPeriod(LocalDateTime from,
                                                 LocalDateTime to);

    /**
     * Получить статистику по метрикам по указанному методу за указанный период
     */
    MethodMetricStat getTotalStatByMethodForPeriod(String method,
                                                   LocalDateTime from,
                                                   LocalDateTime to);
}
