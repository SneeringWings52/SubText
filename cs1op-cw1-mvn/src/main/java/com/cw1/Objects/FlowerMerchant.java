package com.cw1.Objects;

import com.cw1.Game;
import com.cw1.GraphicalUserInterface;

/**
 * Represents the Flower Merchant NPC in the game.
 * Handles player interaction, quest and item state, and dialogue display.
 */
public class FlowerMerchant extends Object {
    // globalObjectID = 13

    // Tracks which player is currently interacting with the flower merchant
    private int currentPlayer = 0;

    // State of the merchant: 0 = not interacted, 1 = interacted
    private int state = 0;

    // Dialogue to display when interacting with the flower merchant
    private String dialouge = "Hello, I suppose you're here for flowers?\nDon't worry, you are in the right place!\nI have a fresh bouquet of tulips available?\nOnly a single coin!";

    /**
     * Returns the current player interacting with the flower merchant.
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
        return "Flower Merchant";
    }

    /**
     * Handles interaction with the flower merchant.
     * Displays appropriate dialogue and choices depending on quest and item state.
     */
    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentPlayer = activePlayer;
        String[] choices;
        // Set choices based on which player is interacting
        if (activePlayer == 1) {
            choices = new String[] {"-W- to Accept", "Coin Required"};
        } else {
            choices = new String[] {"-I- to Accept", "Coin Required"};
        }
        // If player already has flowers, update dialogue and remove choices
        if (Game.getInstance().getItems()[0] != 0) {
            this.dialouge = "I hope your enjoying your flowers!\nThey grew only in the back there,\nunder careful care!";
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
     * Handles the "Up" action (player accepts the flowers).
     * Gives the flowers to the player if they have a coin and the quest is accepted.
     * Updates state, dialogue, and logs the action.
     */
    @Override
    public void Up() {
        String[] choices = new String[0];
        int span;
        if (this.state == 0) {
            this.state = 1;
            if (Game.getInstance().getQuestAccepted()[0] == true) {
                if (Game.getInstance().getItems()[3] != 0) {
                    // Player has a coin, give flowers and log the action
                    Game.getInstance().setItems(0, this.currentPlayer);
                    Game.getInstance().getLog().addLogEntry("ITEMTulips from Flower Merchant", this.currentPlayer);
                    Game.getInstance().setItems(3, 0);
                    this.dialouge = "Here you go!\nEnjoy your tulips and come back anytime!";
                    span = 2;
                } else {
                    // Player does not have a coin
                    this.dialouge = "Well ... you need a coin.\nMaybe speak to a Merchant,they tend to have coins.\nAlthough most Jimo Merchants are rude.";
                    span = 2;
                }
            } else {
                // Quest not accepted
                this.dialouge = "If you don't need flowers now,\nthen you can come back if you ever do.\nPlease..?";
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
     * Resets the current player and state, and reloads the room for that player.
     */
    @Override
    public void RInteract() {
        int tempPlayer = this.currentPlayer;
        this.currentPlayer = 0;
        this.state = 0;
        Game.getInstance().reloadRoom(tempPlayer);
        return;
    }
}