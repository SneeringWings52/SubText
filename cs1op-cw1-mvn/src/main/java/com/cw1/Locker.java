package com.cw1;

import java.util.Random;
public class Locker extends Object{
    //private int globalObjectID = 0;
    private int currentPlayer = 0;
    private String dialouge = "";

    @Override
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public String getObjectName() {
        return "Locker";
    }

    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        if (this.dialouge.equals("")) {
            this.setRandomDialouge();
        }
        this.currentPlayer = activePlayer;
        if (activePlayer == 1) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP1(this.getObjectName(), this.dialouge, 2, new String[0], -1);
            } else {
                GUI.writeDialougeCLIP1(this.getObjectName(), this.dialouge, 1, new String[0]);
            }
        }
        if (activePlayer == 2) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP2(this.getObjectName(), this.dialouge, 2, new String[0], -1);
            } else {
                GUI.writeDialougeCLIP2(this.getObjectName(), this.dialouge, 1, new String[0]);
            }
        }
        return;
    }

    public void setRandomDialouge() {
        Random r= new Random();
        int rSelect = r.nextInt(4);
        switch(rSelect) {
            case 0:
                this.dialouge = "Its locked, you can't\nget in.";
                break;
            case 1:
                this.dialouge = "Its unlocked, revealing\nan array of\nmismatched toolsets.";
                break;
            case 2:
                this.dialouge = "Its locked, you can't\nget in.";
                break;
            case 3:
                this.dialouge = "Its locked, you can't\nget in.";
                break;
            }
        return;
    }

    @Override
    public void Up() {
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

