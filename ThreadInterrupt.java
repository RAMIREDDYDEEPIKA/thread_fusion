class ThreadIsInterrupt extends Thread
{
    private final int threadId;

    public ThreadIsInterrupt(int threadId){
        this.threadId=threadId;
    }

    public void run()
    {
        try{
            for(int i=0;i<5;i++){
                System.out.println(i+" Thread "+threadId+" is running");
                Thread.sleep(1000);
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(i+" Thread "+threadId+" is interrupted");
                    break;
                }
            }
        }
        catch (InterruptedException e) {
            System.out.println("Thread "+threadId+" is interrupted during sleep");
        }
    }
}

public class ThreadInterrupt
{
    public static void main(String[] args) throws InterruptedException {

        ThreadIsInterrupt thread=new ThreadIsInterrupt(3);
        thread.start();
        Thread.sleep(4000);

        System.out.println("Interrupting the thread");
        thread.interrupt();

        thread.join();
        System.out.println("Execution finished");
    }
}