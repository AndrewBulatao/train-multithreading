package src;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static void main(String[] args) {
        PathKeyFileHandler yardHandler = new PathKeyFileHandler();
        TrainPathFileHandler fleetHandler = new TrainPathFileHandler();

        yardHandler.loadYardConfig("theYardFile.csv");

        List<Switch> switches = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            switches.add(new Switch(i));
        }

        List<Train> trainList = fleetHandler.loadFleetSchedule("theFleetFile.csv", yardHandler, switches);

        ExecutorService executor = Executors.newFixedThreadPool(30);

        // Track train dispatch order
        List<Train> dispatchedTrains = Collections.synchronizedList(new ArrayList<>());
        Map<Integer, Integer> dispatchSequenceMap = new HashMap<>();

        System.out.println("\n\n\n$$$ TRAIN MOVEMENT SIMULATION BEGINS ................ $$$\n\n\n");

        // Launch train threads
        for (Train train : trainList) {
            train.setDispatchTracker(dispatchedTrains, dispatchSequenceMap);
            executor.execute(train);
        }

        // Shutdown executor and wait for all trains to finish
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n\n$$$ SIMULATION ENDS $$$\n\n\n");
        printTrainSummary(trainList, dispatchSequenceMap);
    }

    // Function to print all train details in order
    private static void printTrainSummary(List<Train> trainList, Map<Integer, Integer> dispatchSequenceMap) {

        trainList.sort(Comparator.comparingInt(Train::getTrainID));

        for (Train train : trainList) {
            int dispatchSeq = dispatchSequenceMap.getOrDefault(train.getTrainID(), 0);
            List<Integer> switches = train.getRequiredSwitches();

            int switch1 = switches.size() > 0 ? switches.get(0) : 0;
            int switch2 = switches.size() > 1 ? switches.get(1) : 0;
            int switch3 = switches.size() > 2 ? switches.get(2) : 0;
            System.out.println("\n\nTrain Number " + train.getTrainID() + " assinged.");
            System.out.println(
                    "Train Number       Inbound Track       Outbound Track       Switch 1    Switch 2     Switch 3       Hold           Dispatched      Dispatch Sequence");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf(
                    "%d                      %d                   %d                  %d          %d          %d             %s          %s                 %d\n",
                    train.getTrainID(),
                    train.getInboundTrack(),
                    train.getOutboundTrack(),
                    switch1,
                    switch2,
                    switch3,
                    train.isDispatched() ? "False" : "True",
                    train.isDispatched() ? "True" : "False",
                    dispatchSeq);
        }
    }
}
