package com.cw1.EventFilters;

import com.cw1.Game;
import com.cw1.GraphicalUserInterface;

import javafx.event.*;
import javafx.scene.input.KeyEvent;

/**
 * Event filter for handling Player 1's keyboard input in the GUI.
 * Maps specific key presses to player actions in the game.
 */
public class P1CustomEventFilter implements EventHandler<KeyEvent> {
    // Reference to the GUI interface
    private GraphicalUserInterface source;

    /**
     * Constructs a P1CustomEventFilter with the given GUI source.
     * @param GUI The graphical user interface handler.
     */
    public P1CustomEventFilter(GraphicalUserInterface GUI) {
        this.source = GUI;
    }

    /**
     * Handles Player 1's key events and triggers corresponding game actions.
     * Q: Cycle interactive objects
     * E: Interact with selected object
     * W/A/S/D: Move between rooms (up/left/down/right)
     */
    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case Q:
                Game.getInstance().updatePlayerSelection(1, this.source.getCurrentRoomLabelsP1());
                event.consume();
                break;
            case E:
                Game.getInstance().interactPlayerIObject(1);
                break;
            case W:
                Game.getInstance().updatePlayerRoom(1, 1);
                event.consume(); 
                break;
            case A:
                Game.getInstance().updatePlayerRoom(1, 2);
                event.consume();
                break;
            case S:
                Game.getInstance().updatePlayerRoom(1, 3);
                event.consume();
                break;
            case D:
                Game.getInstance().updatePlayerRoom(1, 4);
                event.consume();
                break;
            default:
                break;
        }
    }
}
