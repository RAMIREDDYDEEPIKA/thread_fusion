class SingletonSynchronizedBlock
{
    private static SingletonSynchronizedBlock instance;

    private SingletonSynchronizedBlock() {
        System.out.println("Singleton Synchronized block called :");
    }

    public static SingletonSynchronizedBlock getInstance() {
        if(instance==null) {
            synchronized (SingletonSynchronizedBlock.class) {
                if (instance==null) {
                    instance=new SingletonSynchronizedBlock();
                }
            }
        }
        return instance;
    }

    public void passingMsg(String message) {
        System.out.println("This is -> "+message);
    }
}

class SingletonSynchronizedMethod
{
    private static SingletonSynchronizedMethod instance;

    private SingletonSynchronizedMethod() {
        System.out.println("Singleton Synchronized method called :");
    }

    public static synchronized SingletonSynchronizedMethod getInstance() {
        if(instance==null) {
            instance=new SingletonSynchronizedMethod();
        }
        return instance;
    }

    public void messagePass(String message) {
        System.out.println("This is -> "+message);
    }
}

public class SingletonExample {

    public static void main(String[] args) {

        SingletonSynchronizedBlock singleton =SingletonSynchronizedBlock.getInstance();
        singleton.passingMsg("First message called");
        SingletonSynchronizedBlock singleton2 =SingletonSynchronizedBlock.getInstance();
        singleton2.passingMsg("Second message called");

        SingletonSynchronizedMethod s1 = SingletonSynchronizedMethod.getInstance();
        s1.messagePass("First message from synchronized method");
        SingletonSynchronizedMethod s2 = SingletonSynchronizedMethod.getInstance();
        s2.messagePass("Second message from synchronized method");
    }
}