package com.cw1;

public class Room {
    private int roomID;
    private String roomName;
    private Object[] iObjects;
    private String[] objectNames;

    public Room(int roomID, String roomName, Object[] iObjects) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.iObjects = iObjects;
        this.objectNames = new String[this.iObjects.length];
        for (int i = 0; i < this.iObjects.length; i++) {
            this.objectNames[i] = this.iObjects[i].getObjectName();
        }
    }

    public int getRoomID(){
        return this.roomID;
    }

    public String getRoomName(){
        return this.roomName;
    }

    public Object[] getiObjects(){
        return this.iObjects;
    }

    public String[] getObjectNames(){
        return this.objectNames;
    }
}
