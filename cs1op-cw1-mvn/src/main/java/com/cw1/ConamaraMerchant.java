package com.cw1;

public class ConamaraMerchant extends Object{
    //private int globalObjectID = 7;
    private int currentPlayer = 0;
    private String dialouge = "I can get you some supplies, of course! But you need to do something for me...";

    @Override
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public String getObjectName() {
        return "Merchant";
    }

    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentPlayer = activePlayer;
        String[] choices;
        if (activePlayer == 1) {
            choices = new String[] {"-W- to Take"};
        } else {
            choices = new String[] {"-I- to Take"};
        } if (Game.getInstance().getQuestAccepted()[0] == true) {
            choices = new String[0];
        } if (Game.getInstance().getItems()[1] != 0 && Game.getInstance().getItems()[2] != 0) {
            this.dialouge = "Thank you, you've been an amazing help for our movement! You don't know the power these documents hold!";
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
        if (Game.getInstance().getQuestAccepted()[1] == false) {
            this.dialouge = "Go to the Amalthea Miltary Camp and steal two documents. Probably check the office for them? Heres some supplies for your journey, good luck.";
            Game.getInstance().setQuestAccepted(1, true);
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