package src;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class trackSwitches {
    private int switchID;
    private final ReentrantLock lock = new ReentrantLock();

    public trackSwitches(int switchID) {
        this.switchID = switchID;
    }

    public boolean lockSwitch(int trainID, int switchPosition, List<Integer> requiredSwitches) {
        boolean acquired = lock.tryLock();
        String positionText = (switchPosition == 0) ? "first" : (switchPosition == 1) ? "second" : "third";

        if (acquired) {
            System.out.println(
                    "Train " + trainID + ": HOLDS LOCK on " + positionText + " required switch: Switch " + switchID);
        } else {
            if (switchPosition == 0) {
                System.out
                        .println("\nTrain " + trainID + ": UNABLE TO LOCK " + positionText + " required switch: Switch "
                                + switchID + ". Train will wait...");
            } else if (switchPosition == 1) {
                int firstSwitchID = requiredSwitches.get(0); // Get the first switch
                System.out
                        .println("\nTrain " + trainID + ": UNABLE TO LOCK " + positionText + " required switch: Switch "
                                + switchID + ". Train will wait...");
                System.out.println(
                        "Train " + trainID + ": Releasing lock on first required switch: Switch " + firstSwitchID
                                + ". Train will wait...");
            } else if (switchPosition == 2) {
                int firstSwitchID = requiredSwitches.get(0); // Get the first switch
                int secondSwitchID = requiredSwitches.get(1); // Get the second switch
                System.out
                        .println("\nTrain " + trainID + ": UNABLE TO LOCK " + positionText + " required switch: Switch "
                                + switchID + ". Train will wait...");
                System.out.println("Train " + trainID
                        + ": Releasing locks on first and second required switches: Switch " + firstSwitchID
                        + " and Switch " + secondSwitchID + ". Train will wait...");
            }
        }
        return acquired;
    }

    public void unlockSwitch(int trainID) {
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
            System.out.println("Train " + trainID + ": Unlocks Switch " + switchID);
        }
    }
}