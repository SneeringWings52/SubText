package com.cw1;

import java.util.Scanner;
import java.nio.file.*;

/**
 * Represents a location in the game.
 * Loads location data from a file, including rooms, room connections, and interactive objects.
 */
public class Location {
    // Unique ID for the location
    private int locID;
    // Name of the location
    private String locName;
    // Array of rooms in this location
    private Room[] rooms;
    // Matrix representing connections between rooms
    private int[][] roomMatrix;

    /**
     * Constructs a Location object and loads its data from the specified file.
     * @param locID The unique ID for this location.
     * @param filename The filename containing location data.
     */
    public Location(int locID, String filename) {
        this.locID = locID;
        this.setup(filename);
    }

    /**
     * Loads location data from a file.
     * Reads the location name, rooms, interactive objects, and room matrix.
     * @param filename The filename containing location data.
     */
    private void setup(String filename) {
        try {
            Scanner locFile = new Scanner(Path.of("src\\main\\resources\\com\\cw1\\Locations\\"+ filename));
            this.locName = locFile.nextLine().replace(":"," ");
            int numOfRooms = locFile.nextInt();
            int[][] roomSetup = new int[numOfRooms][0];
            String[] roomNames = new String[numOfRooms];
            int numOfiObjects;

            // Read room and interactive object data
            for (int i = 0; i < numOfRooms; i++) {
                numOfiObjects = locFile.nextInt();
                locFile.nextLine();
                String name = locFile.nextLine().replace(":", " ");
                roomNames[i] = name;
                roomSetup[i] = new int[numOfiObjects];
                for (int j = 0; j < numOfiObjects; j++) {
                    roomSetup[i][j] = locFile.nextInt();
                }
            }
            // Create Room objects using the RoomFactory
            this.rooms = RoomFactory.createRoomList(roomSetup, roomNames);

            // Read the room connection matrix
            this.roomMatrix = new int[numOfRooms][numOfRooms];
            for (int i = 0; i < numOfRooms; i++) {
                for (int j = 0; j < numOfRooms; j++) {
                    this.roomMatrix[i][j] = locFile.nextInt();
                }
            }
            locFile.close();
        }
        // Handle any exceptions that occur during file reading
        catch (Exception IOException){
            System.out.println("ExError\n\n");
        }
    }

    /**
     * Returns the unique ID of the location.
     */
    public int getLocID() {
        return this.locID;
    }

    /**
     * Returns the name of the location.
     */
    public String getLocationName() {
        return this.locName;
    }

    /**
     * Returns the array of rooms in this location.
     */
    public Room[] getRooms() {
        return this.rooms;
    }

    /**
     * Returns the room connection matrix for this location.
     */
    public int[][] getRoomMatrix() {
        return this.roomMatrix;
    }
}