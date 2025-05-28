package com.cw1;

import java.util.Random;

/**
 * Represents a locker object in the game.
 * Handles player interaction and displays random dialogue based on locker state.
 */
public class Locker extends Object {
    // globalObjectID = 0

    // Tracks which player is currently interacting with the locker
    private int currentPlayer = 0;

    // Dialogue to display when interacting with the locker
    private String dialouge = "";

    /**
     * Returns the current player interacting with the locker.
     */
    @Override
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the name of the object.
     */
    @Override
    public String getObjectName() {
        return "Locker";
    }

    /**
     * Handles interaction with the locker.
     * Displays random dialogue for the active player.
     */
    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        if (this.dialouge.equals("")) {
            this.setRandomDialouge();
        }
        this.currentPlayer = activePlayer;
        if (activePlayer == 1) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP1(this.getObjectName(), this.dialouge, 1, new String[0], -1);
            } else {
                GUI.writeDialougeCLIP1(this.getObjectName(), this.dialouge, 1, new String[0]);
            }
        }
        if (activePlayer == 2) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP2(this.getObjectName(), this.dialouge, 1, new String[0], -1);
            } else {
                GUI.writeDialougeCLIP2(this.getObjectName(), this.dialouge, 1, new String[0]);
            }
        }
        return;
    }

    /**
     * Sets a random dialogue for the locker, simulating different locker states.
     */
    public void setRandomDialouge() {
        Random r = new Random();
        int rSelect = r.nextInt(3);
        switch(rSelect) {
            case 0:
                this.dialouge = "Its locked, you can't get in.";
                break;
            case 1:
                this.dialouge = "Its unlocked,\nrevealing an array of mismatched toolsets.";
                break;
            case 2:
                this.dialouge = "Its locked, you can't get in.";
                break;
        }
        return;
    }

    /**
     * Handles the "Up" action. No operation for this object.
     */
    @Override
    public void Up() {
        return;
    }

    /**
     * Handles the "Left" action. No operation for this object.
     */
    @Override
    public void Left() {
        return;
    }

    /**
     * Handles the "Down" action. No operation for this object.
     */
    @Override
    public void Down() {
        return;
    }

    /**
     * Handles the "Right" action. No operation for this object.
     */
    @Override
    public void Right() {
        return;
    }

    /**
     * Handles the "LInteract" action. No operation for this object.
     */
    @Override
    public void LInteract() {
        return;
    }

    /**
     * Handles the "RInteract" action.
     * Resets the current player and reloads the room for that player.
     */
    @Override
    public void RInteract() {
        int tempPlayer = this.currentPlayer;
        this.currentPlayer = 0;
        Game.getInstance().reloadRoom(tempPlayer);
        return;
    }
}

