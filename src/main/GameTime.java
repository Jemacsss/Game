package main;

public class GameTime {

    private long startTime;  // Store the time when the game starts
    private long elapsedTime; // Track elapsed time
    private boolean isRunning;

    public GameTime() {
        startTime = 0;
        elapsedTime = 0;
        isRunning = false;
    }

    // Start the timer
    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    // Stop the timer
    public void stop() {
        if (isRunning) {
            elapsedTime += System.currentTimeMillis() - startTime;
            isRunning = false;
        }
    }

    // Reset the timer
    public void reset() {
        startTime = System.currentTimeMillis();
        elapsedTime = 0;
        isRunning = true;
    }

    // Get the elapsed time in seconds
    public String getElapsedTime() {
        if (isRunning) {
            long currentTime = System.currentTimeMillis();
            return String.format("%d", (currentTime - startTime + elapsedTime) / 1000);
        } else {
            return String.format("%d", elapsedTime / 1000);
        }
    }

    // Update the timer (to be called every frame)
    public void update() {
        if (isRunning) {
            // Calculate elapsed time if needed (optional step here, as getElapsedTime() does this)
        }
    }
}
