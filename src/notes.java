package src;

public class notes { // Time: 29:25
    /*
     *  - A train needs all three of its switches to be free
     *  - Need to figure out how to fix deadlock/ starvation
     *      - Set a protocol where if you want locks, you need to set it in a specific order
     *      - Need to check each switch individually, and see if its free. If so, wait on that switch
     *          -   if not ready, release previously checked switches and then wait
     *  
     * - If there are no possible way for a train to get to a track, make it on permanent hold
     *      - Example: train on track 5 trying to leave track 6
     * 
     * 
     * 
     * Output Specifications:
     *  - Everytime a train holds a lock on a switch, print it
     *  - Unable to use lock = someone is using it. Release previously locked switches and then wait (Sleeps)
     *  - If train gets all locks, simulate the time it takes for the train to pass from side A to side B
     *      - Then release all locks
     *      - Also means the run method on thread is terminated
     * 
     */
}
