package src;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {
        yardPathLoader yardHandler = new yardPathLoader();
        trainLoader fleetHandler = new trainLoader();

        // Get our switches
        List<switchSimulator> switches = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            switches.add(new switchSimulator(i));
        }
        // Read the yard configuration file and fleet schedule file
        yardHandler.loadYardConfig("theYardFile.csv");
        List<Train> trainList = fleetHandler.createTrains("theFleetFile.csv", yardHandler, switches);

        // Log the sequence of when the trains dispatch - ZERO FOR PERM HOLD
        List<Train> dispatchedTrains = Collections.synchronizedList(new ArrayList<>());
        Map<Integer, Integer> dispatchSequenceMap = new HashMap<>();

        System.out.println("\n\n\n$$$ TRAIN MOVEMENT SIMULATION BEGINS ................ $$$\n\n\n");

        // Init executor: our max threads is 30
        ExecutorService executor = Executors.newFixedThreadPool(30); // O

        // Launch train threads
        for (Train train : trainList) {
            train.setDispatchTracker(dispatchedTrains, dispatchSequenceMap);
            executor.execute(train);
        }

        // Shutdown executor, let other trains run
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n\n$$$ SIMULATION ENDS $$$\n\n\n");
        printTrainSummary(trainList, dispatchSequenceMap);
    }

    // Print method: Prints Final results
    private static void printTrainSummary(List<Train> trainList, Map<Integer, Integer> dispatchSequenceMap) {

        // Sorting train list by train numer (lowest to highest)
        trainList.sort(Comparator.comparingInt(Train::getTrainID));

        for (Train train : trainList) {
            int dispatchSeq = dispatchSequenceMap.getOrDefault(train.getTrainID(), 0);
            List<Integer> switches = train.getNeededSwitches();

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
                    train.getTrainID(), train.getInboundTrack(), train.getOutboundTrack(), switch1, switch2, switch3, train.isDispatched() ? "False" : "True", train.isDispatched() ? "True" : "False",
                    dispatchSeq);
        }
    }
}
