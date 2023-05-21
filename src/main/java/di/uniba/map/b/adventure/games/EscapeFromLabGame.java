package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;

import java.io.PrintStream;
import java.util.Iterator;

public class EscapeFromLabGame extends GameDescription {

    /**
     * Inizializza i comandi del gioco
     */
    private void initCommands(){
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv"});
        getCommands().add(iventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva"});
        getCommands().add(push);
    }

    /**
     * Inizializza le stanze del gioco e definisce la mappa
     */
    private void initRooms(){
        Room room1 = new Room(1, "Atrio", "Sei nell'atrio del laboratorio");
        Room room2 = new Room(2, "Laboratorio", "Sei nel laboratorio");
        Room room3 = new Room(3, "Ufficio", "Sei nell'ufficio");
        Room room4 = new Room(4, "Magazzino", "Sei nel magazzino");
        Room room5 = new Room(5, "Sala riunioni", "Sei nella sala riunioni");
        Room room6 = new Room(6, "Sala server", "Sei nella sala server");
        Room room7 = new Room(7, "Sala server", "Sei nella sala server");
        Room room8 = new Room(8, "Sala server", "Sei nella sala server");
        Room room9 = new Room(9, "Sala server", "Sei nella sala server");
        Room room10 = new Room(10, "Sala server", "Sei nella sala server");
        Room room11 = new Room(11, "Sala server", "Sei nella sala server");
        Room room12 = new Room(12, "Sala server", "Sei nella sala server");
        Room room13 = new Room(13, "Sala server", "Sei nella sala server");
        Room room14 = new Room(14, "Sala server", "Sei nella sala server");
        Room room15 = new Room(15, "Sala server", "Sei nella sala server");
        Room room16 = new Room(16, "Sala server", "Sei nella sala server");
        Room room17 = new Room(17, "Sala server", "Sei nella sala server");
        Room room18 = new Room(18, "Sala server", "Sei nella sala server");
        Room room19 = new Room(19, "Sala server", "Sei nella sala server");
        Room room20 = new Room(20, "Sala server", "Sei nella sala server");
        Room room21 = new Room(21, "Sala server", "Sei nella sala server");
        Room room221 = new Room(221, "Sala server", "Sei nella sala server");
        Room room222 = new Room(222, "Sala server", "Sei nella sala server");
        Room room23 = new Room(23, "Sala server", "Sei nella sala server");
        Room room24 = new Room(24, "Sala server", "Sei nella sala server");
        Room room25 = new Room(25, "Sala server", "Sei nella sala server");
        Room room26 = new Room(26, "Sala server", "Sei nella sala server");
        Room room27 = new Room(27, "Sala server", "Sei nella sala server");
        Room room28 = new Room(28, "Sala server", "Sei nella sala server");
        Room room29 = new Room(29, "Sala server", "Sei nella sala server");
        Room room30 = new Room(30, "Sala server", "Sei nella sala server");
        Room room31 = new Room(31, "Sala server", "Sei nella sala server");
        Room room32 = new Room(32, "Sala server", "Sei nella sala server");
        Room room33 = new Room(33, "Sala server", "Sei nella sala server");
        Room room34 = new Room(34, "Sala server", "Sei nella sala server");
        Room room35 = new Room(35, "Sala server", "Sei nella sala server");
        Room room36 = new Room(36, "Sala server", "Sei nella sala server");

        room1.setNorth(room10);
        room1.setEast(room5);
        room1.setWest(room2);

        room2.setNorth(room3);
        room2.setSouth(room1);
        room2.setWest(room4);

        room3.setSouth(room2);

        room4.setSouth(room2);

        room5.setNorth(room8);
        room5.setSouth(room1);
        room5.setEast(room7);
        room5.setWest(room6);

        room6.setSouth(room5);

        room7.setSouth(room5);

        room8.setNorth(room9);
        room8.setSouth(room5);

        room9.setSouth(room8);

        room10.setNorth(room17);
        room10.setSouth(room1);
        room10.setWest(room11);

        room11.setSouth(room10);
        room11.setNorth(room12);

        room12.setSouth(room11);
        room12.setNorth(room13);

        room13.setNorth(room15);
        room13.setSouth(room12);
        room13.setEast(room16);
        room13.setWest(room14);

        room14.setSouth(room13);

        room15.setSouth(room13);

        room16.setSouth(room13);

        room17.setNorth(room18);
        room17.setSouth(room10);
        room17.setEast(room23);
        room17.setWest(room19);

        room18.setSouth(room17);

        room19.setNorth(room20);
        room19.setSouth(room17);

        room20.setNorth(room21);
        room20.setSouth(room19);
        room20.setEast(room17);
        room20.setWest(room221);

        room21.setSouth(room20);

        room221.setSouth(room20);

        room222.setSouth(room20);

        room23.setNorth(room32);
        room23.setEast(room24);
        room23.setSouth(room17);

        room24.setNorth(room26);
        room24.setSouth(room23);
        room24.setWest(room25);

        room25.setSouth(room24);

        room26.setNorth(room27);
        room26.setSouth(room24);

        room27.setSouth(room26);
        room27.setEast(room29);
        room27.setWest(room28);

        room28.setSouth(room27);

        room29.setNorth(room30);
        room29.setSouth(room27);

        room30.setNorth(room31);
        room30.setSouth(room29);

        room31.setSouth(room30);

        room32.setNorth(room33);
        room32.setSouth(room23);

        room33.setNorth(room34);
        room33.setSouth(room32);

        room34.setNorth(room35);
        room34.setEast(room36);
        room34.setSouth(room33);

        room35.setSouth(room34);

        room36.setSouth(room34);

        getRooms().add(room1);
        getRooms().add(room2);
        getRooms().add(room3);
        getRooms().add(room4);
        getRooms().add(room5);
        getRooms().add(room6);
        getRooms().add(room7);
        getRooms().add(room8);
        getRooms().add(room9);
        getRooms().add(room10);
        getRooms().add(room11);
        getRooms().add(room12);
        getRooms().add(room13);
        getRooms().add(room14);
        getRooms().add(room15);
        getRooms().add(room16);
        getRooms().add(room17);
        getRooms().add(room18);
        getRooms().add(room19);
        getRooms().add(room20);
        getRooms().add(room21);
        getRooms().add(room221);
        getRooms().add(room222);
        getRooms().add(room23);
        getRooms().add(room24);
        getRooms().add(room25);
        getRooms().add(room26);
        getRooms().add(room27);
        getRooms().add(room28);
        getRooms().add(room29);
        getRooms().add(room30);
        getRooms().add(room31);
        getRooms().add(room32);
        getRooms().add(room33);
        getRooms().add(room34);
        getRooms().add(room35);
        getRooms().add(room36);

        setCurrentRoom(room1);
    }
    @Override
    public void init() throws Exception {
        initCommands();
        initRooms();
    }

    private void goNorth(boolean noroom, boolean move){
        if (getCurrentRoom().getNorth() != null) {
            setCurrentRoom(getCurrentRoom().getNorth());
            move = true;
        } else {
            noroom = true;
        }
    }

    private void goSouth(boolean noroom, boolean move){
        if (getCurrentRoom().getSouth() != null) {
            setCurrentRoom(getCurrentRoom().getNorth());
            move = true;
        } else {
            noroom = true;
        }
    }

    private void goEast(boolean noroom, boolean move){
        if (getCurrentRoom().getEast() != null) {
            setCurrentRoom(getCurrentRoom().getNorth());
            move = true;
        } else {
            noroom = true;
        }
    }

    private void goWest(boolean noroom, boolean move){
        if (getCurrentRoom().getWest() != null) {
            setCurrentRoom(getCurrentRoom().getNorth());
            move = true;
        } else {
            noroom = true;
        }
    }

    private void checkInventory(PrintStream out){
        out.println("Nel tuo inventario ci sono:");
        for (AdvObject o : getInventory()) {
            out.println(o.getName() + ": " + o.getDescription());
        }
    }

    private void checkRoom(PrintStream out){
        if (getCurrentRoom().getLook() != null) {
            out.println(getCurrentRoom().getLook());
        } else {
            out.println("Non c'è niente di interessante qui.");
        }
    }

    private void pickUpObject(ParserOutput p, PrintStream out){
        if (p.getObject() != null) {
            if (p.getObject().isPickupable()) {
                getInventory().add(p.getObject());
                getCurrentRoom().getObjects().remove(p.getObject());
                out.println("Hai raccolto: " + p.getObject().getDescription());
            } else {
                out.println("Non puoi raccogliere questo oggetto.");
            }
        } else {
            out.println("Non c'è niente da raccogliere qui.");
        }
    }

    private void openObject(ParserOutput p, PrintStream out){
        /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
         * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
         * Potrebbe non esssere la soluzione ottimale.
         */
        if (p.getObject() == null && p.getInvObject() == null) {
            out.println("Non c'è niente da aprire qui.");
        } else {
            if (p.getObject() != null) {
                if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                    if (p.getObject() instanceof AdvObjectContainer) {
                        out.println("Hai aperto: " + p.getObject().getName());
                        AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                        if (!c.getList().isEmpty()) {
                            out.print(c.getName() + " contiene:");
                            Iterator<AdvObject> it = c.getList().iterator();
                            while (it.hasNext()) {
                                AdvObject next = it.next();
                                getCurrentRoom().getObjects().add(next);
                                out.print(" " + next.getName());
                                it.remove();
                            }
                            out.println();
                        }
                        p.getObject().setOpen(true);
                    } else {
                        out.println("Hai aperto: " + p.getObject().getName());
                        p.getObject().setOpen(true);
                    }
                } else {
                    out.println("Non puoi aprire questo oggetto.");
                }
            }
            if (p.getInvObject() != null) {
                if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                    if (p.getInvObject() instanceof AdvObjectContainer) {
                        AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                        if (!c.getList().isEmpty()) {
                            out.print(c.getName() + " contiene:");
                            Iterator<AdvObject> it = c.getList().iterator();
                            while (it.hasNext()) {
                                AdvObject next = it.next();
                                getInventory().add(next);
                                out.print(" " + next.getName());
                                it.remove();
                            }
                            out.println();
                        }
                        p.getInvObject().setOpen(true);
                    } else {
                        p.getInvObject().setOpen(true);
                    }
                    out.println("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                } else {
                    out.println("Non puoi aprire questo oggetto.");
                }
            }
        }
    }

