package task3.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Machine implements Runnable {
    private int workerId;

    public Machine(int workerId) {
        this.workerId = workerId;
    }

    @Override
    public void run() {
        workOnMachine(workerId);
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
        final int WORKER_COUNT = 8;

        ExecutorService executorService = Executors.newFixedThreadPool(MACHINE_COUNT);
        for (int i = 1; i <= WORKER_COUNT; ++i) {
            executorService.execute(new Machine(i));
        }
        executorService.shutdown();
    }
}
