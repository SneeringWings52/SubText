package com.cw1;

import java.util.Random;

public class Window extends Object{
    //private int globalObjectID = 3;
    private int currentPlayer = 0;
    private String dialouge = "";

    @Override
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public String getObjectName() {
        return "Window";
    }

    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.setRandomDialouge();
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
        int rSelect = r.nextInt(3);
        switch(rSelect) {
            case 0:
                this.dialouge = "Its pitch-black,\nalmost like you are\ndeep underwater.";
                break;
            case 1:
                this.dialouge = "It looks like\nits raining outside,\nbut like ..more.";
                break;
            case 2:
                this.dialouge = "You see yourself in\nthe dark reflection,\npulling a stupid face.";
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
