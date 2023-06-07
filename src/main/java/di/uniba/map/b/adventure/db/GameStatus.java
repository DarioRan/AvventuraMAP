package di.uniba.map.b.adventure.db;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameStatus implements Serializable {

    String username;
    Integer lastRoom_id;
    //an array list of the ids of the object in the inventory
    List<Integer> inventory;

    LocalDateTime time;



    public GameStatus(String username, Integer lastRoomId, List<Integer> inventoryIds, LocalDateTime time){
        this.username = username;
        this.lastRoom_id = lastRoomId;
        this.inventory = inventoryIds;
        this.time = time;

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

    public LocalDateTime getTime(){
        return time;
    }

    public void setTime(LocalDateTime time){
        this.time = time;
    }


}