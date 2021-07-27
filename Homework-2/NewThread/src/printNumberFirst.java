import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class printNumberFirst extends Thread{
    private ReentrantLock lock;
    private Condition cond;
    private CustomInteger val;

    printNumberFirst(){
        this.lock = new ReentrantLock();
        this.cond = this.lock.newCondition();
        this.val = new CustomInteger();
    }

    printNumberFirst(ReentrantLock lock, Condition cond, CustomInteger val){
        this.lock = lock;
        this.cond = cond;
        this.val = val;
    }

    public void run(){
        for (;this.val.getVal() <= 120;){
            try {
                this.lock.lock();
                this.cond.signalAll();
                System.out.println("进程" + Thread.currentThread().getName() + "打印:" + val.getVal());
                this.val.autoIncrease();
                try {
                    this.cond.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }finally {
                this.lock.unlock();
            }
        }
    }

    public static void quickStart(){
        ReentrantLock lock = new ReentrantLock();
        Condition cond = lock.newCondition();
        CustomInteger val = new CustomInteger(1);
        var p1 = new printNumberFirst(lock, cond, val);
        var p2 = new printNumberFirst(lock, cond, val);
        var p3 = new printNumberFirst(lock, cond, val);
        p1.start();
        p2.start();
        p3.start();
    }
}
