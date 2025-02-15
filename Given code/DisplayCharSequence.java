package part1;

//Example of creating threads by extending TimerTask class
//Displays characters in separate threads
import java.util.*;

public class DisplayCharSequence extends TimerTask {
	private char displayChar;
	Timer timer;
	
	//constructor for character displayer
	public DisplayCharSequence(char c){
	   displayChar = c;
	   timer = new Timer();
	   timer.schedule(this, 0);
	}
	
	//display the occurrences of the character
	public void run() {
	    for (int i = 0; i < 5; ++i) {
	        System.out.print(displayChar + " ");
	    }
	    timer.cancel();
	}
	
	//main
	public static void main (String[] args) {
	   new DisplayCharSequence('M');
	   new DisplayCharSequence('A');
	   new DisplayCharSequence('R');
	   new DisplayCharSequence('K');
	}
}
