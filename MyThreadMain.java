class MyThread extends Thread
{
    static Object lock=new Object();
    public void run()
    {
        try {
            System.out.println("Thread 1 starting...");
            Thread.sleep(1000);
            System.out.println("Thread 1 after sleep...");
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        synchronized (lock){
            try {
                System.out.println("before waiting....");
                lock.wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class MyThreadMain
{
    public static void main(String[] args)
    {
        MyThread t1 = new MyThread();
        t1.start();
        MyThread t2=new MyThread();
        t2.start();
        try{
            Thread.sleep(2000);
            synchronized (MyThread.lock){

               MyThread.lock.notifyAll();
                System.out.println(Thread.currentThread().getName()+" notified");
            }
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("all threads executed");
    }
}