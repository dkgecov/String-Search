
package full_version;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author John
 */
public class FilesQueueTest {
    
   @Test
   public void resultSizeMustEqual8()
   {
       FilesQueue Q=new FilesQueue( Paths.get("D:\\files\\java\\UnitTestDirectory")  );
   
       assertEquals(8,Q.get_result().size());
   }
    
}
