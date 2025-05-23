package com.cw1;

import javafx.scene.control.Label;
import java.util.*;

public class Game {
    private static Game instance;
    private GraphicalUserInterface GUI;
    private boolean isCLI = false;
    private boolean gameComplete = false;
    private String[] locationFilenames = new String[] {"ConamaraSettlementLoc.txt", "AmaltheaCampLoc.txt", "JohannesStopLoc.txt", "JimoMarketLoc.txt"};
    private int[][] locationMatrix = new int[][] {{0,1,0,0},{1,0,1,0},{0,1,0,1},{1,0,1,0}};
    private Location[] availableLocations = new Location[this.locationFilenames.length];
    private Location currentPlayerLocation; 
    private int[] currentPlayeriObject = new int[2];
    private Room[] availableRooms; //
    private int[][] roomMatrix; //
    private Room[] currentPlayerRoom; //
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public GraphicalUserInterface getGUI() {
        return this.GUI;
    }

    public boolean getGameComplete() {
        return gameComplete;
    }

    public boolean[] getPlayerIObjectState() {
        return this.GUI.getPlayerIObjectState();
    }

    public Location getCurrentPlayerLocation() {
        return this.currentPlayerLocation;
    }

    public Room[] getCurrentPlayerRoom() {
        return this.currentPlayerRoom;
    }

    public Location[] getAvailableLocations() {
        return this.availableLocations;
    }

    public int[][] getLocationMatrix() {
        return this.locationMatrix;
    }

    public boolean getIsCLI() {
        return this.isCLI;
    }

    public void setIsCLI(boolean value) {
        this.isCLI = value;
    }

    public Location gameSetup(GraphicalUserInterface GUI) {
        this.GUI = GUI;
        for (int i = 0; i < this.locationFilenames.length; i++) {
            this.availableLocations[i] = new Location(i, this.locationFilenames[i]);
        }
        this.currentPlayerLocation = this.availableLocations[0];
        this.availableRooms = this.currentPlayerLocation.getRooms();
        this.roomMatrix = this.currentPlayerLocation.getRoomMatrix();
        this.currentPlayerRoom = new Room[] {this.availableRooms[0], this.availableRooms[0]};
        return this.currentPlayerLocation;
    }

    public void reloadRoom(int activePlayer) {
        this.currentPlayeriObject[activePlayer - 1] = 0;
        if (activePlayer == 1) {
            this.GUI.setPlayerIObjectState(0, false);
            if (this.isCLI == false) {
                GUI.removeIObjectLabelsP1();
                GUI.addNextRoomLabelsP1(currentPlayerRoom[0]);
            } else {
                GUI.addNextRoomStringsP1(currentPlayerRoom[0]);
            }
        }
        if (activePlayer == 2) {
            this.GUI.setPlayerIObjectState(1, false);
            if (this.isCLI == false) {
                GUI.removeIObjectLabelsP2();
                GUI.addNextRoomLabelsP2(currentPlayerRoom[1]);
            } else {
                GUI.addNextRoomStringsP2(currentPlayerRoom[1]);
            }
        }
    }

    public void updateLocation(Location newLocation, int activePlayer) {
        if (activePlayer == 1) {
            GUI.removeIObjectLabelsP1();
            if (checkPlayerIObject(2) == true) {
                getPlayerIObject(2).RInteract();
            }
        }else if (activePlayer == 2) {
            GUI.removeIObjectLabelsP2();
            if (checkPlayerIObject(1) == true) {
                getPlayerIObject(1).RInteract();
            }
        }
        this.currentPlayerLocation = newLocation;
        this.currentPlayeriObject = new int[2];
        this.availableRooms = this.currentPlayerLocation.getRooms();
        this.roomMatrix = this.currentPlayerLocation.getRoomMatrix();
        this.currentPlayerRoom = new Room[] {this.availableRooms[0], this.availableRooms[0]};
        GUI.setCurrentLocation(newLocation);
        this.reloadRoom(1);
        this.reloadRoom(2);
    }
    public boolean checkPlayerIObject(int activePlayer) {
        activePlayer -= 1;
        if (activePlayer + 1 == currentPlayerRoom[activePlayer].getiObjects()[this.currentPlayeriObject[activePlayer]].getCurrentPlayer()) {
            return true;
        } else {
            return false;
        }
    }

    public Object getPlayerIObject(int activePlayer) {
        activePlayer -= 1;
        return currentPlayerRoom[activePlayer].getiObjects()[this.currentPlayeriObject[activePlayer]];
    }

    public void interactPlayerIObject(int activePlayer) {
        if(this.checkPlayerIObject(activePlayer) == true) {
            this.getPlayerIObject(activePlayer).RInteract();
            return;
        } else {
            if (currentPlayerRoom[activePlayer - 1].getiObjects()[this.currentPlayeriObject[activePlayer - 1]].getCurrentPlayer() == 0) {
                this.GUI.setPlayerIObjectState(activePlayer - 1, true);
                currentPlayerRoom[activePlayer - 1].getiObjects()[this.currentPlayeriObject[activePlayer - 1]].interact(GUI, activePlayer);
            } //else another Player is interacting  
        }
    }

