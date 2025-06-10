package com.cw1;

/**
 * Log class that implements the Observer design pattern.
 * Receives updates and logs messages.
 */
public class Log{

    /**
     * Called when the subject notifies this observer.
     * Logs the received message.
     * @param message The update message.
     */
    public void addLogEntry(String message, int player) {
        // Print different log messages based on the action keyword in the message
        if (message.startsWith("INTERACT")) {
            System.out.println("Log: Player " + player + " interacted with object: " + message.substring(8));
        } else if (message.startsWith("QUEST")) {
            System.out.println("Log: Player " + player + " accepted quest: " + message.substring(5));
        } else if (message.startsWith("ITEM")) {
            System.out.println("Log: Player " + player + " picked up item: " + message.substring(4));
        } else if (message.startsWith("MOVE")) {
            System.out.println("Log: Player " + player + " moved players to location: " + message.substring(4));
        } else {
            System.out.println("Log: Player " + player + ": " + message);
        }
    }
}
