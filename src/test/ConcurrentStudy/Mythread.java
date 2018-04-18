package ConcurrentStudy;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 创建多线程的四种方式
 * 1.继承Thread类创建线程
 * 2.实现Runnable接口创建线程
 * 3.实现Callable接口通过FutureTask包装器来创建Thread线程
 * 4.使用ExecutorService、Callable、Future实现有返回结果的线程
 * 前两种无返回值，不能抛出异常
 * @author yuhaiyang
 * @create 2018-04-17 14:13
 **/

public class Mythread {

    //继承Thread类创建线程
    class Mythread1 extends Thread{
        @Override
        public void run() {
            System.out.println(this.getClass().getCanonicalName());
            //super.run();
        }
    }

    //实现Runnable接口创建线程
    class Mythread2 implements Runnable{

        @Override
        public void run() {
            System.out.println(this.getClass().getCanonicalName());
        }
    }

    static class MyCallable<T> implements Callable<T>{

        @Override
        public T call() throws Exception {
            System.out.println(this.getClass().getCanonicalName());
            return null;
        }
    }

    public static void main(String[] args) {
        Mythread1 mythread1= new Mythread().new Mythread1();
        Mythread2 mythread2= new Mythread().new Mythread2();
        MyCallable<Object> myCallable = new MyCallable<Object>();
        FutureTask<Object> oneTask = new FutureTask<Object>(myCallable);
        Thread myThread3 = new Thread(oneTask);
        myThread3.start();
        mythread1.start();
        mythread2.run();
        System.out.println("主线程");
    }
}
