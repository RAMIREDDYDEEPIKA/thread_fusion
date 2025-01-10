package garbageCollection_prblms;

import java.lang.ref.Cleaner;

public class GCexample {

    private String name;

    public GCexample(String name) {
        this.name = name;
    }

    private static Cleaner cleaner=Cleaner.create();

    private static class CleanableResource implements Runnable
    {
        String name;

        public CleanableResource(String name){
            this.name=name;
            System.out.println("Object created "+name);
        }

        @Override
        public void run() {
            System.out.println("cleaned up the resource "+name);
        }
    }

    public static void main(String[] args) {

        GCexample obj1=new GCexample("o 1");
        GCexample obj2=new GCexample("o 2");
        GCexample obj3=new GCexample("o 3");

        Cleaner.Cleanable cleanable1=cleaner.register(obj1,new CleanableResource("object 1"));
        Cleaner.Cleanable cleanable2=cleaner.register(obj2,new CleanableResource("object 2"));
        Cleaner.Cleanable cleanable3=cleaner.register(obj3,new CleanableResource("object 3"));

        obj1=null;
 //       obj2=null;

        System.gc();
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Garbage collected...");
    }
}