/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.PickableObject;
import di.uniba.map.b.adventure.type.Room;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author pierpaolo
 */
public abstract class GameDescription {

    private final List<Room> rooms = new ArrayList<>();

    private final List<Command> commands = new ArrayList<>();

    private  List<AdvObject> inventory = new ArrayList<>();

    private  List<AdvObject> objectsInGame = new ArrayList<>();

    private Room currentRoom;

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Room> filterRoom(Predicate<Room> predicate)
    {
        List<Room> listRoom = new ArrayList<>();
        for(Room room : rooms)
        {
            if (predicate.test(room))
            {
                listRoom.add(room);
            }
        }
        return listRoom;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setInventory(List<AdvObject> list_obj)
    {
        this.inventory=list_obj;
    }


    public List<AdvObject> getListObjects() {
        return objectsInGame;
    }
    public List<AdvObject> filterObjects(Predicate<AdvObject> predicate)
    {
        List<AdvObject> listObject = new ArrayList<>();
        for(AdvObject obj : objectsInGame)
        {
            if (predicate.test(obj))
            {
                listObject.add(obj);
            }
        }
        return listObject;
    }



    public List<AdvObject> getInventory() {
        return inventory;
    }

    public abstract void init() throws Exception;

    public abstract String nextMove(ParserOutput p);

}
