package com.cw1;

public class JohannesMerchant extends Object{
    //private int globalObjectID = 11;
    private int currentPlayer = 0;
    private String dialouge = "Do you need some work? I've got a coin going for anyone who can organise my shelfs? An amazing deal! For a short time!";

    @Override
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public String getObjectName() {
        return "Merchant";
    }

    public boolean getIfShelfOrganised() {
        if (Game.getInstance().getCurrentPlayerLocation().getLocationName().equals("Johannes Stop")) {
            Location location = Game.getInstance().getAvailableLocations()[2];
            boolean shelfState1 = location.getRooms()[4].getiObjects()[1].getQuestState();
        }
        return true;
        return false;
    }
    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentPlayer = activePlayer;
        String[] choices;
        if (activePlayer == 1) {
            choices = new String[] {"-W- to Accept"};
        } else {
            choices = new String[] {"-I- to Accept"};
        } if (Game.getInstance().getQuestAccepted()[0] == true) {
            choices = new String[0];
        } if (this.getIfShelfOrganised() == true) {
            this.dialouge = "I am forever grateful! For some simple organising!";
            choices = new String[0];
            if (Game.getInstance().getItems()[0] == 0) {
                Game.getInstance().setItems(3, activePlayer);
                this.dialouge = "Thank you, here is a single coin for your troubles! Get something nice!";
            }
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
     if (Game.getInstance().getQuestAccepted()[2] == false) {
            this.dialouge = "Be quick, its only in the storage room right through that door there! Come straight back!";
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