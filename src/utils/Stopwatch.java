package utils;

public class Stopwatch {

    private long startTime;
    private long stopTime = 0;

    public Stopwatch() {
        this.startTime = System.currentTimeMillis();
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.stopTime = System.currentTimeMillis();
        System.out.println("StopWatch: " + this.getElapsedTime() + " milliseconds.");
    }

    public long getElapsedTime() {
        return this.stopTime - this.startTime;
    }
}
