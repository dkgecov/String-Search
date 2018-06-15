/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package full_version;

import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author John
 */
public class ProducerTest 
{
    
  @Test
  public void ProductsInStoreMustEqual56() throws InterruptedException
  {
     FilesQueue MyQueue=new FilesQueue(  Paths.get("D:\\files\\java\\UnitTestDirectory")  );
     Queue<Product> buffer = new LinkedList<>();
     int maxSize = 3000;
     Thread producer1 = new Producer(buffer, maxSize,"producer1",MyQueue); 
      
     producer1.start();
     
     
       producer1.join();
     
     
  
     assertEquals(56,buffer.size());
  }
     
}
