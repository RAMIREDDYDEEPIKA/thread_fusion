public class RunnableLamdaExpression
{
    public static void main(String[] args) {

        Runnable runnable=()->
                System.out.println("Thread 1 running...."+Thread.currentThread());
        Thread t1=new Thread(runnable);
        t1.start();
    }
}
