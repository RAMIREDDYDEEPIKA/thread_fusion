import java.util.concurrent.CountDownLatch;

class Worker extends Thread{
    private final CountDownLatch latch;

    public Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run(){
        try{
            System.out.println(Thread.currentThread().getName()+" is working");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+" is finished");
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally {
            latch.countDown();
        }
    }
}
public class CountDownLatchMain
{
    public static void main(String[] args) {

        CountDownLatch latch=new CountDownLatch(3);

        for(int i=0;i<=3;i++){
            new Worker(latch).start();
        }
        try{
            latch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Thread execution finished");
    }
}