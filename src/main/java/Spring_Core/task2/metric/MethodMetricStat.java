package Spring_Core.task2.metric;

import lombok.*;

@Setter
@Getter
@Builder
public class MethodMetricStat {
    /**
     * Наименование/идентификатор метода
     */
    private final String methodName;

    /**
     * Кол-во вызовов метода
     */
    private final int invocationsCount;

    /**
     * Минимальное время работы метода
     */
    private final int minTime;

    /**
     * Среднее время работы метода
     */
    private final int averageTime;

    /**
     * максимальное время работы метода
     */
    private final int maxTime;
}