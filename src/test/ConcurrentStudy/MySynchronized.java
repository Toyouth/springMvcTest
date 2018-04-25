package ConcurrentStudy;

/**
 * synchronized 学习
 *
 * @author yuhaiyang
 * @create 2018-04-21 10:36
 **/

public class MySynchronized {
    private int num;

    public  synchronized int  add(){
        System.out.println("num》》》》="+this.num);
        return this.num++;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public static void main(String[] args) throws InterruptedException {
        final MySynchronized num = new MySynchronized();
        num.setNum(1);

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
