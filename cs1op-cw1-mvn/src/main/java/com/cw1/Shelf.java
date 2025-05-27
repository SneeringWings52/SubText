package com.cw1;

public class Shelf extends Object{
    //private int globalObjectID = 10;
    private int currentPlayer = 0;
    public boolean organised = false;
    private String dialouge = "The shelf's contents are spilling out onto the floor. Very unefficiently stacked too ...";

    public boolean getOrganised() {
        return this.organised;
    }

    @Override
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public String getObjectName() {
        return "Shelf";
    }

    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentPlayer = activePlayer;
        String[] choices;
        if (activePlayer == 1) {
            choices = new String[] {"-W- to Organise"};
        } else {
            choices = new String[] {"-I- to Organise"};
        } if (this.organised == true) {
            choices = new String[0];
        }
        if (activePlayer == 1) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP1(this.getObjectName(), this.dialouge, 1, choices, 0);
            } else {
                GUI.writeDialougeCLIP1(this.getObjectName(), this.dialouge, 1, choices);
            }
        }
        if (activePlayer == 2) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP2(this.getObjectName(), this.dialouge, 1, choices, 0);
            } else {
                GUI.writeDialougeCLIP2(this.getObjectName(), this.dialouge, 1, choices);
            }
        }
        return;
    }


    @Override
    public void Up() {
        if (this.organised == false) {
            this.organised = true;
            this.dialouge = "The shelf looks nice and neatly organised, fine work from yourself.";
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
    
    @Override
    public void Left() {
        return;
    }

    @Override
    public void Down() {
        return;
    }

    @Override
    public void Right() {
        return;
    }

    @Override
    public void LInteract() {
        return;
    }

    @Override
    public void RInteract() {
        int tempPlayer = this.currentPlayer;
        this.currentPlayer = 0;
        Game.getInstance().reloadRoom(tempPlayer);
        return;
    }
}