package com.cw1;

public class RoomFactory {

    public static Room[] createRoomList(int[][] roomSet, String[] roomNames) {
       Room[] returningRooms = new Room[roomSet.length];
       Object[] iObjects;
       for (int i = 0; i < roomSet.length; i++) {
            iObjects = new Object[roomSet[i].length];
            for (int j = 0; j < roomSet[i].length; j++) {
                int targetObjectGID = roomSet[i][j];
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
            returningRooms[i] = new Room(i, roomNames[i], iObjects);
       }
        return returningRooms;
    }
}