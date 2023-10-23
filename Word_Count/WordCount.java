import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordCount
{
	public String [ ] seperateWords( String[ ] words ) 
	{
		List < String > eachWords = new ArrayList< >( );
		for ( String word : words)
		{
			if (word.matches("[a-zA-Z]+") )
			{
				eachWords.add(word); //add each word of input file to list
				
			}//end if
			
		}//end for
		return eachWords.toArray(new String[eachWords.size()]); //type casting the list to string array and returning the array
	}//end seperateWords

	public synchronized void finalWordCount(Map<String, Integer> wordCounts, String word, int count) 
	{
		if (wordCounts.containsKey(word)) 
		{
			wordCounts.put(word, wordCounts.get(word) + 1);
		} 
		else
		{
			wordCounts.put(word, 1);
		}//end if-else
		
		if (count == 0)
		{
			System.out.println("\n"+"Current "+ Thread.currentThread().getName()+" is Running");
													//this prints which thread is executing 
			System.out.println("WORD"+"\t"+"\t"+"COUNT");
			
		}//end if
		
		System.out.printf("%-15s", word);
		System.out.printf("%5d\n", wordCounts.get(word));
	}//end finalWordCount

}//end WordCount
