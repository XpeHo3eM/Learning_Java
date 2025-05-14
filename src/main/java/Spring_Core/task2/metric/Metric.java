package Spring_Core.task2.metric;

import java.time.LocalDateTime;

public record Metric(String methodName,
                     LocalDateTime timestamp,
                     Long duration) {
}
