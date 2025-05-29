package com.cw1.Objects;

import java.util.Random;

import com.cw1.Game;
import com.cw1.GraphicalUserInterface;

/**
 * Represents a window object in the game.
 * Handles player interaction and displays random dialogue based on the window's state.
 */
public class Window extends Object {
    // globalObjectID = 3

    // Tracks which player is currently interacting with the window
    private int currentPlayer = 0;

    // Dialogue to display when interacting with the window
    private String dialouge = "";

    /**
     * Returns the current player interacting with the window.
     */
    @Override
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Returns the name of the object.
     */
    @Override
    public String getObjectName() {
        return "Window";
    }

    /**
     * Handles interaction with the window.
     * Displays random dialogue for the active player.
     */
    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.setRandomDialouge();
        this.currentPlayer = activePlayer;
        if (activePlayer == 1) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP1(this.getObjectName(), this.dialouge, 2, new String[0], -1);
            } else {
                GUI.writeDialougeCLIP1(this.getObjectName(), this.dialouge, 1, new String[0]);
            }
        }
        if (activePlayer == 2) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP2(this.getObjectName(), this.dialouge, 2, new String[0], -1);
            } else {
                GUI.writeDialougeCLIP2(this.getObjectName(), this.dialouge, 1, new String[0]);
            }
        }
        return;
    }

    /**
     * Sets a random dialogue for the window, simulating different outside views.
     */
    public void setRandomDialouge() {
        Random r = new Random();
        int rSelect = r.nextInt(3);
        switch(rSelect) {
            case 0:
                this.dialouge = "Its pitch-black,\nalmost like you are deep underwater.";
                break;
            case 1:
                this.dialouge = "It looks like its raining outside,\nbut like\n..more.";
                break;
            case 2:
                this.dialouge = "You see yourself in the dark reflection,\npulling a stupid face.";
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
