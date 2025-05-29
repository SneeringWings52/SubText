package com.cw1.Objects;

import java.util.*;

import com.cw1.Game;
import com.cw1.GraphicalUserInterface;
import com.cw1.Location;

/**
 * Represents a command console object in the game.
 * Allows the player to select and travel to different locations.
 * Handles player interaction, location selection, and GUI updates.
 */
public class Command extends Object {
    // globalObjectID = 2

    // Tracks which player is currently interacting with the command console
    private int currentPlayer = 0;

    // Tracks the currently selected location choice
    private int currentChoicePlace = 0;

    // Array of location choice names for display
    private String[] locationChoices;

    // List of available locations the player can travel to
    List<Location> locationSwims;

    /**
     * Returns the current player interacting with the command console.
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
        return "Command";
    }

    /**
     * Handles interaction with the command console.
     * Displays available locations and allows the player to select one.
     */
    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentChoicePlace = 0;
        currentPlayer = activePlayer;
        int locationID = Game.getInstance().getCurrentPlayerLocation().getLocID();
        int[] localLocationMatrix = Game.getInstance().getLocationMatrix()[locationID];
        this.locationSwims = new ArrayList<>();
        // Build list of available locations based on the location matrix
        for (int i = 0; i < localLocationMatrix.length; i++) {
            if (localLocationMatrix[i] == 1) {
                this.locationSwims.add(Game.getInstance().getAvailableLocations()[i]);
            }
        }
        // Prepare location choices for display
        this.locationChoices = new String[this.locationSwims.size()];
        for (int i = 0; i < this.locationChoices.length; i++) {
            this.locationChoices[i] = this.locationSwims.get(i).getLocationName();
        }
        // Highlight the first choice
        this.locationChoices[0] = "->  " + this.locationChoices[0] + "  <-";
        // Display dialogue for player 1
        if (activePlayer == 1) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP1(this.getObjectName(), "Select Location and\n-W- to Confirm", 2, this.locationChoices, 0);
            } else {
                GUI.writeDialougeCLIP1(this.getObjectName(), "Select Location and -W- to Confirm", 1, this.locationChoices);
            }
        }
        // Display dialogue for player 2
        if (activePlayer == 2) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP2(this.getObjectName(), "Select Location and\n-I- to Confirm", 2, this.locationChoices, 0);
            } else {
                GUI.writeDialougeCLIP2(this.getObjectName(), "Select Location and -I- to Confirm", 1, this.locationChoices);
            }
        }
        return;
    }

    /**
     * Handles the "Up" action (player confirms location selection).
     * Moves the player to the selected location.
     */
    @Override
    public void Up() {
        int tempPlayer = this.currentPlayer;
        this.currentPlayer = 0;
        Game.getInstance().updateLocation(this.locationSwims.get(this.currentChoicePlace), tempPlayer);
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
     * Handles the "LInteract" action.
     * Cycles through available location choices for the player.
     * Updates the GUI to reflect the new selection.
     */
    @Override
    public void LInteract() {
        // Remove highlight from current choice
        this.locationChoices[this.currentChoicePlace] = this.locationSwims.get(this.currentChoicePlace).getLocationName();
        // Move to next choice, wrap around if at end
        if (this.currentChoicePlace + 1 >= this.locationChoices.length) {
            this.currentChoicePlace = 0;
        } else {
            this.currentChoicePlace += 1;
        }
        System.out.println(this.currentChoicePlace);
        // Highlight the new choice
        this.locationChoices[this.currentChoicePlace] = "->  " + this.locationChoices[this.currentChoicePlace] + "  <-";
        // Update dialogue for player 1
        if (this.currentPlayer == 1) {
            if (Game.getInstance().getIsCLI() == false) {
                Game.getInstance().getGUI().writeDialougeP1(this.getObjectName(), "Select Location, use\n-W- to Confirm", 2, this.locationChoices, this.currentChoicePlace);
            } else {
                Game.getInstance().getGUI().writeDialougeCLIP1(this.getObjectName(), "Select Location, use -W- to Confirm", 1, this.locationChoices);
            }
        }
        // Update dialogue for player 2
        if (this.currentPlayer == 2) {
            if (Game.getInstance().getIsCLI() == false) {
                Game.getInstance().getGUI().writeDialougeP2(this.getObjectName(), "Select Location, use\n-I- to Confirm", 2, this.locationChoices, this.currentChoicePlace);
            } else {
                Game.getInstance().getGUI().writeDialougeCLIP2(this.getObjectName(), "Select Location, use -I- to Confirm", 1, this.locationChoices);
            }
        }
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