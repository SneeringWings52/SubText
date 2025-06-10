package com.cw1.Objects;

import com.cw1.Game;
import com.cw1.GraphicalUserInterface;

/**
 * Represents the Partner NPC in the game.
 * Handles player interaction, quest acceptance, and dialogue display.
 */
public class Partner extends Object {
    // globalObjectID = 6

    // Tracks which player is currently interacting with the partner
    private int currentPlayer = 0;

    // Dialogue to display when interacting with the partner
    private String dialouge = "I'd really like some flowers.\nJust a shame they don't grow in Conamara,\nbut I've heard there are some in Jimo.\nMaybe you could travel over?";

    /**
     * Returns the current player interacting with the partner.
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
        return "Partner";
    }

    /**
     * Handles interaction with the partner.
     * Displays appropriate dialogue and choices depending on quest and item state.
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
        if (Game.getInstance().getQuestAccepted()[0] == true) {
            span = 3;
            choices = new String[0];
        }
        // If player has the flowers (item 0), update dialogue and remove choices
        if (Game.getInstance().getItems()[0] != 0) {
            this.dialouge = "Thank you so much,\nI really appreciate these flowers.";
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
        if (Game.getInstance().getQuestAccepted()[0] == false) {
            this.dialouge = "It's a litle bit of a journey,\nabout 3 swims away,\nso maybe stop by the Merchant first.\nThank you!";
            Game.getInstance().getLog().addLogEntry("QUESTAccepted Partner", this.currentPlayer);
            Game.getInstance().setQuestAccepted(0, true);
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