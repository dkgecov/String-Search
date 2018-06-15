package full_version;


public class Product 
{
   private String filename;
   private int row;
   private String line;
   
   public Product(String filename,int row,String line)
   {
       this.filename=filename;
       this.row=row;
       this.line=line;
   }
   
   public String get_filename(){return this.filename;}
   public int get_row(){return this.row;}
   public String get_line(){return this.line;}
}
