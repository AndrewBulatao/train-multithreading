/*
 * Name: Andrew Bulatao
 * Course: CNT 4714 Spring 2025
 * Assignment TItle: Project 2 - Mult-threaded prgramming in java
 * Date: February 16
 */
package src;

import java.io.*;
import java.util.*;

public class trainLoader {
    private List<Train> trainList = new ArrayList<>();

    public List<Train> createTrains(String filePath, yardPathLoader yardHandler, List<switchSimulator> switches) {
        // Read in fleetFile
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
               
                int trainID = Integer.parseInt(parts[0]);
                int inboundTrack = Integer.parseInt(parts[1]);
                int outboundTrack = Integer.parseInt(parts[2]);

                // Get switches
                List<Integer> requiredSwitches = yardHandler.getSwitchSequence(inboundTrack, outboundTrack);

                // Insert new train
                trainList.add(new Train(trainID, inboundTrack, outboundTrack, requiredSwitches, switches));
            }
        } catch (IOException e) {
            System.err.println("Error reading fleet schedule: " + e.getMessage());
        }

        // Return the list of train objects
        return trainList;
    }

    // Our get function
    public List<Train> getTrainList() {
        return trainList;
    }
}