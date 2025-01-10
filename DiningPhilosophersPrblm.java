import java.util.concurrent.Semaphore;

class Philosophers extends Thread{

    private final int id_number;
    private final Semaphore leftChopStick;
    private final Semaphore rightChopStick;

    public Philosophers(int id_number, Semaphore leftChopStick, Semaphore rightChopStick) {
        this.id_number = id_number;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
    }

    public void think() throws InterruptedException{
        System.out.println("philosopher "+id_number+" is thinking");
        Thread.sleep(500);
    }

    public void eat() throws InterruptedException{
        System.out.println("Philosopher "+id_number+" is eating");
    }

    public void run(){
        try{
            while(true){
                think();
                leftChopStick.acquire();
                System.out.println("philosopher "+id_number+" holding left chopstick");
                rightChopStick.acquire();
                System.out.println("philosopher "+id_number+" holding right chopstick");

                eat();
                leftChopStick.release();
                System.out.println("philosopher "+id_number+" waiting for left chopstick");
                rightChopStick.release();
                System.out.println("philosopher "+id_number+" waiting for right chopstick");
            }
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
public class DiningPhilosophersPrblm
{
    public static void main(String[] args) {

         int numPhilosophers=5;
         Semaphore[] chopsticks=new Semaphore[numPhilosophers];

         for(int i=0;i<numPhilosophers;i++){
             chopsticks[i]=new Semaphore(1);
         }

         Philosophers[] philosophers=new Philosophers[numPhilosophers];
         for(int i=0;i<numPhilosophers;i++){
             Semaphore leftChopStick=chopsticks[i];
             Semaphore rightChopStick=chopsticks[(i+1)%numPhilosophers];

             if(i==numPhilosophers-1){
                 philosophers[i]=new Philosophers(i,leftChopStick,rightChopStick);
             }
             else{
                 philosophers[i]=new Philosophers(i,rightChopStick,leftChopStick);
             }
             philosophers[i].start();
         }
    }
}