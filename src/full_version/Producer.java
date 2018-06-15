package full_version;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.nio.file.Path;



class Producer extends Thread
{

    private Queue <Product> buffer;
    private int maxSize;
    private String name;
    private FilesQueue filesQueue;
    
    public Producer(Queue<Product> buffer,int maxSize,String name,FilesQueue filesQueue)
    {
        this.filesQueue = filesQueue;
        this.buffer = buffer;
        this.maxSize = maxSize;
        this. name = name;
    }
    public void run()
    { 
        String line = null;
        int cnt = 0;
        Path currentFile = null;
    
        while(true)
        {  
            synchronized(this.filesQueue)
            {
                if(!this.filesQueue.get_result().isEmpty())
                currentFile = this.filesQueue.get_result().remove();
                else return;
            }
    
            String filename = currentFile.toString();
            cnt = 0;
    
            try (BufferedReader in = new BufferedReader( new FileReader(filename));)
            {
       
                while( (line = in.readLine())!= null)
                {   
                    cnt++;
                    Product p = new Product(filename,cnt,line);
           
                    synchronized(this.buffer)
                    {
                        while (this.buffer.size() == maxSize) 
                        {                        
                            try 
                            {   
                                this.buffer.wait(); 
                            }
                            catch (InterruptedException e) 
                            { 
                             
                            } 
                        }
                        this.buffer.add(p);      
                        this.buffer.notifyAll();
                    }
        
                }
        
            }
            catch(IOException e)
            {
                 e.printStackTrace();
            }

        }
    }


}
