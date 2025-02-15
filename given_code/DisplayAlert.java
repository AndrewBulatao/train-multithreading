package given_code;

//Displays an alert at a specific time - threaded execution
import java.util.*;
import javax.swing.*;

public class DisplayAlert extends TimerTask {
	//instance variables
 private String message;
	private java.util.Timer timer;
	//constructor
	public DisplayAlert(String s, Date t){
	   message = s + ": " + t;
		timer = new java.util.Timer();
		timer.schedule(this, t);
	}
	//execute thread
	public void run() {
	   //application specific task
	   JOptionPane.showMessageDialog(null, message, "Mark's Message Service", JOptionPane.INFORMATION_MESSAGE);  
	   timer.cancel();  //kill thread
	}
	public static void main(String[] args) {
	   Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 17);
		c.set(Calendar.MINUTE, 00);
		c.set(Calendar.SECOND, 0);
		Date zoomMeetingTime = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 17);
		c.set(Calendar.MINUTE, 10);
		c.set(Calendar.SECOND, 0);
		Date heidiMeetingTime = c.getTime();
		new DisplayAlert("Zoom Meeting", zoomMeetingTime);
		new DisplayAlert("Meet Heidi", heidiMeetingTime);
	}
}
