package Spring_Core.task2;

import Spring_Core.task2.metric.MetricStorage;
import Spring_Core.task2.metric.MetricStorageImpl;
import Spring_Core.task2.test.TestServiceClassAnnotated;
import Spring_Core.task2.test.TestServiceMethodAnnotated;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TestServiceClassAnnotated classAnnotatedService = context.getBean(TestServiceClassAnnotated.class);
        TestServiceMethodAnnotated methodAnnotatedService = context.getBean(TestServiceMethodAnnotated.class);
        MetricStorage storage = context.getBean(MetricStorage.class);

        for (int i = 0; i < 3; ++i) {
            classAnnotatedService.performTask();
            classAnnotatedService.anotherTask();

            methodAnnotatedService.performTask();
            methodAnnotatedService.anotherTask();
        }

        storage.getMetricsForPeriodByMethodName("performTask",
                LocalDateTime.now().minusSeconds(10),
                LocalDateTime.now())
                .forEach(System.out::println);
    }
}
