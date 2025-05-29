package com.cw1;

import com.cw1.Objects.AmaltheaQuestDesk;
import com.cw1.Objects.AmaltheaQuestLocker;
import com.cw1.Objects.Bed;
import com.cw1.Objects.Bulkhead;
import com.cw1.Objects.Command;
import com.cw1.Objects.ConamaraMerchant;
import com.cw1.Objects.FlowerMerchant;
import com.cw1.Objects.HydroponicsBasin;
import com.cw1.Objects.JohannesMerchant;
import com.cw1.Objects.Locker;
import com.cw1.Objects.Merchant;
import com.cw1.Objects.Object;
import com.cw1.Objects.Partner;
import com.cw1.Objects.Reactor;
import com.cw1.Objects.Shelf;
import com.cw1.Objects.Window;

/**
 * Factory class for creating Room objects and their interactive objects.
 * Converts integer IDs from location data into specific object instances.
 */
public class RoomFactory {

    /**
     * Creates an array of Room objects based on the provided room setup and names.
     * Each room is populated with interactive objects according to their global IDs.
     *
     * @param roomSet   2D array where each sub-array contains the global object IDs for a room's objects.
     * @param roomNames Array of room names, one for each room.
     * @return Array of constructed Room objects with their interactive objects.
     */
    public static Room[] createRoomList(int[][] roomSet, String[] roomNames) {
       Room[] returningRooms = new Room[roomSet.length];
       Object[] iObjects;
       for (int i = 0; i < roomSet.length; i++) {
            iObjects = new Object[roomSet[i].length];
            for (int j = 0; j < roomSet[i].length; j++) {
                int targetObjectGID = roomSet[i][j];
                // Instantiate the correct object type based on its global ID
                switch (targetObjectGID) {
                    case 0:
                        iObjects[j] = new Locker();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 1:
                        iObjects[j] = new Reactor();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 2:
                        iObjects[j] = new Command();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 3:
                        iObjects[j] = new Window();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 4:
                        iObjects[j] = new Bulkhead();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 5:
                        iObjects[j] = new Bed();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 6:
                        iObjects[j] = new Partner();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 7:
                        iObjects[j] = new ConamaraMerchant();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 8:
                        iObjects[j] = new AmaltheaQuestDesk();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 9:
                        iObjects[j] = new AmaltheaQuestLocker();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 10:
                        iObjects[j] = new Shelf();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 11:
                        iObjects[j] = new JohannesMerchant();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 12:
                        iObjects[j] = new Merchant();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 13:
                        iObjects[j] = new FlowerMerchant();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    case 14:
                        iObjects[j] = new HydroponicsBasin();
                        iObjects[j].setLocalObjectID(j);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown TOGID int: " + targetObjectGID);
                };
            }
            // Create the Room with its ID, name, and interactive objects
            returningRooms[i] = new Room(i, roomNames[i], iObjects);
       }
        return returningRooms;
    }
}