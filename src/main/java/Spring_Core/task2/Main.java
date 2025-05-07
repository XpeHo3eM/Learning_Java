package Spring_Core.task2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TestService sampleService = context.getBean(TestService.class);

        sampleService.performTask();
        sampleService.anotherTask();
    }
}
