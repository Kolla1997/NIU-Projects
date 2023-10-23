/****************************************************************
 GoodMovie

Author   :  Dinesh Kolla

Z-number :  z1935563

Description: This class is used to store movies most related to the target movie

****************************************************************/
package hw4;
import java.util.ArrayList;
import java.util.List;

public class GoodMovie
{
	 List< String > Movies = new ArrayList< >();
	
	public GoodMovie( String movieName , double r )
	{
		Movies.add( movieName );
		Movies.add( String.format ( "%.6f", r ) );
		
	}//end GoodMovie constructor
	
/***************************************************************

    			Getters and Setters

***************************************************************/

	public void setMovie( String movieName )
	{
		Movies.set( 0 , movieName );
	}//end setMovie
	
	public String getMovie( )
	{
		return Movies.get( 0 );
	}//end getMovie
	
	public void setrValue( double r )
	{
		Movies.set ( 1 , String.format( "%.6f", r ) );
	}//end setrValue
	public String getrValue( ) 
	{
		return Movies.get( 1 );
	}//end getrValue
	
}//end GoodMovie
