package com.cw1.Objects;

import java.util.Random;

import com.cw1.Game;
import com.cw1.GraphicalUserInterface;

/**
 * Represents a generic merchant NPC in the game.
 * Handles player interaction and displays random dialogue responses.
 */
public class Merchant extends Object {
    // globalObjectID = 12

    // Tracks which player is currently interacting with the merchant
    private int currentPlayer = 0;

    // Dialogue to display when interacting with the merchant
    private String dialouge = "";

    /**
     * Returns the current player interacting with the merchant.
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
        return "Merchant";
    }

    /**
     * Handles interaction with the merchant.
     * Displays a random dialogue for the active player.
     */
    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        if (this.dialouge.equals("")) {
            this.setRandomDialouge();
        }
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
     * Sets a random dialogue for the merchant, simulating different responses.
     */
    public void setRandomDialouge() {
        Random r = new Random();
        int rSelect = r.nextInt(4);
        switch(rSelect) {
            case 0:
                this.dialouge = "I don't have anything for\n...you...";
                break;
            case 1:
                this.dialouge = "Leave! ...Now!";
                break;
            case 2:
                this.dialouge = "I have nothing for you here.\nGo somewhere else.. quick!";
                break;
            case 3:
                this.dialouge = "They left as you walked in.\nHow... strange?";
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