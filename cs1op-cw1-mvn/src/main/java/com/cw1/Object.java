package com.cw1;

public abstract class Object {
    private int globalObjectID;
    private int localObjectID;
    
    public int getGlobalObjectID() {
        return this.globalObjectID;
    }

    public int getLocalObjectID() {
        return this.localObjectID;
    }

    public void setLocalObjectID(int localObjectID) {
        this.localObjectID = localObjectID;
    }

    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        return;
    }

    public abstract String getObjectName();

    public abstract int getCurrentPlayer();

    public abstract void Up();

    public abstract void Left();

    public abstract void Down();

    public abstract void Right();

    public abstract void LInteract();

    public abstract void RInteract();
}

 