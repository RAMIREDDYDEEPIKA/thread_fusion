class LiveLock extends Thread {
    final Object resource1;
    final Object resource2;

    public LiveLock(Object resource1, Object resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    public void run() {
        while (true) {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + " :holding resource 1");
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " :waiting for resource 2");
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " :acquired resource 2");
                }
            }
        }
    }
}

public class LiveLockExample {
    public static void main(String[] args) {
        Object resource1 = new Object();
        Object resource2 = new Object();

        Thread thread1 = new LiveLock(resource1, resource2);
        Thread thread2 = new LiveLock(resource2, resource1);

        thread1.start();
        thread2.start();
    }
}