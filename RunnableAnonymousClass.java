public class RunnableAnonymousClass
{
    public static void main(String[] args) {

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 1 printing... "+Thread.currentThread().getName());
            }
        };

        Thread t1=new Thread(runnable);
        t1.start();
    }
}