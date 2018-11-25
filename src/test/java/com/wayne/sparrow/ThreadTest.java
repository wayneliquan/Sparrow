package com.wayne.sparrow;

/**
 * 测试线程等待
 */
public class ThreadTest {

    public static void main(String[] args) {
        Object object = new Object();
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    System.out.println("t1 start run");
                    synchronized (object) {
                        object.wait();
                    }
                    System.out.println("T1 wake up");
                } catch (InterruptedException e) {
                    System.out.println("t1 failed");
                    e.printStackTrace();
                }
            }
        });
        Tester tester = new Tester(object);
        Thread t2 = new Thread(tester);
        t.start();
        t2.start();

    }
}

class Tester implements Runnable {

    private Object notify;
    public Tester(Object notify) {
        this.notify = notify;
    }

    @Override
    public void run() {
        try {
            System.out.println("t2 start");
            Thread.sleep(2000);
            synchronized (notify) {
                notify.notify();
            }
            System.out.println("t2 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("t2 exception");
        }
    }
}
