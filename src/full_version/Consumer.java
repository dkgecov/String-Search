package full_version;

import java.util.Queue;


class Consumer extends Thread
{
    private Queue <Product> buffer;
    private String name;
    private String string;

    public Consumer(Queue<Product> buffer,String name,String string)
    {

        this.buffer = buffer;
        this. name = name;
        this.string = string;
    }
    
    public void run()
    {
        while (true) 
        {    
            synchronized (this.buffer) 
            {
                while (this.buffer.isEmpty()) // 
                {
                    if( this.isInterrupted() ) return;//nenujno
                    try 
                    { 
                        this.buffer.wait(); 
                    } 
                    catch (InterruptedException e) 
                    { 
                        return;//когато буфера е празен и когато producerite са приключили викаме thread.innterupt
                        //които поражда exception докато wait или sleep
                    } 
                    
                } 
                for(int i=0;i<6;++i)//moje da se sloji otvun
                   if(!this.buffer.isEmpty())
                this.consume(this.buffer.remove());
                    else break;
                this.buffer.notifyAll();
            }
        } 
    } 
    
    private void consume(Product product)
          
    {
        if(  product.get_line().contains(this.string)  )
        System.out.println(product.get_filename() + " " + product.get_row() + " " + product.get_line());
        
    }
    
}

/*
Разглеждайки кода на горната програма и документацията на методите wait() и notify(),
вероятно ще ви направи впечатление, че тези методи могат да се викат само от код, който е синхронизиран по обекта,
на който те принадлежат. Това е така и в горната програма. На всякъде, където се извикват методите wait() и notify(),
те са разположени в синхронизиран код.

Нека помислим малко какво се случва при изпълнението на горната програма. Ако методът за заспиване и методът
за събуждане се викат от различни нишки и са в блокове код, синхронизирани по един и същ обект, би трябвало
ако първата нишка заспи по време на изпълнение на синхронизиран код, кодът, който я събужда, никога да не се изпълни,
защото ще чака излизането на заспалата нишка от синхронизирания код. Изглежда, че и двете нишки ще заспят за вечни времена.
Първата нишка ще чака втората да я събуди, а втората преди да се опита да събуди първата ще я чака да излезе
от синхронизирания код за да изпълни своя синхронизиран код, а пък това няма да се случи никога понеже първата нишка е
заспала. Изглежда, че има нещо нередно в горната програма.

Защо горните разсъждения са грешни? Отговорът ще открием ако се вгледаме внимателно в документацията на метода wait(). 
Извикването на wait() не само приспива текущата нишка, но и отключва обекта, по който тя е синхронизирана. 
Това позволява на блока, извикващ notify(), който е синхронизиран по същия обект, да се изпълни без да чака.
Извикването на notify() събужда заспалата нишка, но не й разрешава веднага да продължи изпълнението си. 
Събудената нишка изчаква завършването на синхронизирания блок, от който е извикан notify().*/