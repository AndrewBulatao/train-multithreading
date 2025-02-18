package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class trainHandler{

    private HashMap<Integer, List<Integer>> trainMap = new HashMap<>();

    public boolean trainChecker(int inbound, int outbound){
        // TODO: CHECK BY USING THE HASH FUNCTION

        return true;
    }
    // inserting function
    public void insertTrain(String fleetString, List<String> yardData){
        // Make the string into int. 
        String[] fleet = fleetString.split(",");

        int trainNum = Integer.parseInt(fleet[0]);
        int inboundTrack = Integer.parseInt(fleet[1]);
        int outboundTrack = Integer.parseInt(fleet[2]);
        boolean matchFound = false;
        
        // Init switches arrayList
        List<Integer> switches = null;
        // Search for the match
        for (String curYard : yardData){
            String[] yard = curYard.split("");
            int yardInbound = Integer.parseInt(yard[0]);
            int yardOutbound = Integer.parseInt(yard[4]);

            if (inboundTrack == yardInbound && outboundTrack == yardOutbound){
                // Insert the switches in the yardFile into a hashmap with the train number
                switches = new ArrayList<>();
                switches.add(Integer.valueOf(yard[1]));
                switches.add(Integer.valueOf(yard[2]));
                switches.add(Integer.valueOf(yard[3]));
                matchFound = true;
                
                break; // Stop looking
            }
        }
        
        // We found no match
        if (!matchFound){
            switches.add(0);
            switches.add(0);
            switches.add(0);

        }
        trainMap.put(trainNum, switches);

    }// insertTrain end

    // Prints out hashmap
    public HashMap<Integer, List<Integer>> getTrainMap() {
        return trainMap;
    }


}
