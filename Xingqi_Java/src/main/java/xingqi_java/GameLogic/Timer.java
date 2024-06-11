/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xingqi_java.GameLogic;

/**
 *
 * @author TRUNG
 */
public class Timer {

    private long startTime;
    private long stopTime;
    private boolean stillRunning;

    
    public Timer() {
        this.startTime = 0;
        this.stopTime = 0;
        this.stillRunning = false;
    }

    
    public void start() {
        startTime = System.nanoTime();   
        stillRunning = true;
    }

   
    public void stop() {
        stopTime = System.nanoTime();
        stillRunning = false;
    }

   
    public boolean isStillRunning() {
        return this.stillRunning;
    }

    
    public long getTime() {

        if (stillRunning) {
            return (System.nanoTime() - startTime) / 1000000;  //convert to milliseconds
        } else {
            return (stopTime - startTime) / 1000000;
        }
    }


}
