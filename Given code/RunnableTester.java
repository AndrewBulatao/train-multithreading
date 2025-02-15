package part1;

//Multiple threads printing at different intervals.
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class RunnableTester 
{
	public static void main( String[] args )
	{
	   // create and name each runnable
	   PrintTask task1 = new PrintTask( "Thread-1" );
	   PrintTask task2 = new PrintTask( "Thread-2" );
	   PrintTask task3 = new PrintTask( "Thread-3" );
	     
	   System.out.println( "* * Starting Threads \n" );
	
	   // create ExecutorService to manage threads
	   ExecutorService threadExecutor = Executors.newCachedThreadPool();
	
	   // Starting threads
	   System.out.println( "* * All Threads Started . . .\n" );
	   // start threads and place in runnable state
	   threadExecutor.execute( task1 ); // start task1
	   threadExecutor.execute( task2 ); // start task2
	   threadExecutor.execute( task3 ); // start task3
	   
	   // Shutdown the ExecutorService object
	   threadExecutor.shutdown(); 
	   while (!threadExecutor.isTerminated()) 
	   {
	 	  //wait for all threads to terminate
	   }     
	   System.out.println( "\n* * All Threads Terminated. Main Ends.\n" );
	} // end main
} // end class RunnableTester