package com.cw1;

import java.util.Scanner;

/**
 * Handles manual event filtering for CLI input.
 * Reads player input from the console and triggers corresponding game actions.
 */
public class ManualEventFilter {
    // Reference to the GUI or CLI interface
    private GraphicalUserInterface source;

    /**
     * Constructs a ManualEventFilter with the given GUI source.
     * @param GUI The graphical user interface or CLI handler.
     */
    public ManualEventFilter(GraphicalUserInterface GUI) {
        this.source = GUI;
    }

    /**
     * Handles user input from the CLI.
     * Maps specific key commands to player actions in the game.
     */
    public void handle() {
        this.source.outputCLI();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a move:");
        String move = scanner.nextLine();
        switch (move.toUpperCase()) {
            // Player 1 controls
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
            // Player 2 controls
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
            // Exit command
            case "KILL":
                System.exit(0);
            default:
                break;
        }
    }
}