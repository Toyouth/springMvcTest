package ConcurrentStudy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mylock {
    private int num;
    Lock lock = new ReentrantLock();
    public int  add(){
        System.out.println("num》》》》="+this.num);
        lock.lock();
        num++;
        lock.unlock();
        return num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public static void main(String[] args) throws InterruptedException {
        final MySynchronized num = new MySynchronized();
        num.setNum(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 150; i++) {
                    num.add();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    num.add();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    num.add();
                }
            }
        }).start();

        //Thread.sleep(10000);
        //System.exit(0);
    }
}
