package com.cw1;

public class FlowerMerchant extends Object{
    //private int globalObjectID = 13;
    private int currentPlayer = 0;
    private int state = 0;
    private String dialouge = "Hello, I suppose you're here for flowers? Don't worry, you are in the right place! I have a fresh bouquet of tulips available? Only a single coin!";

    @Override
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public String getObjectName() {
        return "Flower Merchant";
    }

    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentPlayer = activePlayer;
        String[] choices;
        if (activePlayer == 1) {
            choices = new String[] {"-W- to Accept", "Coin Required"};
        } else {
            choices = new String[] {"-I- to Accept", "Coin Required"};
        } if (Game.getInstance().getItems()[0] != 0) {
            this.dialouge = "I hope your enjoying your flowers! They grew only in the back there under careful care!";
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
            this.state = 1;
            if (Game.getInstance().getQuestAccepted()[0] == true) {
                if (Game.getInstance().getItems()[3] != 0) {
                    Game.getInstance().setItems(0, this.currentPlayer);
                    this.dialouge = "Here you go! Enjoy your tulips and come back anytime!";
                    span = 1;
                } else {
                    this.dialouge = "Well ... you need a coin. Maybe speak to a Merchant to see if they have any work. Although most Jimo Merchants are rude.";
                    span = 2;
                }
            } else {
                this.dialouge = "If you don't need flowers, then I guess you should come back if you ever do. Please..?";
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
        this.state = 0;
        Game.getInstance().reloadRoom(tempPlayer);
        return;
    }
}