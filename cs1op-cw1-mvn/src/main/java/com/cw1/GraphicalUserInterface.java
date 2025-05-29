package com.cw1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import java.util.*;

import com.cw1.EventFilters.ManualEventFilter;
import com.cw1.EventFilters.P1CustomEventFilter;
import com.cw1.EventFilters.P2CustomEventFilter;

import javafx.scene.text.TextAlignment;

/**
 * Main class for the game's graphical user interface (GUI) and CLI.
 * Handles all display logic, user input, and scene management for both players.
 */
public class GraphicalUserInterface extends Application {
    // Main application window and layout
    private Stage stage = new Stage();
    private GridPane gridPane = new GridPane();
    private Location currentLocation;

    // GUI state for Player 1 and Player 2
    private Label[] currentRoomNameLabels = new Label[2];
    private List<Label> currentRoomLabelsP1 = new ArrayList<>();
    private Label[] currentRoomDirectionLabelsP1 = new Label[4];
    private List<Label> currentRoomLabelsP2 = new ArrayList<>();
    private Label[] currentRoomDirectionLabelsP2 = new Label[4];
    private List<Label> currentInteractLabelsP1 = new ArrayList<>();
    private List<Label> currentInteractLabelsP2 = new ArrayList<>();

    // CLI state for Player 1 and Player 2
    private boolean[] currentPlayeriObjectState = new boolean[] {false, false};
    private String[] currentRoomNamesCLI = new String[2];
    private List<String> currentRoomIObjectsP1 = new ArrayList<>();
    private String[] currentRoomDirectionsP1 = new String[4];
    private List<String> currentRoomIObjectsP2 = new ArrayList<>();
    private String[] currentRoomDirectionsP2 = new String[4];
    private List<String> currentInteractStringsP1 = new ArrayList<>();
    private List<String> currentInteractStringsP2 = new ArrayList<>();

    // Getters for GUI components and state
    public Stage getStage() { return this.stage; }
    public GridPane getGridPane() { return this.gridPane; }
    public List<Label> getCurrentRoomLabelsP1() { return this.currentRoomLabelsP1; }
    public List<Label> getCurrentRoomLabelsP2() { return this.currentRoomLabelsP2; }
    public List<String> getCurrentRoomIObjectsP1() { return this.currentRoomIObjectsP1; }
    public List<String> getCurrentRoomIObjectsP2() { return this.currentRoomIObjectsP2; }
    public boolean[] getPlayerIObjectState() { return this.currentPlayeriObjectState; }
    public void setPlayerIObjectState(int activePlayerIndex, boolean setValue) {
        this.currentPlayeriObjectState[activePlayerIndex] = setValue;
    }
    public void setCurrentLocation(Location newLocation) { this.currentLocation = newLocation; }

    /**
     * Adds and updates the room labels and direction labels for Player 1 in the GUI.
     */
    public void addNextRoomLabelsP1(Room nextRoom) {
        for (Label curLabel : this.currentRoomLabelsP1) {
            this.gridPane.getChildren().remove(curLabel);
        }
        this.gridPane.getChildren().remove(this.currentRoomNameLabels[0]);
        this.currentRoomLabelsP1 = new ArrayList<>();
        Label newLabel = new Label("->  "+ nextRoom.getObjectNames()[0] + "  <-");
        newLabel.setId("selected-i-object");
        this.currentRoomLabelsP1.add(newLabel);
        for (int i = 1; i < nextRoom.getObjectNames().length; i ++) {
            newLabel = new Label(nextRoom.getObjectNames()[i]);
            newLabel.setId("i-object");
            this.currentRoomLabelsP1.add(newLabel);
        }
        for (int i = 0; i < this.currentRoomLabelsP1.size(); i++) {
            this.gridPane.add(this.currentRoomLabelsP1.get(i), 1, i + 2);
        }
        this.currentRoomNameLabels[0] = new Label("-- "+ Game.getInstance().getCurrentPlayerLocation().getLocationName() +" : "+ nextRoom.getRoomName() +" --");
        this.currentRoomNameLabels[0].setId("current-room");
        this.gridPane.add(this.currentRoomNameLabels[0], 1, 1);
        updateRoomDirectionLabelsP1(nextRoom);
    }

