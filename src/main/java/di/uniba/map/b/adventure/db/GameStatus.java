package di.uniba.map.b.adventure.db;

import di.uniba.map.b.adventure.type.Inventory;
import di.uniba.map.b.adventure.type.Room;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {

    String username;
    Integer lastRoom_id;
    //an array list of the ids of the object in the inventory
    List<Integer> inventory;

    public GameStatus(String username, Integer lastRoomId, List<Integer> inventoryIds){
        this.username = username;
        this.lastRoom_id = lastRoomId;
        this.inventory = inventoryIds;
    }

    public String getUsername(){
        return username;
    }

    public Integer getLastRoomId(){
        return lastRoom_id;
    }

    public List<Integer> getInventoryIds(){
        return inventory;
    }

    public String getInventoryIdsAsString(){
        String string="";
        for(Integer id: inventory )
        {
            string=string+id.toString()+",";
        }
        return string;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setLastRoomId(Integer lastRoom){
        this.lastRoom_id = lastRoom;
    }

    public void setInventoryIds(ArrayList<Integer> inventory){
        this.inventory = inventory;
    }

}