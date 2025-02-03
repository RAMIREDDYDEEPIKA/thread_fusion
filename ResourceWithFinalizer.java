public class ResourceWithFinalizer implements AutoCloseable{

    private final String resourceName;

    public ResourceWithFinalizer(String resourceName) {
        this.resourceName = resourceName;
        System.out.println(resourceName+" is created");
    }

    public void close(){
        System.out.println(resourceName+" is closed");
    }

    public static void main(String[] args) {

        try(ResourceWithFinalizer resource1=new ResourceWithFinalizer("resource 1");
            ResourceWithFinalizer resource2=new ResourceWithFinalizer("resource 2");
            ResourceWithFinalizer resource3=new ResourceWithFinalizer("resource 3")){
            System.out.println("resource created using try with resource");
        }

        System.gc();
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            System.out.println(e.getMessage());
        }
        System.out.println("execution finished");
    }
}