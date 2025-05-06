### Задача 1.

Условие. Посмотрите на код ниже. Необходимо модифицировать его так, чтобы обеспечить порядок
движения ног робота. Если выводится left, то робот двигает левую ногу, если right, то правую.
Модифицировать можно любой участок кода, но необходимо, чтобы сохранялся запуск 2 потоков.
Привести не менее 2 вариантов решения
```
public class Leg implements Runnable {

    private final String name;

    public Leg(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        CompletableFuture.allOf(
            CompletableFuture.runAsync(new Leg("left")),
            CompletableFuture.runAsync(new Leg("right"))
        ).join();
    }
}
```