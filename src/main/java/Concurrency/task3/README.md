### Задача 3

Условие: На заводе 5 станков и 8 фрезеровщиков, которые работают на станках. На каждом станке в один момент времени может работать только 1 рабочий. Чтобы все работники получили получку за рабочую смену, каждый должен поработать на станке.

Решить задачу 2мя способами:

- С использованием пула потоков
- С использованием синхронайзера из java.util.concurrent


Для работы на станке можно использовать метод
```
private static void workOnMachine(int workerId) {
        try {
            System.out.println("worker " + workerId + " occupy production machine ...");
            Thread.sleep(2000);
            System.out.println("worker " + workerId + " release production machine");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
```



Вывод в консоль будет примерно таким (только порядок может быть другой):
```
worker 1 occupy production machine ...
worker 3 occupy production machine ...
worker 0 occupy production machine ...
worker 4 occupy production machine ...
worker 2 occupy production machine ...
worker 0 release production machine
worker 2 release production machine
worker 4 release production machine
worker 1 release production machine
worker 3 release production machine
worker 5 occupy production machine ...
worker 6 occupy production machine ...
worker 7 occupy production machine ...
worker 5 release production machine
worker 6 release production machine
worker 7 release production machine
```