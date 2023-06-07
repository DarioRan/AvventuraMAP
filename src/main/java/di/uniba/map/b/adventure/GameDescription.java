package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.Room;
import di.uniba.map.b.adventure.type.TimerListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Classe astratta che rappresenta il gioco.
 */
public abstract class GameDescription {

    /**
     * Lista di stanze.
     */
    private final List<Room> rooms = new ArrayList<>();

    /**
     * Lista di comandi.
     */
    private final List<Command> commands = new ArrayList<>();

    /**
     * Lista di oggetti nell'inventario.
     */
    private  List<AdvObject> inventory = new ArrayList<>();

    /**
     * Lista di oggetti nel gioco.
     */
    private  List<AdvObject> objectsInGame = new ArrayList<>();

    /**
     * Stanza corrente.
     */
    private Room currentRoom;

    /**
     * Timer del gioco.
     */
    private TimerListener timer;

    /**
     * getter della lista di stanze
     * @return lista di stanze
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Metodo che filtra le stanze in base ad un predicato
     * @param predicate predicato
     * @return lista di stanze filtrate
     */
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

    /**
     * getter della lista di comandi
     * @return lista di comandi
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Getter della staza corrente
     * @return stanza corrente
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Setter della stanza corrente
     * @param currentRoom stanza corrente
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Setter della lista di oggetti nell'inventario
     * @param list_obj lista di oggetti nell'inventario
     */
    public void setInventory(List<AdvObject> list_obj)
    {
        this.inventory=list_obj;
    }

    /**
     * Setter del timer del gioco
     * @param timer timer del gioco
     */
    public void setTimer(TimerListener timer) {
        this.timer = timer;
    }

    /**
     * Getter del timer del gioco
     * @return  timer del gioco
     */
    public TimerListener getTimer() {
        return timer;
    }

    /**
     * Getter della lista di oggetti nel gioco
     * @return lista di oggetti nel gioco
     */
    public List<AdvObject> getListObjects() {
        return objectsInGame;
    }

    /**
     * Metodo che filtra gli oggetti in base ad un predicato
     * @param predicate predicato
     * @return lista di oggetti filtrati
     */
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

    /**
     * Getter della lista di oggetti nell'inventario
     * @return lista di oggetti nell'inventario
     */
    public List<AdvObject> getInventory() {
        return inventory;
    }

    /**
     * Metodo astratto che inizializza il gioco
     * @throws Exception eccezione
     */
    public abstract void init() throws Exception;

    /**
     * Metodo astratto che restituisce la prossima mossa
     * @param p parser output
     * @return prossima mossa
     */
    public abstract String nextMove(ParserOutput p);

}
