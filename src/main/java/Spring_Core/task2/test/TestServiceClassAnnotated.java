package Spring_Core.task2.test;

import Spring_Core.task2.Timed;

@Timed
public class TestServiceClassAnnotated {

    public void performTask() {
        try {
            Thread.sleep((int)(Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void anotherTask() {
        try {
            Thread.sleep((int)(Math.random() * 700));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
