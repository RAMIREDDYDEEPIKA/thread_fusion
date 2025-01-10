class DeadLockPojo extends Thread
{
    final Object lock1;
    final Object lock2;

    public DeadLockPojo(Object lock1,Object lock2) {
        this.lock1 =lock1;
        this.lock2=lock2;
    }

    public void run()
    {
        synchronized (lock1)
        {
            System.out.println(Thread.currentThread().getName()+" :holding resource 1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(Thread.currentThread().getName()+" :waiting for resource 2");
            synchronized (lock2)
            {
                System.out.println(Thread.currentThread().getName()+" :Acquired resource 2");
            }
        }
    }
}
public class DeadLockExample
{
    public static void main(String[] args) {
        Object lock1=new Object();
        Object lock2=new Object();
        DeadLockPojo t1=new DeadLockPojo(lock1,lock2);
        DeadLockPojo t2=new DeadLockPojo(lock2,lock1);
        t1.start();
        t2.start();
    }
}

//{
//    public static void main(String[] args) {
//         Object lock1=new Object();
//         Object lock2=new Object();
//
//         Thread thread1=new Thread(()->{
//             synchronized (lock1){
//                 System.out.println(Thread.currentThread().getName()+" :holding");
//                 synchronized (lock2){
//                     System.out.println(Thread.currentThread().getName()+" :acquired");
//                 }
//             }
//         });
//
//         Thread thread2=new Thread(()->{
//             synchronized (lock2){
//                 System.out.println(Thread.currentThread().getName()+" :holding");
//                 synchronized (lock1){
//                     System.out.println(Thread.currentThread().getName()+" :acquired");
//                 }
//             }
//         });
//
//         thread1.start();
//         thread2.start();
//    }
//}