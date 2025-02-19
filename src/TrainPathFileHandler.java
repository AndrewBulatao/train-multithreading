package src;

import java.io.*;
import java.util.*;

public class TrainPathFileHandler {
    private List<Train> trainList = new ArrayList<>();

    public List<Train> loadFleetSchedule(String filePath, PathKeyFileHandler yardHandler, List<Switch> switches) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3)
                    continue; // Ensure valid format

                int trainID = Integer.parseInt(parts[0]);
                int inboundTrack = Integer.parseInt(parts[1]);
                int outboundTrack = Integer.parseInt(parts[2]);

                // Get required switches for the train
                List<Integer> requiredSwitches = yardHandler.getSwitchSequence(inboundTrack, outboundTrack);

                // Create train object and add to list
                trainList.add(new Train(trainID, inboundTrack, outboundTrack, requiredSwitches, switches));
            }
        } catch (IOException e) {
            System.err.println("Error reading fleet schedule: " + e.getMessage());
        }

        // Return the list of trains
        return trainList;
    }

    public List<Train> getTrainList() {
        return trainList;
    }
}