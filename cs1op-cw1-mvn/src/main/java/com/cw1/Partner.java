package com.cw1;

public class Partner extends Object{
    //private int globalObjectID = 6;
    private int currentPlayer = 0;
    private String dialouge = "I'd really like some flowers. Just a shame they don't grow in Conamara, but I've heard there are some in Jimo. Maybe you could travel over?";

    @Override
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public String getObjectName() {
        return "Partner";
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
        } if (Game.getInstance().getItems()[3] != 0) {
            this.dialouge = "Thank you so much, I really appreciate these flowers.";
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
        if (Game.getInstance().getQuestAccepted()[0] == false) {
            this.dialouge = "It's a litle bit of a journey, about 3 swims away, so maybe stop by the Merchant first. Thank you!";
            Game.getInstance().setQuestAccepted(0, true);
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