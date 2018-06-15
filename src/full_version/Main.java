package full_version;

import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author John
 */
public class Main 
{
    
    public static void main(String[] args)
    {
      
        FilesQueue MyQueue=new FilesQueue(  Paths.get("D:\\lfs 06b")  );
       
        
         Queue<Product> buffer = new LinkedList<>(); 
         int maxSize = 3000;
         
        Thread producer1 = new Producer(buffer, maxSize,"producer1",MyQueue); 
        //Producer producer1 = new Producer(buffer, maxSize,"producer1",MyQueue); 
       // Thread t=new Thread(producer1);t.start();
        Thread producer2 = new Producer(buffer, maxSize,"producer2",MyQueue);
        Thread producer3 = new Producer(buffer, maxSize,"producer3",MyQueue);
        Thread producer4 = new Producer(buffer, maxSize,"producer4",MyQueue);
        Thread producer5 = new Producer(buffer, maxSize,"producer5",MyQueue);
        Thread producer6 = new Producer(buffer, maxSize,"producer6",MyQueue);
   
        Thread consumer1 = new Consumer(buffer,"consumer1","galaxy"); 
        Thread consumer2 = new Consumer(buffer,"consumer2","galaxy"); 
    
    
        long t1=System.nanoTime();
   
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        producer6.start();
  
        consumer1.start();
        consumer2.start();
      
   
        try
        {
            producer1.join();
            producer2.join();
            producer3.join();
            producer4.join();
            producer5.join();
            producer6.join();
       
            consumer1.interrupt();
            consumer2.interrupt();
   
            consumer1.join();
            consumer2.join();
        }
   
        catch(InterruptedException e) 
        {
            throw new RuntimeException("Main thread was interrupted!");
        }
  
   
         long t2=System.nanoTime();
         System.out.println(t2-t1);
        
    }
     
}