    private void pushObject(ParserOutput p, PrintStream out){
        //ricerca oggetti pushabili
        if (p.getObject() != null && p.getObject().isPushable()) {
            out.println("Hai premuto: " + p.getObject().getName());
            if (p.getObject().getId() == 3) {
                end(out);
            }
        } else if (p.getInvObject() != null && p.getInvObject().isPushable()) {
            out.println("Hai premuto: " + p.getInvObject().getName());
            if (p.getInvObject().getId() == 3) {
                end(out);
            }
        } else {
            out.println("Non ci sono oggetti che puoi premere qui.");
        }
    }
    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean noroom = false;
            boolean move = false;
            if (p.getCommand().getType() == CommandType.NORD) {
                goNorth(noroom, move);
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
                goSouth(noroom, move);
            } else if (p.getCommand().getType() == CommandType.EAST) {
                goEast(noroom, move);
            } else if (p.getCommand().getType() == CommandType.WEST) {
                goWest(noroom, move);
            } else if (p.getCommand().getType() == CommandType.INVENTORY) {
                checkInventory(out);
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
                checkRoom(out);
            } else if (p.getCommand().getType() == CommandType.PICK_UP) {
                pickUpObject(p, out);
            } else if (p.getCommand().getType() == CommandType.OPEN) {
                openObject(p, out);
            } else if (p.getCommand().getType() == CommandType.PUSH) {
               pushObject(p, out);
            }
            if (noroom) {
                out.println("Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...");
            } else if (move) {
                out.println(getCurrentRoom().getName());
                out.println("================================================");
                out.println(getCurrentRoom().getDescription());
            }
        }
    }

    private void end (PrintStream out) {
        out.println("Premi il pulsante del giocattolo e in seguito ad una forte esplosione la tua casa prende fuoco...\ntu e tuoi famigliari cercate invano di salvarvi e venite avvolti dalle fiamme...\nè stata una morte CALOROSA...addio!");
        System.exit(0);
    }
}
