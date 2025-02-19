package src;

import java.io.*;
import java.util.*;

public class PathKeyFileHandler {
    // Maps (inbound track, outbound track) -> required switches
    private Map<String, List<Integer>> trackSwitchMap = new HashMap<>();

    public void loadYardConfig(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 5)
                    continue; // Ensure valid format

                int inboundTrack = Integer.parseInt(parts[0]);
                List<Integer> switchSequence = new ArrayList<>();
                for (int i = 1; i <= 3; i++) {
                    switchSequence.add(Integer.parseInt(parts[i]));
                }
                int outboundTrack = Integer.parseInt(parts[4]);

                // Store using key: "inbound-outbound"
                String key = inboundTrack + "-" + outboundTrack;

                trackSwitchMap.put(key, switchSequence);
            }
        } catch (IOException e) {
            System.err.println("Error reading yard configuration: " + e.getMessage());
        }
    }

    // Returns switch sequence given inbound and outbound tracks
    public List<Integer> getSwitchSequence(int inboundTrack, int outboundTrack) {
        String key = inboundTrack + "-" + outboundTrack;
        return trackSwitchMap.getOrDefault(key, new ArrayList<>()); // Empty list if not found CHECK EMPTY FOR TRAIN 14
    }

    // public void printYardConfig() {
    // for (Map.Entry<String, List<Integer>> entry : trackSwitchMap.entrySet()) {
    // System.out.println("Path " + entry.getKey() + " -> Switches " +
    // entry.getValue());
    // }
    // }
}
