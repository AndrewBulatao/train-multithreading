# Train-Multithreading

## Project Overview
**train-multithreading** is a simulation project that models train dispatching and scheduling using multithreading concepts. Each train is represented as a thread, allowing the simulation of concurrent train movements and interactions with shared resources, such as tracks and stations. The simulation demonstrates safe thread management, synchronization, and resource allocation in a multi-threaded environment.

## Features
- Simulates multiple trains running concurrently.  
- Uses multithreading to model real-world train dispatching.  
- Demonstrates thread synchronization and safe access to shared resources.  
- Logs train movements and dispatch events for easy tracking.  

## Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/train-multithreading.git
    ```
2. Navigate to the project directory:
    ```bash
    cd train-multithreading
    ```
3. Compile the Java files (if using Java):
    ```bash
    javac *.java
    ```

## Usage
Run the simulation:
```bash
java Main
```

The program will simulate trains being dispatched, moving along tracks, arriving at stations, or waiting if a track is occupied. Each train runs as an independent thread, demonstrating concurrency and synchronization.

## How Dispatch Works

- Each train is a separate thread.
- Tracks are shared resources; only one train can occupy a track at a time.
- If a track is occupied, trains will wait until the track becomes free.
- Dispatching occurs sequentially, but trains move concurrently, showing real-time scheduling.

## Example Output

From `simulated_run.txt`:
Train 1 dispatched to Track A
Train 2 dispatched to Track B
Train 1 arrived at Station X
Train 3 waiting for Track A
Train 2 arrived at Station Y
Train 3 dispatched to Track A
...


This shows the real-time dispatching, arrivals, and waiting states of trains during the simulation.

## Dependencies

- Java 8 or higher
- No external libraries required



