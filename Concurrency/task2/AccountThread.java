package task2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountThread implements Runnable {
    private static final Lock lock = new ReentrantLock();
    private final Account accountFrom;
    private final Account accountTo;
    private final int money;

    public AccountThread(Account accountFrom, Account accountTo, int money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4000; i++) {
//            // Deadlock
//            synchronized (accountFrom) {
//                if (accountFrom.takeOffMoney(money)) {
//                    synchronized (accountTo) {
//                        accountTo.addMoney(money);
//                    }
//                }
//            }

            lock.lock();
            if (accountFrom.takeOffMoney(money)) {
                accountTo.addMoney(money);
            }
            lock.unlock();
        }
    }
}