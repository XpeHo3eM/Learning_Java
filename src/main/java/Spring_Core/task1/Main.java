package Spring_Core.task1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SimpleDateFormat.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            SimpleDateFormat sdf = context.getBean(SimpleDateFormat.class);
            System.out.print("Enter time type (today, today-iso): ");

            switch (scanner.nextLine()) {
                case "today" -> System.out.println(sdf.today());
                case "today-iso" -> System.out.println(sdf.todayIso());
                default -> System.out.println("Incorrect command");
            }
        }
    }
}
