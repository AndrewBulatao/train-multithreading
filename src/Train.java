package src;

import java.util.List;
import java.util.concurrent.locks.Lock;

public class Train {
    // Train object
    private int trainNumber;
    private int inboundTrack;
    private int outboundTrack;

    private List<Lock> requiredSwitches;

    // Create object
    public Train(int trainNumber, int inboundTrack, int outboundTrack, List<Lock> requiredSwitches){
        this.trainNumber = trainNumber;
        this.inboundTrack = inboundTrack;
        this.outboundTrack = outboundTrack;
        this.requiredSwitches = requiredSwitches;
    }


    // Create get functions
    public int getTrainNumber(){
        return trainNumber;
    }

    public int getInBoundTrack(){
        return inboundTrack;
    }

    public int getOutBoundTrack(){
        return outboundTrack;
    }

    public List<Lock> getRequiredSwitches(){
        return requiredSwitches;
    }

    

}

