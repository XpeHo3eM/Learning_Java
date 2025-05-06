package Concurrency.task1.example1;

import java.util.concurrent.CompletableFuture;

public class Leg implements Runnable {
    private final String name;

    public Leg(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
    }

    public static void main(String[] args) {
        while (true) {
            CompletableFuture.allOf(
                    CompletableFuture.runAsync(new Leg("left")),
                    CompletableFuture.runAsync(new Leg("right"))
            ).join();
        }
    }
}