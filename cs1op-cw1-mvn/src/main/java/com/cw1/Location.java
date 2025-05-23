package com.cw1;

import java.util.Scanner;
import java.nio.file.*;

public class Location {
    private int locID;
    private String locName;
    private Room[] rooms;
    private int[][] roomMatrix;

    public Location(int locID, String filename) {
        this.locID = locID;
        this.setup(filename);
    }

    private void setup(String filename) {
        try {
            Scanner locFile = new Scanner(Path.of("src\\main\\java\\com\\cw1\\Locations\\"+ filename));
            this.locName = locFile.nextLine().replace(":"," ");
            int numOfRooms = locFile.nextInt();
            int[][] roomSetup = new int[numOfRooms][0];
            String[] roomNames = new String[numOfRooms];
            int numOfiObjects;

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
            this.rooms = RoomFactory.createRoomList(roomSetup, roomNames);
            this.roomMatrix = new int[numOfRooms][numOfRooms];
            for (int i = 0; i < numOfRooms; i++) {
                for (int j = 0; j < numOfRooms; j++) {
                    this.roomMatrix[i][j] = locFile.nextInt();
                }
            }
            locFile.close();
        }

        catch (Exception IOException){
            System.out.println("ExError\n\n");
        }
    }

    public int getLocID() {
        return this.locID;
    }

    public String getLocationName() {
        return this.locName;
    }

    public Room[] getRooms() {
        return this.rooms;
    }

    public int[][] getRoomMatrix() {
        return this.roomMatrix;
    }
}