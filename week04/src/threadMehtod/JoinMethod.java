package threadMehtod;

public class JoinMethod {

    private static String hello(String name){
        return "Hello, " + name;
    }
    public static void main(String[] args){
        System.out.println("Main begin!");
        Thread thread = new Thread(() -> {
            System.out.println("Thread begin!");
            hello("zhj6422");
            System.out.println("Thread end!");
        });
        thread.start();
        try {
            thread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main end!");
    }
}
