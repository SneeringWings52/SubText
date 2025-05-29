package com.cw1.Objects;

import com.cw1.Game;
import com.cw1.GraphicalUserInterface;
import com.cw1.Location;

/**
 * Represents the Johannes Merchant NPC in the game.
 * Handles player interaction, quest acceptance, shelf organization, and dialogue display.
 */
public class JohannesMerchant extends Object {
    // globalObjectID = 11

    // Tracks which player is currently interacting with the merchant
    private int currentPlayer = 0;

    // Tracks if the coin has been given to the player
    private int coinCount = 0;

    // Dialogue to display when interacting with the merchant
    private String dialouge = "Do you need some work?\nI've got a coin going for anyone\nwho can organise my shelfs?\nAn amazing deal!\nFor a short time!";

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
     * Checks if the shelf in the storage room is organized.
     * Returns true if both shelf objects in the storage room have state == 1.
     */
    public boolean getIfShelfOrganised() {
        if (Game.getInstance().getCurrentPlayerLocation().getLocationName().equals("Johannes Stop")) {
            Location location = Game.getInstance().getCurrentPlayerLocation();
            if (location.getRooms()[4].getRoomName().equals("Storage")) {
                int shelfState1 = location.getRooms()[4].getiObjects()[1].getState();
                int shelfState2 = location.getRooms()[4].getiObjects()[2].getState();
                if (shelfState1 == 1 && shelfState2 == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Handles interaction with the merchant.
     * Displays appropriate dialogue and choices depending on quest and shelf state.
     * Gives a coin if the shelf is organized.
     */
    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentPlayer = activePlayer;
        String[] choices;
        int span = 3;
        // Set choices based on which player is interacting
        if (activePlayer == 1) {
            choices = new String[] {"-W- to Accept"};
        } else {
            choices = new String[] {"-I- to Accept"};
        }
        // If quest already accepted, update dialogue and remove choices
        if (Game.getInstance().getQuestAccepted()[2] == true) {
            span = 2;
            choices = new String[0];
        }
        // If shelf is organized, complete quest, give coin, and update dialogue
        if (this.getIfShelfOrganised() == true) {
            Game.getInstance().setQuestAccepted(2, true);
            Game.getInstance().getLog().addLogEntry("QUESTAccepted Johannes Merchant", activePlayer);
            this.dialouge = "I am forever grateful!\nFor some simple organising!";
            span = 2;
            choices = new String[0];
            if (this.coinCount == 0) {
                this.coinCount = 1;
                Game.getInstance().getLog().addLogEntry("ITEMCoin from Johannes Merchant", activePlayer);
                Game.getInstance().setItems(3, activePlayer);
                this.dialouge = "Thank you, here is a single coin.\nJust for your troubles!\nGet something nice!";
            }
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
     * Updates quest state and displays new dialogue.
     */
    @Override
    public void Up() {
        if (Game.getInstance().getQuestAccepted()[2] == false) {
            this.dialouge = "Be quick,\nits only in the storage room,\nright through that door there!\nCome straight back!";
            Game.getInstance().setQuestAccepted(2, true);
            if (this.currentPlayer == 1) {
                if (Game.getInstance().getIsCLI() == false) {
                    Game.getInstance().getGUI().writeDialougeP1(this.getObjectName(), this.dialouge, 1, new String[0], 0);
                } else {
                    Game.getInstance().getGUI().writeDialougeCLIP1(this.getObjectName(), this.dialouge, 1, new String[0]);
                }
            }
            if (this.currentPlayer == 2) {
                if (Game.getInstance().getIsCLI() == false) {
                    Game.getInstance().getGUI().writeDialougeP2(this.getObjectName(), this.dialouge, 1, new String[0], 0);
                } else {
                    Game.getInstance().getGUI().writeDialougeCLIP2(this.getObjectName(), this.dialouge, 1, new String[0]);
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