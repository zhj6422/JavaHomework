package threadMehtod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

public class CyclicBarrierMethod {
    public static String hello(String name){
        return " Hello, " + name;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int num = 2;
        CyclicBarrier barrier = new CyclicBarrier(num, new BarrierAction());
        List<CompletableFuture> list = new ArrayList<>(num);
        for(int i = 0; i < num; i++){
            CompletableFuture<Void> future = CompletableFuture.runAsync(new CyclicBarrierTask(barrier));
            list.add(future);
        }
        for(CompletableFuture future : list){
            System.out.println(future.get());
        }
        barrier.reset();
        System.out.println("主线程做完了");
    }

}

class CyclicBarrierTask implements Runnable{
    private CyclicBarrier barrier;
    public CyclicBarrierTask(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName() + "：我到了，我先等待");
            this.barrier.await();
            String result = CountDownLatchMethod.hello("zhj6422");
            System.out.println(Thread.currentThread().getName() + result + " 边吃边聊");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class BarrierAction implements Runnable{

    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName() + ": 大家吃饭");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}