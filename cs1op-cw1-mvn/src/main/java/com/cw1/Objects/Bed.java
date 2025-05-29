package com.cw1.Objects;

import com.cw1.Game;
import com.cw1.GraphicalUserInterface;

/**
 * Represents a bed object in the game.
 * Handles player interaction and displays dialogue.
 */
public class Bed extends Object {
    // globalObjectID = 5

    // Tracks which player is currently interacting with the bed
    private int currentPlayer = 0;

    // Dialogue to display when interacting with the bed
    private String dialouge = "It looks comfy,\nabout as comfy as a metal slab and cloth can.";

    /**
     * Returns the current player interacting with the bed.
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
        return "Bed";
    }

    /**
     * Handles interaction with the bed.
     * Displays appropriate dialogue for the active player.
     */
    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentPlayer = activePlayer;
        if (activePlayer == 1) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP1(this.getObjectName(), this.dialouge, 2, new String[0], -1);
            } else {
                GUI.writeDialougeCLIP1(this.getObjectName(), this.dialouge, 2, new String[0]);
            }
        }
        if (activePlayer == 2) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP2(this.getObjectName(), this.dialouge, 2, new String[0], -1);
            } else {
                GUI.writeDialougeCLIP2(this.getObjectName(), this.dialouge, 2, new String[0]);
            }
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