    public void updatePlayerSelection(int activePlayer, List<Label> curRoomLabels) {
        if (this.checkPlayerIObject(activePlayer) == true) {
            this.getPlayerIObject(activePlayer).LInteract();
            return;
        }
        activePlayer -= 1;
        String[] playerAvailableObjects = this.currentPlayerRoom[activePlayer].getObjectNames();
        
        curRoomLabels.get(this.currentPlayeriObject[activePlayer]).setText(playerAvailableObjects[this.currentPlayeriObject[activePlayer]]);
        curRoomLabels.get(this.currentPlayeriObject[activePlayer]).setId("i-object");
        if (this.currentPlayeriObject[activePlayer] ==  playerAvailableObjects.length - 1) {
            curRoomLabels.get(0).setText("->  "+ playerAvailableObjects[0] +"  <-");
            curRoomLabels.get(0).setId("selected-i-object");
            this.currentPlayeriObject[activePlayer] = 0;

        } else {
            curRoomLabels.get(this.currentPlayeriObject[activePlayer] + 1).setText("->  "+ playerAvailableObjects[this.currentPlayeriObject[activePlayer] + 1] +"  <-");
            curRoomLabels.get(this.currentPlayeriObject[activePlayer] + 1).setId("selected-i-object");
            this.currentPlayeriObject[activePlayer] += 1; 
        }

    }

    public void updatePlayerSelectionCLI(int activePlayer, List<String> curRoomStrings) {
        if (this.checkPlayerIObject(activePlayer) == true) {
            this.getPlayerIObject(activePlayer).LInteract();
            return;
        }
        activePlayer -= 1;
        String[] playerAvailableObjects = this.currentPlayerRoom[activePlayer].getObjectNames();
        
        curRoomStrings.set(this.currentPlayeriObject[activePlayer], playerAvailableObjects[this.currentPlayeriObject[activePlayer]]);
        if (this.currentPlayeriObject[activePlayer] ==  playerAvailableObjects.length - 1) {
            curRoomStrings.set(0, "->  "+ playerAvailableObjects[0] +"  <-");
            this.currentPlayeriObject[activePlayer] = 0;

        } else {
            curRoomStrings.set(this.currentPlayeriObject[activePlayer] + 1,"->  "+ playerAvailableObjects[this.currentPlayeriObject[activePlayer] + 1] +"  <-");
            this.currentPlayeriObject[activePlayer] += 1; 
        }

    }

    public void updatePlayerRoom(int activePlayer, int direction) {
        if (this.checkPlayerIObject(activePlayer) == true) {
            switch(direction) {
                case 1:
                    this.getPlayerIObject(activePlayer).Up();
                    break;
                case 2:
                    this.getPlayerIObject(activePlayer).Left();
                    break;
                case 3:
                    this.getPlayerIObject(activePlayer).Down();
                    break;
                case 4:
                    this.getPlayerIObject(activePlayer).Right();
                    break;
            }
            return;
        }
        activePlayer -= 1;
        int curRoomID = this.currentPlayerRoom[activePlayer].getRoomID();
        int nextRoomID = -1;
        
        for (int i = 0; i < this.roomMatrix[curRoomID].length; i++) {
            if (direction == this.roomMatrix[curRoomID][i]) {
                nextRoomID = i;
                break;
            }
        }
        if (nextRoomID == -1) {
            return;
        }
        Room nextRoom = availableRooms[nextRoomID];
        this.currentPlayeriObject[activePlayer] = 0;
        this.currentPlayerRoom[activePlayer] = nextRoom;
        if (activePlayer == 0) {
            this.GUI.addNextRoomLabelsP1(nextRoom);
        }
        else {
            this.GUI.addNextRoomLabelsP2(nextRoom);
        }
    }

    public void updatePlayerRoomCLI(int activePlayer, int direction) {
        if (this.checkPlayerIObject(activePlayer) == true) {
            switch(direction) {
                case 1:
                    this.getPlayerIObject(activePlayer).Up();
                    break;
                case 2:
                    this.getPlayerIObject(activePlayer).Left();
                    break;
                case 3:
                    this.getPlayerIObject(activePlayer).Down();
                    break;
                case 4:
                    this.getPlayerIObject(activePlayer).Right();
                    break;
            }
            return;
        }
        activePlayer -= 1;
        int curRoomID = this.currentPlayerRoom[activePlayer].getRoomID();
        int nextRoomID = -1;
        
        for (int i = 0; i < this.roomMatrix[curRoomID].length; i++) {
            if (direction == this.roomMatrix[curRoomID][i]) {
                nextRoomID = i;
                break;
            }
        }
        if (nextRoomID == -1) {
            return;
        }
        Room nextRoom = availableRooms[nextRoomID];
        this.currentPlayeriObject[activePlayer] = 0;
        this.currentPlayerRoom[activePlayer] = nextRoom;
        if (activePlayer == 0) {
            this.GUI.addNextRoomStringsP1(nextRoom);
        }
        else {
            this.GUI.addNextRoomStringsP2(nextRoom);
        }
    }
}
