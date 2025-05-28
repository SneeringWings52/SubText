package com.cw1;

/**
 * Represents a shelf object in the game.
 * Handles player interaction, organization state, and dialogue display.
 */
public class Shelf extends Object {
    // globalObjectID = 10

    // Tracks which player is currently interacting with the shelf
    private int currentPlayer = 0;

    // Indicates whether the shelf is organized (true) or not (false)
    public boolean organised = false;

    // Dialogue to display when interacting with the shelf
    private String dialouge = "The shelf's contents are spilling out,\nall onto the floor.\nVery unefficiently stacked too ...";

    /**
     * Returns the current player interacting with the shelf.
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
        return "Shelf";
    }

    /**
     * Returns the state of the shelf.
     * 1 if organized, 0 if not.
     */
    @Override
    public int getState() {
        if (this.organised == true) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Handles interaction with the shelf.
     * Displays appropriate dialogue and choices depending on organization state.
     */
    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentPlayer = activePlayer;
        String[] choices;
        if (activePlayer == 1) {
            choices = new String[] {"-W- to Organise"};
        } else {
            choices = new String[] {"-I- to Organise"};
        }
        // If already organized, remove choices
        if (this.organised == true) {
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
     * Handles the "Up" action (player organizes the shelf).
     * Updates organization state and dialogue, and displays the result.
     */
    @Override
    public void Up() {
        if (this.organised == false) {
            this.organised = true;
            this.dialouge = "The shelf looks nice and neatly organised,\nfine work from yourself.";
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