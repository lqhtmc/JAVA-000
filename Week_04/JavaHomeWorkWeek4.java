package jcu;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？
 */
public class JavaHomeWorkWeek3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //方法1：FutureTask
        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return sum();
            }
        });
        new Thread(task).start();
        System.out.println("new thread t1 result: " + task.get());
        Thread.sleep(5000);
        //方法二：join
        JavaHomeWorkWeek3.R r = new R();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                r.result = sum();
            }
        });
        t2.start();
        t2.join();
        System.out.println("new thread t2 result: " + r.result);
        Thread.sleep(5000);
        //方法三：CountDownLatch
        r.result = 0;
        CountDownLatch latch = new CountDownLatch(1);
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                r.result = sum();
                latch.countDown();
            }
        });
        t3.start();
        latch.await();
        System.out.println("new thread t3 result: " + r.result);
    }

    private static class R {
        int result;
    }
    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
