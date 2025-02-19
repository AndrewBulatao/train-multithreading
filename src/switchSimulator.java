/*
 * Name: Andrew Bulatao
 * Course: CNT 4714 Spring 2025
 * Assignment TItle: Project 2 - Mult-threaded prgramming in java
 * Date: February 16
 */
package src;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class switchSimulator {
    private int switchID;
    private final ReentrantLock lock = new ReentrantLock();

    public switchSimulator(int switchID) {
        this.switchID = switchID;
    }

    public boolean lockSwitch(int trainID, int switchPosition, List<Integer> requiredSwitches) {
        boolean acquired = lock.tryLock();
        String positionText;
        if (switchPosition == 0) {
            positionText = "first";
        } else if (switchPosition == 1) {
            positionText = "second";
        } else {
            positionText = "third";
        }
        // Attempt to acquire switch
        if (acquired) { // If we get all the switches
            System.out.println(
                    "Train " + trainID + ": HOLDS LOCK on " + positionText + " required switch: Switch " + switchID);
        } else {
            if (switchPosition == 0) { // if cant even get the first switch
                System.out.println("\nTrain " + trainID + ": UNABLE TO LOCK " + positionText + 
                        " required switch: Switch " + switchID + ". Train will wait...");
            } else if (switchPosition == 1) { // If at first switch but cant get to second
                int firstSwitchID = requiredSwitches.get(0); 
                System.out.println("\nTrain " + trainID + ": UNABLE TO LOCK " + positionText + 
                        " required switch: Switch " + switchID + ". Train will wait...");
                System.out.println(
                        "Train " + trainID + ": Releasing lock on first required switch: Switch " + 
                        firstSwitchID + ". Train will wait...");
            } else if (switchPosition == 2) { // If at second switch but can get to third
                int firstSwitchID = requiredSwitches.get(0); 
                int secondSwitchID = requiredSwitches.get(1); 
                System.out.println("\nTrain " + trainID + ": UNABLE TO LOCK " + positionText 
                         + " required switch: Switch " + switchID + ". Train will wait...");
                System.out.println("Train " + trainID + 
                        ": Releasing locks on first and second required switches: Switch " + 
                        firstSwitchID + " and Switch " + secondSwitchID + ". Train will wait...");
            }
        }
        return acquired;
    }

    // Unlock switches when train is done using it
    public void unlockSwitch(int trainID) {
        if (lock.isHeldByCurrentThread()) { // If its held by the train, release
            lock.unlock();
            System.out.println("Train " + trainID + ": Unlocks Switch " + switchID);
        }
    }
}