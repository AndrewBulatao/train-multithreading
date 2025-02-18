package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class fileRead {

    public static trainHandler read(){
        // Read both files. The trainHandler 'insertTrain should handle both files'
    trainHandler handler = new trainHandler();

    //Reading
    List<String> fleetData = readFile("theFleetFile.csv");
    List<String> yardData = readFile("theYardFile.csv");
    
        // Create hashmap
        for(String curFleetLine : fleetData){
            handler.insertTrain(curFleetLine, yardData);
        }

        // Debugging prints out hashmap
        HashMap<Integer, List<Integer>> trainMap = handler.getTrainMap();
        System.out.println("OUR TRAIN MAP: \n" + trainMap);
    
        // Return hashmap
        return handler;
    }
    
    // Helper to read in the file
    private static List<String> readFile(String filePath){
        List<String> fileData = new ArrayList<>();
        try{
            Scanner s = new Scanner(new File(filePath));
            while(s.hasNextLine()){
                fileData.add(s.nextLine());
            }
            s.close();
        } catch(FileNotFoundException e){
            System.out.println("COULD NOT FIND FILE!\n");
            e.printStackTrace();
        }

        return fileData;
    }

}
