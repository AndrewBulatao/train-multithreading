package src;

import java.util.List;
import java.util.Map;

public class Train implements Runnable {
    private int trainID;
    private int inboundTrack;
    private int outboundTrack;
    private List<Switch> switches;
    private boolean dispatched = false;
    private List<Integer> requiredSwitches;
    private List<Train> dispatchedTrains;
    private Map<Integer, Integer> dispatchSequenceMap;

    public Train(int trainID, int inboundTrack, int outboundTrack, List<Integer> requiredSwitches,
            List<Switch> switches) {
        this.trainID = trainID;
        this.inboundTrack = inboundTrack;
        this.outboundTrack = outboundTrack;
        this.switches = switches;
        this.requiredSwitches = requiredSwitches;
    }

    public void setDispatchTracker(List<Train> dispatchedTrains, Map<Integer, Integer> dispatchSequenceMap) {
        this.dispatchedTrains = dispatchedTrains;
        this.dispatchSequenceMap = dispatchSequenceMap;
    }

    @Override
    public void run() {
        if (requiredSwitches.isEmpty()) {
            System.out.println("*************");
            System.out.println("Train " + trainID + " is on permanent hold and cannot be dispatched.");
            System.out.println("*************");
            return;
        }

        while (true) {
            if (tryAcquireSwitches(requiredSwitches)) {
                System.out.println("Train " + trainID + ": HOLDS ALL NEEDED SWITCH LOCKS – Train movement begins.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Train " + trainID + ": Clear of yard control.");
                System.out.println("Train " + trainID + ": Releasing all switch locks.");
                releaseSwitches(requiredSwitches);
                System.out.println("Train " + trainID
                        + ": Has been dispatched and moves on down the line out of yard control into CTC.");
                System.out.println("\n\n\n                            @ @ @ TRAIN " + trainID + ": DISPATCHED @ @ @");

                dispatched = true;

                // Add to dispatch list and assign sequence number
                synchronized (dispatchedTrains) {
                    dispatchedTrains.add(this);
                    dispatchSequenceMap.put(trainID, dispatchedTrains.size());
                }
                break;
            } else {
                System.out.println("Train " + trainID
                        + ": Unable to acquire all needed switches. Releasing acquired locks and retrying...");
                releaseSwitches(requiredSwitches);
                try {
                    Thread.sleep((long) (Math.random() * 200));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Utility functions to retrieve train details
    public int getTrainID() {
        return trainID;
    }

    public int getInboundTrack() {
        return inboundTrack;
    }

    public int getOutboundTrack() {
        return outboundTrack;
    }

    public List<Integer> getRequiredSwitches() {
        return requiredSwitches;
    }

    public boolean isDispatched() {
        return dispatched;
    }

    private boolean tryAcquireSwitches(List<Integer> requiredSwitches) {
        for (int i = 0; i < requiredSwitches.size(); i++) {
            int switchID = requiredSwitches.get(i);
            Switch sw = switches.get(switchID - 1);
            if (!sw.lockSwitch(trainID, i, requiredSwitches)) {
                return false;
            }
        }
        return true;
    }

    private void releaseSwitches(List<Integer> requiredSwitches) {
        for (int switchID : requiredSwitches) {
            Switch sw = switches.get(switchID - 1);
            sw.unlockSwitch(trainID);
            System.out.println("Train " + trainID + ": Unlocks/Releases Switch " + switchID);
        }
    }
}

