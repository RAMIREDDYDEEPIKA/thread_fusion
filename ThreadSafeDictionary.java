import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeDictionary
{
    private ConcurrentHashMap<String,String> data;

    public ThreadSafeDictionary(){
        data=new ConcurrentHashMap<>();
    }
    public void put(String key,String value){
        data.put(key,value);
        System.out.println("key : "+key+" , value : "+value);
    }
    public void get(String key){
        String gettingValue=data.get(key);
        if(gettingValue!=null){
            System.out.println("key -> "+key);
        }
    }

    public static void main(String[] args) {

        ThreadSafeDictionary data=new ThreadSafeDictionary();
        Thread thread1=new Thread(()->{
                data.put("1","poojitha");
                data.put("2","Nisha");
                data.put("3","USha");
        });

        Thread thread2=new Thread(()->{
            data.get("1");
        });

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Threads execution finishes");
    }
}