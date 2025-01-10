import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class Producer extends Thread{
    private final Queue<Integer> sharedQueue;
    private final Semaphore emptySlot;
    private final Semaphore filledSlot;

    public Producer(Queue<Integer> sharedQueue, Semaphore emptySlot, Semaphore filledSlot) {
        this.sharedQueue = sharedQueue;
        this.emptySlot = emptySlot;
        this.filledSlot = filledSlot;
    }

    public void run(){
        for(int i=0;i<10;i++) {
            try {
                emptySlot.acquire();
                synchronized (sharedQueue){
                    sharedQueue.add(i);
                    System.out.println("produced "+i);
                }
                filledSlot.release();
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}

class Consumer extends Thread{
    private final Queue<Integer> sharedQueue;
    private final Semaphore emptySlot;
    private final Semaphore filledSlot;

    public Consumer(Queue<Integer> sharedQueue, Semaphore emptySlot, Semaphore filledSlot) {
        this.sharedQueue = sharedQueue;
        this.emptySlot = emptySlot;
        this.filledSlot = filledSlot;
    }

    public void run(){
        for(int i=0;i<10;i++){
            try{
                int item;
                filledSlot.acquire();
                synchronized (sharedQueue){
                    item=sharedQueue.poll();
                    System.out.println("Consumed "+item);
                }
                emptySlot.release();
                Thread.sleep(500);
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}

public class ProducerConsumerMain
{
    public static void main(String[] args) {

        int maxSize=10;
        Semaphore emptySlots=new Semaphore(maxSize);
        Semaphore filledSlots=new Semaphore(0);

        Queue<Integer> sharedQueue = new LinkedList<>();
        Producer producer=new Producer(sharedQueue,emptySlots,filledSlots);
        Consumer consumer=new Consumer(sharedQueue,emptySlots,filledSlots);
        
        producer.start();
        consumer.start();
    }
}