//Class to illustrate threads created via Runnable interface
//modified version of the PrintNum class to implement the join() method as 
//illustrated on page 28 of part 1 of the multi-threaded notes
package part1;

public class TestRunnableRightBox {

	// Create threads
	Thread printA = new Thread(new part1_implement_runnable.PrintChar('a', 20));
	Thread printB = new Thread(new part1_implement_runnable.PrintChar('b', 20));
	Thread print20 = new Thread(new part1_implement_runnable.PrintNumRightBox(20, printB));
	
	public static void main(String[] args) {
	  new TestRunnableRightBox();
	}
	
	public TestRunnableRightBox() {
	  // Start threads
	  printA.start();
	  printB.start();
	  print20.start();
	  //printA.start();
	 // printB.start();
	}
}//end class TestRunnableRightBox

