package com.cw1;

/**
 * Abstract base class for all interactive objects in the game.
 * Defines common properties and required methods for game objects.
 */
public abstract class Object {
    // Unique ID for the object within its room/location
    private int localObjectID;
    // State variable for the object (meaning depends on subclass)
    private int state;
    
    /**
     * Returns the current state of the object.
     */
    public int getState() {
        return this.state;
    }

    /**
     * Returns the local object ID.
     */
    public int getLocalObjectID() {
        return this.localObjectID;
    }

    /**
     * Sets the local object ID.
     * @param localObjectID The ID to assign to this object.
     */
    public void setLocalObjectID(int localObjectID) {
        this.localObjectID = localObjectID;
    }

    /**
     * Handles interaction with the object.
     * Default implementation does nothing; should be overridden by subclasses.
     * @param GUI The graphical user interface.
     * @param activePlayer The player interacting with the object.
     */
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        return;
    }

    /**
     * Returns the name of the object.
     */
    public abstract String getObjectName();

    /**
     * Returns the current player interacting with the object.
     */
    public abstract int getCurrentPlayer();

    /**
     * Handles the "Up" action for the object.
     */
    public abstract void Up();

    /**
     * Handles the "Left" action for the object.
     */
    public abstract void Left();

    /**
     * Handles the "Down" action for the object.
     */
    public abstract void Down();

    /**
     * Handles the "Right" action for the object.
     */
    public abstract void Right();

    /**
     * Handles the "LInteract" action for the object.
     */
    public abstract void LInteract();

    /**
     * Handles the "RInteract" action for the object.
     */
    public abstract void RInteract();
}

