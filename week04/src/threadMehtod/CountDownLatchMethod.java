package threadMehtod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class CountDownLatchMethod {
    public static String hello(String name){
        return " Hello, " + name;
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int num = 5;
        CountDownLatch latch = new CountDownLatch(num);
        List<CompletableFuture> list = new ArrayList<>(num);
        for(int i = 0; i < num; i++){
            CompletableFuture<Void> future = CompletableFuture.runAsync(new CountDownLatchTask(latch));
            list.add(future);
        }
        latch.await();
        System.out.println("大家一起去吃饭");
        for(CompletableFuture future : list){
            System.out.println(future.get());
        }

        System.out.println("主线程做完了");

    }
}

class CountDownLatchTask implements Runnable{
    private CountDownLatch latch;
    public CountDownLatchTask(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run(){
        try{
            String result = CountDownLatchMethod.hello("zhj6422");
            System.out.println(Thread.currentThread().getName() + result);
            System.out.println(Thread.currentThread().getName() + "：我干完活了");
            this.latch.countDown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
