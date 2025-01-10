import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class ThreadSafeQueue<T>{

    private final Queue<T> queue=new ConcurrentLinkedQueue<>();

    public synchronized void enqueue(T item){
        queue.add(item);
        System.out.println("Enqueue "+ item);
    }

    public synchronized void dequeue(){
        if(queue.isEmpty()){
            System.out.println("Queue is empty");
        }
        T item=queue.poll();
        System.out.println("Dequeue "+item);
    }
}

class Produced extends Thread
{
    private final ThreadSafeQueue<Integer> queue;

    public Produced(ThreadSafeQueue<Integer> queue) {
        this.queue=queue;
    }

    public void run()
    {
        for(int i=0;i<5;i++){
            queue.enqueue(i);
            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class Consumed extends Thread
{
    private final ThreadSafeQueue<Integer> queue;

    public Consumed(ThreadSafeQueue<Integer> queue){
        this.queue=queue;
    }

    public void run(){
        for(int i=0;i<5;i++){
            queue.dequeue();
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class ThreadSafeQueueExmple
{
    public static void main(String[] args) {

        ThreadSafeQueue<Integer> queue=new ThreadSafeQueue<>();

        Produced produced=new Produced(queue);
        Consumed consumed=new Consumed(queue);

        produced.start();
        consumed.start();

        try{
            produced.join();
            consumed.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Execution finished");
    }
}