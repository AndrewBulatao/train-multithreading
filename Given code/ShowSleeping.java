
package part1;

//Illustrates putting a process to sleep
import java.util.*;

public class ShowSleeping {

	public static void main(String[] args) {
	   Date t1 = new Date();
	   System.out.println(" Thread goes to sleep at: " + t1);
	   try {
		   Thread.sleep(10000);
	   }
	   catch (InterruptedException e) {
	   }
	   Date t2 = new Date();
	   System.out.println(" Thread wakes up at: " + t2);
	}  
}