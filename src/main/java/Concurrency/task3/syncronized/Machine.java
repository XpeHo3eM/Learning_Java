package Concurrency.task3.syncronized;

import java.util.concurrent.Semaphore;

public class Machine implements Runnable {
    private static Semaphore SEMAPHORE;
    private final int workerId;

    public static void setMachineCount(int count) {
        SEMAPHORE = new Semaphore(count);
    }

    public Machine(int workerId) {
        this.workerId = workerId;
    }

    @Override
    public void run() {
        try {
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        workOnMachine(workerId);
        SEMAPHORE.release();
    }

    private void workOnMachine(int workerId) {
        try {
            System.out.println("worker " + workerId + " occupy production machine ...");
            Thread.sleep(2000);
            System.out.println("worker " + workerId + " release production machine");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final int MACHINE_COUNT = 5;
        Machine.setMachineCount(MACHINE_COUNT);

        final int WORKER_COUNT = 8;
        for (int i = 1; i <= WORKER_COUNT; ++i) {
            new Thread(new Machine(i)).start();
        }
    }
}