    /**
     * Updates the direction labels for Player 1 based on the current room's connections.
     */
    public void updateRoomDirectionLabelsP1(Room room) {
        for (Label curRLabel : this.currentRoomDirectionLabelsP1) {
            this.gridPane.getChildren().remove(curRLabel);
        }
        this.currentRoomDirectionLabelsP1 = new Label[4];
        this.currentRoomDirectionLabelsP1[0] = new Label("↑  Nothing");
        this.currentRoomDirectionLabelsP1[0].setId("room-direction-empty-vertical");
        this.currentRoomDirectionLabelsP1[0].setAlignment(Pos.BASELINE_CENTER);
        this.gridPane.add(this.currentRoomDirectionLabelsP1[0], 1, 0);

        this.currentRoomDirectionLabelsP1[1] = new Label("←  Nothing");
        this.currentRoomDirectionLabelsP1[1].setId("room-direction-empty");
        this.currentRoomDirectionLabelsP1[1].setAlignment(Pos.BASELINE_RIGHT);
        this.gridPane.add(this.currentRoomDirectionLabelsP1[1], 0, 1);

        this.currentRoomDirectionLabelsP1[2] = new Label("↓  Nothing");
        this.currentRoomDirectionLabelsP1[2].setId("room-direction-empty-vertical");
        this.currentRoomDirectionLabelsP1[2].setAlignment(Pos.BASELINE_CENTER);
        if (currentPlayeriObjectState[0] == true) {
            this.gridPane.add(this.currentRoomDirectionLabelsP1[2], 1, this.currentInteractLabelsP1.size() + 10);
        } else {
            this.gridPane.add(this.currentRoomDirectionLabelsP1[2], 1, this.currentRoomLabelsP1.size() + 2);
        }

        this.currentRoomDirectionLabelsP1[3] = new Label("Nothing  →");
        this.currentRoomDirectionLabelsP1[3].setId("room-direction-empty");
        this.currentRoomDirectionLabelsP1[3].setAlignment(Pos.BASELINE_LEFT);;
        this.gridPane.add(this.currentRoomDirectionLabelsP1[3], 2, 1);

        int[] localRoomMatrix = this.currentLocation.getRoomMatrix()[room.getRoomID()];
        for (int i = 0; i < localRoomMatrix.length; i++) {
            if (localRoomMatrix[i] == 1) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionLabelsP1[0].setText("↑  "+ roomName);
                this.currentRoomDirectionLabelsP1[0].setId("room-direction-vertical");
            }
            if (localRoomMatrix[i] == 2) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionLabelsP1[1].setText("←  "+ roomName);
                this.currentRoomDirectionLabelsP1[1].setId("room-direction");
            }
            if (localRoomMatrix[i] == 3) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionLabelsP1[2].setText("↓  " + roomName);
                this.currentRoomDirectionLabelsP1[2].setId("room-direction-vertical");
            }
            if (localRoomMatrix[i] == 4) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionLabelsP1[3].setText(roomName +"  →");
                this.currentRoomDirectionLabelsP1[3].setId("room-direction");
            }
        }
    }

    /**
     * Adds and updates the room labels and direction labels for Player 2 in the GUI.
     */
    public void addNextRoomLabelsP2(Room nextRoom) {
        for (Label curLabel : this.currentRoomLabelsP2) {
            this.gridPane.getChildren().remove(curLabel);
        }
        this.gridPane.getChildren().remove(this.currentRoomNameLabels[1]);
        this.currentRoomLabelsP2 = new ArrayList<>();
        Label newLabel = new Label("->  "+ nextRoom.getObjectNames()[0] + "  <-");
        newLabel.setId("selected-i-object");
        this.currentRoomLabelsP2.add(newLabel);
        for (int i = 1; i < nextRoom.getObjectNames().length; i ++) {
            newLabel = new Label(nextRoom.getObjectNames()[i]);
            newLabel.setId("i-object");
            this.currentRoomLabelsP2.add(newLabel);
        }
        for (int i = 0; i < this.currentRoomLabelsP2.size(); i++) {
            this.gridPane.add(this.currentRoomLabelsP2.get(i), 4, i + 2);
        }
        this.currentRoomNameLabels[1] = new Label("-- "+ Game.getInstance().getCurrentPlayerLocation().getLocationName() +" : "+ nextRoom.getRoomName() +" --");
        this.currentRoomNameLabels[1].setId("current-room");
        this.gridPane.add(this.currentRoomNameLabels[1], 4, 1);
        updateRoomDirectionLabelsP2(nextRoom);
    }

    /**
     * Updates the direction labels for Player 2 based on the current room's connections.
     */
    public void updateRoomDirectionLabelsP2(Room room) {
        for (Label curRLabel : this.currentRoomDirectionLabelsP2) {
            this.gridPane.getChildren().remove(curRLabel);
        }
        this.currentRoomDirectionLabelsP2 = new Label[4];
        this.currentRoomDirectionLabelsP2[0] = new Label("↑  Nothing");
        this.currentRoomDirectionLabelsP2[0].setId("room-direction-empty-vertical");
        this.currentRoomDirectionLabelsP2[0].setAlignment(Pos.BASELINE_CENTER);
        this.gridPane.add(this.currentRoomDirectionLabelsP2[0], 4, 0);

        this.currentRoomDirectionLabelsP2[1] = new Label("←  Nothing");
        this.currentRoomDirectionLabelsP2[1].setId("room-direction-empty");
        this.currentRoomDirectionLabelsP2[1].setAlignment(Pos.BASELINE_RIGHT);
        this.gridPane.add(this.currentRoomDirectionLabelsP2[1], 3, 1);

        this.currentRoomDirectionLabelsP2[2] = new Label("↓  Nothing");
        this.currentRoomDirectionLabelsP2[2].setId("room-direction-empty-vertical");
        this.currentRoomDirectionLabelsP2[2].setAlignment(Pos.BASELINE_CENTER);
        if (currentPlayeriObjectState[1] == true) {
            this.gridPane.add(this.currentRoomDirectionLabelsP2[2], 4, this.currentInteractLabelsP2.size() + 10);
        } else {
            this.gridPane.add(this.currentRoomDirectionLabelsP2[2], 4, this.currentRoomLabelsP2.size() + 2);
        }

        this.currentRoomDirectionLabelsP2[3] = new Label("Nothing  →");
        this.currentRoomDirectionLabelsP2[3].setId("room-direction-empty");
        this.currentRoomDirectionLabelsP2[3].setAlignment(Pos.BASELINE_LEFT);
        this.gridPane.add(this.currentRoomDirectionLabelsP2[3], 5, 1);

        int[] localRoomMatrix = this.currentLocation.getRoomMatrix()[room.getRoomID()];
        for (int i = 0; i < localRoomMatrix.length; i++) {
            if (localRoomMatrix[i] == 1) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionLabelsP2[0].setText("↑  "+ roomName);
                this.currentRoomDirectionLabelsP2[0].setId("room-direction-vertical");
            }
            if (localRoomMatrix[i] == 2) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionLabelsP2[1].setText("←  "+ roomName);
                this.currentRoomDirectionLabelsP2[1].setId("room-direction");
            }
            if (localRoomMatrix[i] == 3) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionLabelsP2[2].setText("↓  " + roomName);
                this.currentRoomDirectionLabelsP2[2].setId("room-direction-vertical");
            }
            if (localRoomMatrix[i] == 4) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionLabelsP2[3].setText(roomName +"  →");
                this.currentRoomDirectionLabelsP2[3].setId("room-direction");
            }
        }
    }

    /**
     * Adds the next room's interactive object strings for Player 1 in CLI mode.
     */
    public void addNextRoomStringsP1(Room nextRoom) {
        this.currentPlayeriObjectState[0] = false;
        this.currentRoomIObjectsP1 = new ArrayList<>();
        String newString = "->  "+ nextRoom.getObjectNames()[0] + "  <-";
        this.currentRoomIObjectsP1.add(newString);

        for (int i = 1; i < nextRoom.getObjectNames().length; i ++) {
            newString = nextRoom.getObjectNames()[i];
            this.currentRoomIObjectsP1.add(newString);
        }

        this.currentRoomNamesCLI[0] = "-- "+ nextRoom.getRoomName() +" --";
        updateRoomDirectionsP1(nextRoom);
    }

    /**
     * Updates the CLI direction strings for Player 1.
     */
    public void updateRoomDirectionsP1(Room room) {
        this.currentRoomDirectionsP1 = new String[4];
        this.currentRoomDirectionsP1[0] = "-W-  Nothing";
        this.currentRoomDirectionsP1[1] = "-A-  Nothing";
        this.currentRoomDirectionsP1[2] = "-S-  Nothing";
        this.currentRoomDirectionsP1[3] = "-D-  Nothing";

        int[] localRoomMatrix = this.currentLocation.getRoomMatrix()[room.getRoomID()];
        for (int i = 0; i < localRoomMatrix.length; i++) {
            if (localRoomMatrix[i] == 1) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionsP1[0] = "-W-  "+ roomName;
            }
            if (localRoomMatrix[i] == 2) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionsP1[1] = "-A-  "+ roomName;
            }
            if (localRoomMatrix[i] == 3) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionsP1[2] = "-S-  " + roomName;
            }
            if (localRoomMatrix[i] == 4) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionsP1[3] = "-D-  " + roomName;
            }
        }
    }

    /**
     * Adds the next room's interactive object strings for Player 2 in CLI mode.
     */
    public void addNextRoomStringsP2(Room nextRoom) {
        this.currentPlayeriObjectState[1] = false;
        this.currentRoomIObjectsP2 = new ArrayList<>();
        String newString = "->  "+ nextRoom.getObjectNames()[0] + "  <-";
        this.currentRoomIObjectsP2.add(newString);

        for (int i = 1; i < nextRoom.getObjectNames().length; i ++) {
            newString = nextRoom.getObjectNames()[i];
            this.currentRoomIObjectsP2.add(newString);
        }

        this.currentRoomNamesCLI[1] = "-- "+ nextRoom.getRoomName() +" --";
        updateRoomDirectionsP2(nextRoom);
    }

    /**
     * Updates the CLI direction strings for Player 2.
     */
    public void updateRoomDirectionsP2(Room room) {
        this.currentRoomDirectionsP2 = new String[4];
        this.currentRoomDirectionsP2[0] = "-I-  Nothing";
        this.currentRoomDirectionsP2[1] = "-J-  Nothing";
        this.currentRoomDirectionsP2[2] = "-K-  Nothing";
        this.currentRoomDirectionsP2[3] = "-L-  Nothing";

        int[] localRoomMatrix = this.currentLocation.getRoomMatrix()[room.getRoomID()];
        for (int i = 0; i < localRoomMatrix.length; i++) {
            if (localRoomMatrix[i] == 1) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionsP2[0] = "-I-  "+ roomName;
            }
            if (localRoomMatrix[i] == 2) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionsP2[1] = "-J-  "+ roomName;
            }
            if (localRoomMatrix[i] == 3) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionsP2[2] = "-K-  " + roomName;
            }
            if (localRoomMatrix[i] == 4) {
                String roomName = currentLocation.getRooms()[i].getRoomName();
                this.currentRoomDirectionsP2[3] = "-L-  " + roomName;
            }
        }
    }

    /**
     * Displays dialogue for Player 1 in the GUI, including choices and highlights.
     */
    public void writeDialougeP1(String objectName, String dialouge, int span, String[] choices, int selected) {
        for (Label curLabel : this.currentRoomLabelsP1) {
            this.gridPane.getChildren().remove(curLabel);
        }
        for (Label curILabel : this.currentInteractLabelsP1) {
            this.gridPane.getChildren().remove(curILabel);
        }
        int offset = 1;
        this.currentInteractLabelsP1 = new ArrayList<>();
        this.currentInteractLabelsP1.add(new Label("- " + objectName + " -"));
        this.currentInteractLabelsP1.get(0).setId("current-room");
        this.gridPane.add(this.currentInteractLabelsP1.get(0), 1, 2);
        if (span > 0) {
            this.currentInteractLabelsP1.add(new Label(dialouge));
            this.currentInteractLabelsP1.get(1).setId("dialouge");
            this.currentInteractLabelsP1.get(1).setTextAlignment(TextAlignment.JUSTIFY);
            this.gridPane.add(this.currentInteractLabelsP1.get(1), 1, 3, 1, span);
            offset += 1;
        }
        Label newLabel;
        for (int i = 0; i < choices.length; i++) {
            newLabel = new Label(choices[i]);
            if (i == selected) {
                newLabel.setId("selected-i-object");
            } else {
                newLabel.setId("i-object");
            }
            this.currentInteractLabelsP1.add(newLabel);
        }
        for (int i = offset; i < this.currentInteractLabelsP1.size(); i++) {
            this.gridPane.add(this.currentInteractLabelsP1.get(i), 1, i + (span+1));
        }
        this.updateRoomDirectionLabelsP1(Game.getInstance().getCurrentPlayerRoom()[0]);
    }

    /**
     * Displays dialogue for Player 2 in the GUI, including choices and highlights.
     */
    public void writeDialougeP2(String objectName, String dialouge, int span, String[] choices, int selected) {
        for (Label curLabel : this.currentRoomLabelsP2) {
            this.gridPane.getChildren().remove(curLabel);
        }
        for (Label curILabel : this.currentInteractLabelsP2) {
            this.gridPane.getChildren().remove(curILabel);
        }
        int offset = 1;
        this.currentInteractLabelsP2 = new ArrayList<>();
        this.currentInteractLabelsP2.add(new Label("- " + objectName + " -"));
        this.currentInteractLabelsP2.get(0).setId("current-room");
        this.gridPane.add(this.currentInteractLabelsP2.get(0), 4, 2);
        if (span > 0) {
            this.currentInteractLabelsP2.add(new Label(dialouge));
            this.currentInteractLabelsP2.get(1).setId("dialouge");
            this.currentInteractLabelsP2.get(1).setTextAlignment(TextAlignment.JUSTIFY);
            this.gridPane.add(this.currentInteractLabelsP2.get(1), 4, 3, 1, span);
            offset += 1;
        }
        Label newLabel;
        for (int i = 0; i < choices.length; i++) {
            newLabel = new Label(choices[i]);
            if (i == selected) {
                newLabel.setId("selected-i-object");
            } else {
                newLabel.setId("i-object");
            }
            this.currentInteractLabelsP2.add(newLabel);
        }
        for (int i = offset; i < this.currentInteractLabelsP2.size(); i++) {
            this.gridPane.add(this.currentInteractLabelsP2.get(i), 4, i + (span+1));
        }
        this.updateRoomDirectionLabelsP2(Game.getInstance().getCurrentPlayerRoom()[1]);
    }

    /**
     * Displays dialogue for Player 1 in CLI mode.
     */
    public void writeDialougeCLIP1(String objectName, String dialouge, int span, String[] choices) {
        this.currentPlayeriObjectState[0] = true;
        this.removeIObjectLabelsP1();
        this.currentInteractStringsP1 = new ArrayList<>();
        this.currentInteractStringsP1.add("- " + objectName + " -");
        if (span > 0) {
            this.currentInteractStringsP1.add(dialouge);
        }
        for (int i = 0; i < choices.length; i++) {
            this.currentInteractStringsP1.add(choices[i]);
        }
    }

    /**
     * Displays dialogue for Player 2 in CLI mode.
     */
    public void writeDialougeCLIP2(String objectName, String dialouge, int span, String[] choices) {
        this.currentPlayeriObjectState[1] = true;
        this.removeIObjectLabelsP2();
        this.currentInteractStringsP2 = new ArrayList<>();
        this.currentInteractStringsP2.add("- " + objectName + " -");
        if (span > 0) {
            this.currentInteractStringsP2.add(dialouge);
        }
        for (int i = 0; i < choices.length; i++) {
            this.currentInteractStringsP2.add(choices[i]);
        }
    }

    /**
     * Removes all interactive object labels for Player 1 from the GUI.
     */
    public void removeIObjectLabelsP1() {
        for (Label curILabel: this.currentInteractLabelsP1) {
            this.gridPane.getChildren().remove(curILabel);
        }
    }

    /**
     * Removes all interactive object labels for Player 2 from the GUI.
     */
    public void removeIObjectLabelsP2() {
        for (Label curILabel: this.currentInteractLabelsP2) {
            this.gridPane.getChildren().remove(curILabel);
        }
    }

    /**
     * Outputs the current CLI state for both players to the console.
     */
    public void outputCLI() {
        String commandLineString = "\n\n";
        commandLineString += this.currentRoomNamesCLI[0] +"\n";
        for (String roomDirection : this.currentRoomDirectionsP1) {
            commandLineString += roomDirection + "   ";
        }
        commandLineString += "\n";
        if (this.currentPlayeriObjectState[0] == false) {
            for (int i = 0; i < this.currentRoomIObjectsP1.size(); i++) {
                commandLineString += this.currentRoomIObjectsP1.get(i) + "\n";
            }
        } else {
            for (int i = 0; i < this.currentInteractStringsP1.size(); i++) {
                commandLineString += this.currentInteractStringsP1.get(i) + "\n";
            }
        }
        commandLineString += "-------\n";
        commandLineString += this.currentRoomNamesCLI[1] +"\n";
        for (String roomDirection : this.currentRoomDirectionsP2) {
            commandLineString += roomDirection + "   ";
        }
        commandLineString += "\n";
        if (this.currentPlayeriObjectState[1] == false) {
            for (int i = 0; i < this.currentRoomIObjectsP2.size(); i++) {
                commandLineString += this.currentRoomIObjectsP2.get(i) + "\n";
            }
        } else {
            for (int i = 0; i < this.currentInteractStringsP2.size(); i++) {
                commandLineString += this.currentInteractStringsP2.get(i) + "\n";
            }
        }
        System.out.println(commandLineString);
    }

    /**
     * JavaFX application entry point. Prompts for CLI or GUI mode and starts the game.
     */
    @Override
    public void start(Stage primaryStage) {
        this.currentLocation = Game.getInstance().gameSetup(this);

        Scanner input = new Scanner(System.in);
        System.out.print("Would you like to use a Command Line Interface? (Y/N): ");
        String move = input.nextLine();

        if (move.toUpperCase().equals("Y")) {
            this.startCLI();
        } else {
            this.stage = primaryStage;
            this.setupGridPane();
            this.addNextRoomLabelsP1(this.currentLocation.getRooms()[0]);
            this.addNextRoomLabelsP2(this.currentLocation.getRooms()[0]);
            primaryStage.addEventFilter(KeyEvent.KEY_PRESSED,new P1CustomEventFilter(this));
            primaryStage.addEventFilter(KeyEvent.KEY_PRESSED,new P2CustomEventFilter(this));
            Scene scene = new Scene(this.gridPane,500, 300);
            scene.getStylesheets().add(getClass().getResource("SubTextStyle.css").toExternalForm());
            this.stage.setScene(scene);
            this.stage.setTitle("SubText");
            this.stage.show();
        }
    }

    /**
     * Starts the CLI game loop.
     */
    public void startCLI() {
        this.addNextRoomStringsP1(this.currentLocation.getRooms()[0]);
        this.addNextRoomStringsP2(this.currentLocation.getRooms()[0]);
        Game.getInstance().setIsCLI(true);
        ManualEventFilter CLIEventFilter = new ManualEventFilter(this);
        while (Game.getInstance().getGameComplete() == false) {
            CLIEventFilter.handle();
        }
    }

    /**
     * Sets up the main grid pane for the GUI, sizing and aligning it to the screen.
     */
    public void setupGridPane () {
        this.gridPane = new GridPane();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        this.stage.setX(screenBounds.getMinX());
        this.stage.setY(screenBounds.getMinY());
        this.stage.setWidth(screenBounds.getWidth());
        this.stage.setHeight(screenBounds.getHeight());
        this.gridPane.setMinSize(screenBounds.getWidth(), screenBounds.getHeight());
        this.gridPane.setPadding(new Insets(10, 10, 10, 10));
        this.gridPane.setVgap(5); 
        this.gridPane.setHgap(5);
        this.gridPane.setAlignment(Pos.CENTER);
    }

    /**
     * Placeholder for test code.
     */
    public void testCode() {
        return;
    }
    
    /**
     * Displays an alert scene with a button to return to the main game.
     */
    public void alert(){
        Label alertLabel = new Label("ALERT!!!");
        Button alertButton = new Button("Remove Alert");

        VBox alertLayout = new VBox(10, alertLabel, alertButton);
        Scene alertScene = new Scene(alertLayout, 300, 200);
        alertButton.setOnAction(e -> this.start(this.stage));
        this.stage.setScene(alertScene);
    }

    /**
     * Main entry point for launching the application.
     */
    public static void main(String[] args){
        System.out.println("Runs");
        launch(args);
    }
}
