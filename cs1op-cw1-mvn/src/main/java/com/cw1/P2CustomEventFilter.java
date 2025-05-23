package com.cw1;
import javafx.event.*;
import javafx.scene.input.KeyEvent;

public class P2CustomEventFilter implements EventHandler<KeyEvent> {
    private GraphicalUserInterface source;
    
    public P2CustomEventFilter(GraphicalUserInterface GUI) {
        this.source = GUI;
    }

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
