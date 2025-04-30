package task1.example2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Leg implements Runnable {
    private static boolean leftLegTurn = false;
    private static final Lock lock = new ReentrantLock();
    private final String name;

    public Leg(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (leftLegTurn && name.equals("right")) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (!leftLegTurn && name.equals("left")) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println(name);
                leftLegTurn = !leftLegTurn;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(new Leg("left")),
                CompletableFuture.runAsync(new Leg("right"))
        ).join();
    }
}
