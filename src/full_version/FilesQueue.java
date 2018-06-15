package full_version;



import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.util.LinkedList;
import java.util.Queue;
import java.nio.file.DirectoryIteratorException;
   
    
    

public class FilesQueue 
{
    private Queue<Path> result = new LinkedList<>();
    
    public FilesQueue(Path dir)
    {
     this.FillQueue(dir);
    }
    
    public Queue<Path> get_result()
    {        
        return this.result;
    }
    
    static DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() 
    {
         public boolean accept(Path file) throws IOException 
         {
            try 
            {
                String fileName = file.getFileName().toString();
                return ( Files.isDirectory(file) ||   fileName.endsWith(".txt") || fileName.endsWith(".dat") 
                 || fileName.endsWith(".css") );
            } 
            catch (Exception e) 
            {
                System.err.println(e);
                return false;
            }
         }
    };
   
    public  void FillQueue(Path dir)
    {
       
         try (  DirectoryStream<Path> stream = Files.newDirectoryStream(dir,this.filter)  )   
         {
             for (Path entry: stream)
             {
               if(Files.isRegularFile(entry)) 
               this.result.add(entry);
               else if(Files.isDirectory(entry))
               FillQueue(entry);
             }
         } 
         catch (DirectoryIteratorException e) 
         {
            System.err.println(e);
         }
         catch (IOException e) 
         {
             System.err.println(e);
         }  
       
    }
    
}
