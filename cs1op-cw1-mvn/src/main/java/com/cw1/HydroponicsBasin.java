package com.cw1;

/**
 * Represents a hydroponics basin object in the game.
 * Handles player interaction, state changes, and dialogue display.
 */
public class HydroponicsBasin extends Object {
    // globalObjectID = 14

    // Tracks which player is currently interacting with the basin
    private int currentPlayer = 0;

    // State of the basin: 0 = untouched, 1 = tulips stolen
    private int state = 0;

    // Dialogue to display when interacting with the basin
    private String dialouge = "There is a cluster of beautiful tulips,\nin the middle of all the devices.";

    /**
     * Returns the current player interacting with the basin.
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
        return "Hydroponics Basin";
    }

    /**
     * Handles interaction with the basin.
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
        // If the tulips have already been stolen, update dialogue and remove choices
        if (this.state == 1) {
            this.dialouge = "It's now an withering patch of dry soil,\nI hope it was worth it...";
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
     * Handles the "Up" action (e.g., player chooses to steal the tulips).
     * Gives the tulips to the player if the quest is accepted, updates state and dialogue.
     */
    @Override
    public void Up() {
        String[] choices = new String[0];
        if (this.state == 0) {
            // If the quest is accepted, give the item and update state
            if (Game.getInstance().getQuestAccepted()[0] == true) {
                Game.getInstance().setItems(0, this.currentPlayer);
                this.dialouge = "You steal the tulips,\nhopefully your partner doesn't find out.";
                this.state = 1;
            } else {
                // If quest not accepted, show different dialogue
                this.dialouge = "They are stunning flowers,\ngood thing you don't need to steal them.";
            }
            // Show updated dialogue for the current player
            if (this.currentPlayer == 1) {
                if (Game.getInstance().getIsCLI() == false) {
                    Game.getInstance().getGUI().writeDialougeP1(this.getObjectName(), this.dialouge, 2, choices, 0);
                } else {
                    Game.getInstance().getGUI().writeDialougeCLIP1(this.getObjectName(), this.dialouge, 2, choices);
                }
            }
            if (this.currentPlayer == 2) {
                if (Game.getInstance().getIsCLI() == false) {
                    Game.getInstance().getGUI().writeDialougeP2(this.getObjectName(), this.dialouge, 2, choices, 0);
                } else {
                    Game.getInstance().getGUI().writeDialougeCLIP2(this.getObjectName(), this.dialouge, 2, choices);
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