package part1;

//Class to generate threads by extending the Thread class
//TestThread - example of extending the Thread class

public class TestThread {
	// Main method 
	public static void main(String[] args) {
	   // Create threads
	   part1_extend_thread.PrintChar printA = new part1_extend_thread.PrintChar('a', 20);
	   part1_extend_thread.PrintChar printB = new part1_extend_thread.PrintChar('b', 20);
	   part1_extend_thread.PrintNum  print20 = new part1_extend_thread.PrintNum(20);

	   // Start threads
	   //print20.start();// try {print20.sleep(5000);}catch(InterruptedException e){}
	   printA.start(); // try {printA.sleep(5000);} catch(InterruptedException e) {}
	   printB.start();	    
	   // printB.start();
       print20.start();// try {print20.sleep(5000);}catch(InterruptedException e){}
       //printA.start(); // try {printA.sleep(5000);} catch(InterruptedException e) {}
	   //printB.start();	    
	  }//end main
}//end TestThread