package com.cw1;

import java.util.*;

public class Command extends Object{
    //private int globalObjectID = 2;
    private int currentPlayer = 0;
    private int currentChoicePlace = 0;
    private String[] locationChoices;
    List<Location> locationSwims;

    @Override
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public String getObjectName() {
        return "Command";
    }

    @Override
    public void interact(GraphicalUserInterface GUI, int activePlayer) {
        this.currentChoicePlace = 0;
        currentPlayer = activePlayer;
        int locationID = Game.getInstance().getCurrentPlayerLocation().getLocID();
        int[] localLocationMatrix= Game.getInstance().getLocationMatrix()[locationID];
        this.locationSwims = new ArrayList<>();
        for (int i = 0; i < localLocationMatrix.length; i++) {
            if (localLocationMatrix[i] == 1) {
                this.locationSwims.add(Game.getInstance().getAvailableLocations()[i]);
            }
        }
        this.locationChoices = new String[this.locationSwims.size()];
        for (int i = 0; i < this.locationChoices.length; i++) {
            this.locationChoices[i] = this.locationSwims.get(i).getLocationName();
        }
        this.locationChoices[0] = "->  " + this.locationChoices[0] + "  <-";
        if (activePlayer == 1) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP1(this.getObjectName(), "Select Location and\n-W- to Confirm", 2, this.locationChoices, 0);
            } else {
                GUI.writeDialougeCLIP1(this.getObjectName(), "Select Location and -W- to Confirm", 1, this.locationChoices);
            }
        }
        if (activePlayer == 2) {
            if (Game.getInstance().getIsCLI() == false) {
                GUI.writeDialougeP2(this.getObjectName(), "Select Location and\n-I- to Confirm", 2, this.locationChoices, 0);
            } else {
                GUI.writeDialougeCLIP2(this.getObjectName(), "Select Location and -I- to Confirm", 1, this.locationChoices);
            }
        }
        return;
    }

    @Override
    public void Up() {
        int tempPlayer = this.currentPlayer;
        this.currentPlayer = 0;
        Game.getInstance().updateLocation(this.locationSwims.get(this.currentChoicePlace), tempPlayer);
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
        this.locationChoices[this.currentChoicePlace] = this.locationSwims.get(this.currentChoicePlace).getLocationName();
        if (this.currentChoicePlace + 1 >= this.locationChoices.length) {
            this.currentChoicePlace = 0;
        } else {
            this.currentChoicePlace += 1;
        }
        System.out.println(this.currentChoicePlace);
        this.locationChoices[this.currentChoicePlace] = "->  " + this.locationChoices[this.currentChoicePlace] + "  <-";
        if (this.currentPlayer == 1) {
            if (Game.getInstance().getIsCLI() == false) {
                Game.getInstance().getGUI().writeDialougeP1(this.getObjectName(), "Select Location, use\n-W- to Confirm", 2, this.locationChoices, this.currentChoicePlace);
            } else {
                Game.getInstance().getGUI().writeDialougeCLIP1(this.getObjectName(), "Select Location, use -W- to Confirm", 1, this.locationChoices);
            }
        }
        if (this.currentPlayer == 2) {
            if (Game.getInstance().getIsCLI() == false) {
                Game.getInstance().getGUI().writeDialougeP2(this.getObjectName(), "Select Location, use\n-I- to Confirm", 2, this.locationChoices, this.currentChoicePlace);
            } else {
                Game.getInstance().getGUI().writeDialougeCLIP2(this.getObjectName(), "Select Location, use -I- to Confirm", 1, this.locationChoices);
            }
        }
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