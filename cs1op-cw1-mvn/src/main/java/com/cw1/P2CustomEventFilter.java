package com.cw1;

import javafx.event.*;
import javafx.scene.input.KeyEvent;

/**
 * Event filter for handling Player 2's keyboard input in the GUI.
 * Maps specific key presses to player actions in the game.
 */
public class P2CustomEventFilter implements EventHandler<KeyEvent> {
    // Reference to the GUI interface
    private GraphicalUserInterface source;
    
    /**
     * Constructs a P2CustomEventFilter with the given GUI source.
     * @param GUI The graphical user interface handler.
     */
    public P2CustomEventFilter(GraphicalUserInterface GUI) {
        this.source = GUI;
    }

    /**
     * Handles Player 2's key events and triggers corresponding game actions.
     * U: Cycle interactive objects
     * O: Interact with selected object
     * I/J/K/L: Move between rooms (up/left/down/right)
     */
    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case U:
                Game.getInstance().updatePlayerSelection(2, this.source.getCurrentRoomLabelsP2());
                event.consume();
                break;
            case O:
                Game.getInstance().interactPlayerIObject(2);
                break;
            case I:
                Game.getInstance().updatePlayerRoom(2, 1);
                event.consume(); 
                break;
            case J:
                Game.getInstance().updatePlayerRoom(2, 2);
                event.consume();
                break;
            case K:
                Game.getInstance().updatePlayerRoom(2, 3);
                event.consume();
                break;
            case L:
                Game.getInstance().updatePlayerRoom(2, 4);
                event.consume();
                break;
            default:
                break;
        }
    }
}
