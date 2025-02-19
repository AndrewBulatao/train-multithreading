package src;

import java.io.*;
import java.util.*;

public class yardPathLoader {
    
    // Track path to required switch sequence
    private Map<String, List<Integer>> trackSwitchMap = new HashMap<>();

    // Reading in the Yard file
    public void loadYardConfig(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                int inboundTrack = Integer.parseInt(parts[0]);
                List<Integer> switchSequence = new ArrayList<>();
                for (int i = 1; i <= 3; i++) {
                    switchSequence.add(Integer.parseInt(parts[i]));
                }
                int outboundTrack = Integer.parseInt(parts[4]);

                // Create a key that follows inbound - outbound (ex: 1-5)
                String key = inboundTrack + "-" + outboundTrack;

                trackSwitchMap.put(key, switchSequence);
            }
        } catch (IOException e) {
            System.err.println("Error reading yard configuration: " + e.getMessage());
        }
    }

    // Our get function
    public List<Integer> getSwitchSequence(int inboundTrack, int outboundTrack) {
        String key = inboundTrack + "-" + outboundTrack;
        // When returning a train that doesnt exist (ex:14) we should return a list 0,0,0
        return trackSwitchMap.getOrDefault(key, new ArrayList<>()); 
    }
}
