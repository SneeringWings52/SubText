package com.cw1.Objects;

import com.cw1.Game;
import com.cw1.GraphicalUserInterface;

/**
 * Represents the Conamara Merchant NPC in the game.
 * Handles player interaction, quest acceptance, and dialogue display.
 */
public class ConamaraMerchant extends Object {
    // globalObjectID = 7

    // Tracks which player is currently interacting with the merchant
    private int currentPlayer = 0;

    // Dialogue to display when interacting with the merchant
    private String dialouge = "I can get you some supplies, of course!\nBut you need to do something for me...";

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
     * Displays appropriate dialogue and choices depending on quest and item state.
     */
    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentPlayer = activePlayer;
        String[] choices;
        int span = 2;
        // Set choices based on which player is interacting
        if (activePlayer == 1) {
            choices = new String[] {"-W- to Accept"};
        } else {
            choices = new String[] {"-I- to Accept"};
        }
        // If quest already accepted, update dialogue and remove choices
        if (Game.getInstance().getQuestAccepted()[1] == true) {
            span = 3;
            choices = new String[0];
        }
        // If both documents have been collected, update dialogue and remove choices
        if (Game.getInstance().getItems()[1] != 0 && Game.getInstance().getItems()[2] != 0) {
            this.dialouge = "Thank you, you've been an amazing help!\nThe power these documents hold...";
            choices = new String[0];
        }
        // Display dialogue for player 1
        if (activePlayer == 1) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP1(this.getObjectName(), this.dialouge, span, choices, 0);
            } else {
                GUI.writeDialougeCLIP1(this.getObjectName(), this.dialouge, span, choices);
            }
        }
        // Display dialogue for player 2
        if (activePlayer == 2) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP2(this.getObjectName(), this.dialouge, span, choices, 0);
            } else {
                GUI.writeDialougeCLIP2(this.getObjectName(), this.dialouge, span, choices);
            }
        }
        return;
    }

    /**
     * Handles the "Up" action (player accepts the quest).
     * Updates quest state, logs the action, and displays new dialogue.
     */
    @Override
    public void Up() {
        if (Game.getInstance().getQuestAccepted()[1] == false) {
            this.dialouge = "Go to the Amalthea Miltary Camp and\nsteal two documents.\nProbably check the office for them?\nHeres some supplies for your journey.\nGood luck.";
            Game.getInstance().getLog().addLogEntry("QUESTAccepted Conamara Merchant", this.currentPlayer);
            Game.getInstance().setQuestAccepted(1, true);
            // Show updated dialogue for the current player
            if (this.currentPlayer == 1) {
                if (Game.getInstance().getIsCLI() == false) {
                    Game.getInstance().getGUI().writeDialougeP1(this.getObjectName(), this.dialouge, 3, new String[0], 0);
                } else {
                    Game.getInstance().getGUI().writeDialougeCLIP1(this.getObjectName(), this.dialouge, 3, new String[0]);
                }
            }
            if (this.currentPlayer == 2) {
                if (Game.getInstance().getIsCLI() == false) {
                    Game.getInstance().getGUI().writeDialougeP2(this.getObjectName(), this.dialouge, 3, new String[0], 0);
                } else {
                    Game.getInstance().getGUI().writeDialougeCLIP2(this.getObjectName(), this.dialouge, 3, new String[0]);
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
