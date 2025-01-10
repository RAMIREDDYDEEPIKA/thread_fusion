class ThreadExample implements Runnable
{
    @Override
    public void run() {
        for(int i=0;i<=10;i++){
            System.out.println(i+" working thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class ThreadExampleMain
{
    public static void main(String[] args) {

        ThreadExample threadExample=new ThreadExample();
        Thread t1=new Thread(threadExample);
        t1.start();
        for(int i=20;i<=30;i++)
        {
            System.out.println(i+" main thread");
        }
    }
}
