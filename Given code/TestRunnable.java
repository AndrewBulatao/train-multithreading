
package part1;

//Class to illustrate threads created via Runnable interface
public class TestRunnable {
	// Create threads
	Thread printA = new Thread(new part1_implement_runnable.PrintChar('a', 20));
	Thread printB = new Thread(new part1_implement_runnable.PrintChar('b', 20));
	Thread print20 = new Thread(new part1_implement_runnable.PrintNum(20));
	
		
		
	public static void main(String[] args) {
	  new TestRunnable();
	}
	
	public TestRunnable() {
	  // Start threads
	  printB.start();
	  //print20.start();
	  printA.start();
      //printB.start();
	  print20.start();
	  //printB.start();
	  //printB.start();
	}
}//end class TestRunnable

