package com.cw1;
import javafx.event.*;
import javafx.scene.input.KeyEvent;

public class P1CustomEventFilter implements EventHandler<KeyEvent> {
    private GraphicalUserInterface source;

    public P1CustomEventFilter(GraphicalUserInterface GUI) {
        this.source = GUI;
    }

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
