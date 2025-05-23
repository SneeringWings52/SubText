package com.cw1;

import java.util.Scanner;

public class ManualEventFilter {
    private GraphicalUserInterface source;

    public ManualEventFilter(GraphicalUserInterface GUI) {
        this.source = GUI;
    }

    public void handle() {
        this.source.outputCLI();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a move:");
        String move = scanner.nextLine();
        switch (move.toUpperCase()) {
            case "Q":
                Game.getInstance().updatePlayerSelectionCLI(1, this.source.getCurrentRoomIObjectsP1());
                break;
            case "E":
                Game.getInstance().interactPlayerIObject(1);
                break;
            case "W":
                Game.getInstance().updatePlayerRoomCLI(1, 1);
                break;
            case "A":
                Game.getInstance().updatePlayerRoomCLI(1, 2);
                break;
            case "S":
                Game.getInstance().updatePlayerRoomCLI(1, 3);
                break;
            case "D":
                Game.getInstance().updatePlayerRoomCLI(1, 4);
                break;
            case "U":
                Game.getInstance().updatePlayerSelectionCLI(2, this.source.getCurrentRoomIObjectsP2());
                break;
            case "O":
                Game.getInstance().interactPlayerIObject(2);
                break;
            case "I":
                Game.getInstance().updatePlayerRoomCLI(2, 1);
                break;
            case "J":
                Game.getInstance().updatePlayerRoomCLI(2, 2);
                break;
            case "K":
                Game.getInstance().updatePlayerRoomCLI(2, 3);
                break;
            case "L":
                Game.getInstance().updatePlayerRoomCLI(2, 4);
                break;
            case "KILL":
                System.exit(0);
            default:
                break;
        }
    }
}