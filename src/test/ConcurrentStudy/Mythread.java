package ConcurrentStudy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

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

    //实现Callable接口通过FutureTask包装器来创建Thread线程
    class MyCallable<T> implements Callable<T>{

        @Override
        public T call() throws Exception {
            System.out.println(this.getClass().getCanonicalName());
            return null;
        }
    }

    //使用ExecutorService、Callable、Future实现有返回结果的线程
    class MyCallable1 implements Callable<Object> {

        private String taskNum;

        public MyCallable1(String taskNum) {
            this.taskNum = taskNum;
        }

        @Override
        public Object call() throws Exception {
            System.out.println(">>>"+taskNum+"任务启动");
            Date dateTmpl = new Date();
            Thread.sleep(1000);
            Date dateTmpl2 = new Date();
            long time = dateTmpl2.getTime() - dateTmpl.getTime();
            System.out.println(">>>"+taskNum+"任务终止");

            return taskNum + "任务返回运行结果，当前任务时间【"+time+"毫秒";
        }
    }
    class Mythread4 {
        //线程池数量
       public void  myRun() throws ExecutionException, InterruptedException {
           Date date1 = new Date();
           int taskSize = 5;
           //创建一个线程池
           ExecutorService pool = Executors.newFixedThreadPool(taskSize);
           //创建多个有返回值的任务
           List<Future> list = new ArrayList<Future>();
           for (int i = 0; i < taskSize; i++) {
               Callable c = new Mythread().new MyCallable1(i + " ");
               //执行任务并获取Future对象
               Future f = pool.submit(c);
               list.add(f);
           }
           //关闭线程池
           pool.shutdown();
           //获取所以并发任务的运行结果
           for (Future f : list) {
               // 从Future对象上获取任务的返回值，并输出到控制台
               System.out.println(">>>" + f.get().toString());
           }

           Date date2 = new Date();
           System.out.println("----程序结束运行----，程序运行时间【"
                   + (date2.getTime() - date1.getTime()) + "毫秒】");
       }
    }

    public static void main(String[] args) {
        System.out.println("主线程---开始运行");
        Mythread1 mythread1= new Mythread().new Mythread1();
        Mythread2 mythread2= new Mythread().new Mythread2();
        //由Callable<Integer>创建一个FutureTask<Integer>对象：
        MyCallable<Object> myCallable = new Mythread().new MyCallable<Object>();
        //注释：FutureTask<Integer>是一个包装器，它通过接受Callable<Integer>来创建，它同时实现了Future和Runnable接口。
        FutureTask<Object> oneTask = new FutureTask<Object>(myCallable);
        //由FutureTask<Integer>创建一个Thread对象：
        Thread myThread3 = new Thread(oneTask);
        //使用ExecutorService、Callable、Future实现有返回结果的线程
        Mythread4 myThread4 = new Mythread().new Mythread4();

        mythread1.start();
        mythread2.run();
        myThread3.start();
        try {
            myThread4.myRun();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
