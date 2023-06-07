package di.uniba.map.b.adventure.db;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameStatus implements Serializable {

    /**
     * Stringa che rappresenta il nome utente.
     */
    private String username;
    /**
     * Integer che rappresenta l'ultima stanza in cui si trovava l'utente.
     */
    private Integer lastRoom_id;
    /**
     * Lista di Integer che rappresenta gli oggetti che l'utente ha in inventario.
     */
    private List<Integer> inventory;
    /**
     * LocalDateTime che rappresenta l'ora e la data in cui l'utente ha salvato la partita.
     */
    private LocalDateTime time;


    /**
     * Costruttore della classe GameStatus.
     * @param username nome utente.
     * @param lastRoomId ultima stanza in cui si trovava l'utente.
     * @param inventoryIds lista di oggetti in inventario.
     * @param time tempo di salvataggio.
     */
    public GameStatus(String username, Integer lastRoomId, List<Integer> inventoryIds, LocalDateTime time) {
        this.username = username;
        this.lastRoom_id = lastRoomId;
        this.inventory = inventoryIds;
        this.time = time;
    }

    /**
     * getter per username.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter per ultima stanza in cui si trovava l'utente.
     * @return lastRoom_id.
     */
    public Integer getLastRoomId() {
        return lastRoom_id;
    }

    /**
     * getter per la lista di oggetti in inventario.
     * @return lista degli id degli oggetti in inventario.
     */
    public List<Integer> getInventoryIds() {
        return inventory;
    }

    /**
     * getter per la lista di oggetti in inventario sotto forma di stringa.
     * @return stringa degli id degli oggetti in inventario.
     */
    public String getInventoryIdsAsString() {
        String string = "";
        for(Integer id: inventory )
        {
            string = string + id.toString() + ",";
        }
        return string;
    }

    /**
     * setter per username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * setter per ultima stanza in cui si trovava l'utente.
     */
    public void setLastRoomId(Integer lastRoom) {
        this.lastRoom_id = lastRoom;
    }

    /**
     * setter per la lista di oggetti in inventario.
     */
    public void setInventoryIds(ArrayList<Integer> inventory) {
        this.inventory = inventory;
    }

    /**
     * setter per il tempo di salvataggio.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * setter per il tempo.
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
