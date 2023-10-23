import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class WordThreading implements Runnable
{
	WordCount wordcount;
    Queue < String > wordQueue               = new PriorityQueue< > ( );
    Map < String , Integer > wordCountersMap = new HashMap < String , Integer > ( );


    public WordThreading( WordCount wordcount, Map< String , Integer > wordCountersMap, Queue< String > wordQueue )
    {
        this.wordcount       = wordcount;
        this.wordQueue       = wordQueue;
        this.wordCountersMap = wordCountersMap;
        
    }
    public void run( )
    {
        while ( !wordQueue.isEmpty( ) ) 
        {
        	int count   = 0;
            String line = wordQueue.poll( );
            if ( line != null )
            {
                String [ ] onlyWords = wordcount.seperateWords ( line.split ( "[ _\\.,\\-\\+]" ) );
                
                String [ ] upperCaseWords = new String[ onlyWords.length ];
                
                for ( int i = 0 ; i < onlyWords.length ; i++ ) 
                {
                	upperCaseWords[ i ] = onlyWords [ i ].toUpperCase( ); //Converts every word to upper case
	            }//end for
                
                for ( String word : upperCaseWords )
                {
                  	wordcount.finalWordCount( wordCountersMap, word, count ); 
                  										//calling finalWordCount method to store each word count
                    count++;
                }//end for
            }//end if
        }//end while
    }//end run
}//end WordThreading

public class ThreadPooling 
{
    static final int MaxThreads = 10; //Initializing maximum number of threads to 10
	static Map < String , Integer > wordCountMap = new HashMap< >( );
	static Queue < String > queueWords           = new ConcurrentLinkedQueue< >( );

    public static void main ( String[ ] args ) throws Exception  
    {																	
    	WordCount wordcount = new WordCount( );
    	
		int i = 0;
		new Thread( )
		{
		    @SuppressWarnings( "resource" )
			public void run( )
		    {													//This method reads the file and add them into queue	
		    	try
		    	{
					BufferedReader buffReader = new BufferedReader ( 
							new FileReader("F:\\Master's\\Softaware Engineering\\Assignment_3\\ThreadPolling_input.txt" ) );
					String newLine =  buffReader.readLine( );
					
					while( newLine != null )
					{
						String line = newLine;
						try 
						{
							newLine = buffReader.readLine( );
				        } 
						catch ( IOException e ) 
						{
							newLine = null;
				        }
				            queueWords.add( line );
				        				    
					}	
				} 
		    	catch ( FileNotFoundException e )
		    	{
					e.printStackTrace( );
				}
				catch ( IOException e ) 
		    	{
					e.printStackTrace( );
				}//end try-catch
		    }//end run
		    
		}.start( );
		
		
		while ( queueWords.isEmpty( ) )
		{
		    Thread.sleep( 1000 );
		}//end while
		
		ExecutorService executorService = Executors.newFixedThreadPool( MaxThreads );
		
		while( i < MaxThreads ) 
		{											//queue will be processed by the thread pool implemented by executor
			
		    executorService.execute( new WordThreading( wordcount, wordCountMap, queueWords ) );
			i++; 
		}//end while
		
		executorService.shutdown( );
		executorService.awaitTermination( 1, TimeUnit.MINUTES );
		
		System.out.println("\n"+"Total number of words in the given Input File:"+"\n");
		
		for( Entry< String , Integer > entry : wordCountMap.entrySet( ) )
		{
		    System.out.println( entry.getKey( ) + " -----> "+ entry.getValue( ) );
		}//end for
    }//end main

}//end ThreadPooling
