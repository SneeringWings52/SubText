package com.cw1;

public class AmaltheaQuestLocker extends Object{
    //private int globalObjectID = 9;
    private int currentPlayer = 0;
    private int state = 0; 
    private String dialouge = "There is an important-looking document resting on a shelf here.";

    @Override
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public String getObjectName() {
        return "Locker";
    }

    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentPlayer = activePlayer;
        String[] choices;
        if (activePlayer == 1) {
            choices = new String[] {"-W- to Take"};
        } else {
            choices = new String[] {"-I- to Take"};
        } if (this.state == 1){
            this.dialouge = "It's empty now ... you thief!";
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
        String[] choices = new String[0];
        int span;
        if (this.state == 0) {
            if (Game.getInstance().getQuestAccepted()[1] == true) {
                Game.getInstance().setItems(2, this.currentPlayer);
                this.dialouge = "You take the documents, its probably the ones the vague Merchant was on about.";
                span = 1;
                this.state = 1;
            } else {
                this.dialouge = "It doesn't look like anything you need to take.";
                span = 1;
            }
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