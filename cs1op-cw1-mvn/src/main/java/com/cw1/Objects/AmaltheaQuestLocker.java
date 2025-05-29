package com.cw1.Objects;

import com.cw1.Game;
import com.cw1.GraphicalUserInterface;

/**
 * Represents a quest locker object in the game.
 * Handles player interaction, state changes, and dialogue display.
 */
public class AmaltheaQuestLocker extends Object {
    // globalObjectID = 9

    // Tracks which player is currently interacting with the locker
    private int currentPlayer = 0;

    // State of the locker: 0 = unopened, 1 = document taken
    private int state = 0;

    // Dialogue to display when interacting with the locker
    private String dialouge = "There is an important-looking document\nresting on a shelf here.";

    /**
     * Returns the current player interacting with the locker.
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
        return "Locker";
    }

    /**
     * Handles interaction with the locker.
     * Displays appropriate dialogue and choices depending on player and state.
     */
    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentPlayer = activePlayer;
        String[] choices;
        // Set choices based on which player is interacting
        if (activePlayer == 1) {
            choices = new String[] {"-W- to Steal"};
        } else {
            choices = new String[] {"-I- to Steal"};
        }
        // If the document has already been taken, update dialogue and remove choices
        if (this.state == 1){
            this.dialouge = "It's empty now ... you thief!";
            choices = new String[0];
        }
        // Display dialogue for player 1
        if (activePlayer == 1) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP1(this.getObjectName(), this.dialouge, 1, choices, 0);
            } else {
                GUI.writeDialougeCLIP1(this.getObjectName(), this.dialouge, 1, choices);
            }
        }
        // Display dialogue for player 2
        if (activePlayer == 2) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP2(this.getObjectName(), this.dialouge, 1, choices, 0);
            } else {
                GUI.writeDialougeCLIP2(this.getObjectName(), this.dialouge, 1, choices);
            }
        }
        return;
    }

    /**
     * Handles the "Up" action (e.g., player chooses to steal).
     * Gives the document to the player if the quest is accepted, updates state and dialogue.
     */
    @Override
    public void Up() {
        String[] choices = new String[0];
        int span;
        if (this.state == 0) {
            // If the quest is accepted, give the item and update state
            if (Game.getInstance().getQuestAccepted()[1] == true) {
                Game.getInstance().setItems(2, this.currentPlayer);
                Game.getInstance().getLog().addLogEntry("ITEMDocuments from Locker",this.currentPlayer);
                this.dialouge = "You take the documents,\nits probably the ones the Merchant wants.";
                span = 1;
                this.state = 1;
            } else {
                // If quest not accepted, show different dialogue
                this.dialouge = "It doesn't look like anything,\nnor something you need to steal.";
                span = 1;
            }
            // Show updated dialogue for the current player
            if (this.currentPlayer == 1) {
                if (Game.getInstance().getIsCLI() == false) {
                    Game.getInstance().getGUI().writeDialougeP1(this.getObjectName(), this.dialouge, span, choices, 0);
                } else {
                    Game.getInstance().getGUI().writeDialougeCLIP1(this.getObjectName(), this.dialouge, span, choices);
                }
            }
            if (this.currentPlayer == 2) {
                if (Game.getInstance().getIsCLI() == false) {
                    Game.getInstance().getGUI().writeDialougeP2(this.getObjectName(), this.dialouge, span, choices, 0);
                } else {
                    Game.getInstance().getGUI().writeDialougeCLIP2(this.getObjectName(), this.dialouge, span, choices);
                }
            }
        }
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