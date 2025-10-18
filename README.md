Module Code:  CS1OP  
Assignment report Title:   SubText
Actual hrs spent for the assignment: 58  
Which Artificial Intelligence tools used: Github Co-Pilot in VsCode

## Introduction
This is a 2-player text-based adventure titled SubText. 
This project demonstrates object-oriented programming (Aggregation, Composition, Inheiritance, etc..), event-driven design, and the use of both graphical and command-line interfaces for interactive gameplay.

**Key Features**
1. Dual Interface: Playable via a modern JavaFX GUI or a classic Command Line Interface (CLI) which is chosen on start-up.
2. Two-Player Support: Both players can interact with the world simultaneously, each with their own controls and  their own sections on each user interface.
3. Modular Game World: The game world is structured into Locations, Rooms, and a variety of interactive Objects, each with unique behaviors and dialogue.
4. Quest System: NPCs (like Merchants) offer quests and rewards, requiring players to explore, interact, and cooperate.
5. Event Handling: Custom event filters for both players map keyboard input to in-game actions, supporting smooth and responsive gameplay.
6. Logging: All major player actions (interactions, quest acceptance, item pickups) are logged for debugging and demonstration purposes.
7. Extensible Design: Easily add new rooms, objects, or quests by extending the provided classes and updating configuration files (New Locations may require updates to the Location Matrix inside Game file).

**Core Components**  
GraphicalUserInterface: Manages all GUI and CLI display logic, user input, and scene transitions.  
Game: Singleton class that maintains game state, player progress, and orchestrates all core logic.  
Room & Location: Define the structure of the game world, loading data from configuration files.  
Object & Subclasses: Abstract base for all interactive objects (e.g., Locker, Reactor, Merchant, Partner, Shelf), each with custom interaction logic.  
RoomFactory: Factory pattern for instantiating rooms and their objects based on configuration data.  
Event Filters: Separate classes for handling keyboard input for each player in both GUI and CLI modes.  

## Getting Started
Download All Associated: All the files can be found in this git repository.  
Run the Game: Find the SubText Shortcut (links directly to cs1op-cw1 batch file), which can be moved as well, and double-click.  
Choose CLI or GUI: Decide between a simpler Command Line Interface or the more complex GUI.  
Explore and Play: Whichever way you decide, the controls are the same. Look below for controls.  

**Controls**  
Player1:  
    Q. Left Interact Button and is used to cycle between choices in most menus.  
    E. Right Interact Button and is used to interact or exit objects.  
    W, A, S, D. The Directional Buttons and is used to move between rooms. Can sometimes be used for extra interaction inside objects (Example: W is used to confirm choices.)  
Player2:  
    U. left Interact Button and is used to cycle between choices in most menus.  
    O. Right Interact Button and is used to interact or exit objects.  
    I, J, K, L. The Directional Buttons and is used to move between rooms. Can sometimes be used for extra interaction inside objects (Example: I is used to confirm choices.)  

## Design
**Simple Class Diagram in Mermaid Syntax:**
    
    class Game {
        -boolean[] questAccepted
        -int[] items
        -Location currentPlayerLocation
        -Room[] currentPlayerRoom
        -boolean isCLI
        -Log log
        +getInstance()
        +setIsCLI(boolean)
        +setItems(int, int)
        +setQuestAccepted(int, boolean)
        +reloadRoom(int)
        +getGUI()
        +getLog()
        +gameSetup(GraphicalUserInterface)
    }

    class GraphicalUserInterface {
        -Stage stage
        -GridPane gridPane
        -Location currentLocation
        +start(Stage)
        +startCLI()
        +setupGridPane()
        +addNextRoomLabelsP1(Room)
        +addNextRoomLabelsP2(Room)
        +outputCLI()
        +writeDialougeP1(...)
        +writeDialougeP2(...)
        +writeDialougeCLIP1(...)
        +writeDialougeCLIP2(...)
    }

    class Location {
        -int locID
        -String locName
        -Room[] rooms
        -int[][] roomMatrix
        +getRooms()
        +getLocationName()
        +getRoomMatrix()
    }

    class Room {
        -int roomID
        -String roomName
        -Object[] iObjects
        -String[] objectNames
        +getiObjects()
        +getObjectNames()
        +getRoomName()
    }

    class RoomFactory {
        +createRoomList(int[][], String[]): Room[]
    }

    class Object {
        -int localObjectID
        -int state
        +getState()
        +getLocalObjectID()
        +setLocalObjectID(int)
        +interact(GraphicalUserInterface, int)
        +getObjectName()
        +getCurrentPlayer()
        +Up()
        +Left()
        +Down()
        +Right()
        +LInteract()
        +RInteract()
    }

    class Locker
    class Reactor
    class Merchant
    class Partner
    class Shelf
    class HydroponicsBasin
    class Bulkhead
    class Command

    Object <|-- Locker
    Object <|-- Reactor
    Object <|-- Merchant
    Object <|-- Partner
    Object <|-- Shelf
    Object <|-- HydroponicsBasin
    Object <|-- Bulkhead
    Object <|-- Command

    RoomFactory ..> Room
    Room ..> Object
    Location ..> Room
    GraphicalUserInterface ..> Location
    GraphicalUserInterface ..> Room
    GraphicalUserInterface ..> Game
    Game --> Log

    class ManualEventFilter
    class P1CustomEventFilter
    class P2CustomEventFilter

    GraphicalUserInterface ..> ManualEventFilter
    GraphicalUserInterface ..> P1CustomEventFilter
    GraphicalUserInterface ..> P2CustomEventFilter

## Assumptions

1. Java is installed.
2. Their computer can run JavaFX.
3. The storage space required can be accessed.
4. They have the ability to run the .bat file to start the program.
5. User can use a Command Line Interface.
6. User has a keyboard or an input device acting as a keyboard.
