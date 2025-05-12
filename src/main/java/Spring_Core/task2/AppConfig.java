package Spring_Core.task2;

import Spring_Core.task2.metric.MetricProxyPostProcessor;
import Spring_Core.task2.metric.MetricStorage;
import Spring_Core.task2.metric.MetricStorageImpl;
import Spring_Core.task2.test.TestServiceClassAnnotated;
import Spring_Core.task2.test.TestServiceMethodAnnotated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MetricProxyPostProcessor.class)
public class AppConfig {

    @Bean
    public TestServiceClassAnnotated testServiceClassAnnotated() {
        return new TestServiceClassAnnotated();
    }

    @Bean
    public TestServiceMethodAnnotated testServiceMethodAnnotated() {
        return new TestServiceMethodAnnotated();
    }

    @Bean
    public MetricStorage metricStorage() {
        return new MetricStorageImpl();
    }
}
