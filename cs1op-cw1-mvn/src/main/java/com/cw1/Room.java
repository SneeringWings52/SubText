package com.cw1;

/**
 * Represents a room within a location in the game.
 * Each room contains interactive objects and a name.
 */
public class Room {
    // Unique ID for the room within its location
    private int roomID;
    // Name of the room
    private String roomName;
    // Array of interactive objects in this room
    private Object[] iObjects;
    // Array of object names for display or selection
    private String[] objectNames;

    /**
     * Constructs a Room with the given ID, name, and interactive objects.
     * @param roomID The unique ID for this room.
     * @param roomName The name of the room.
     * @param iObjects The array of interactive objects in this room.
     */
    public Room(int roomID, String roomName, Object[] iObjects) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.iObjects = iObjects;
        this.objectNames = new String[this.iObjects.length];
        for (int i = 0; i < this.iObjects.length; i++) {
            this.objectNames[i] = this.iObjects[i].getObjectName();
        }
    }

    /**
     * Returns the unique ID of the room.
     */
    public int getRoomID(){
        return this.roomID;
    }

    /**
     * Returns the name of the room.
     */
    public String getRoomName(){
        return this.roomName;
    }

    /**
     * Returns the array of interactive objects in this room.
     */
    public Object[] getiObjects(){
        return this.iObjects;
    }

    /**
     * Returns the array of object names in this room.
     */
    public String[] getObjectNames(){
        return this.objectNames;
    }
}
