package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.Engine;
import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.misc.RoomDesc;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import java.io.IOException;
import java.util.Iterator;
import org.apache.commons.lang3.mutable.MutableBoolean;

/**
 * Classe che implementa il gioco EscapeFromLab.
 * Estende la classe astratta GameDescription.
 */
public class EscapeFromLabGame extends GameDescription {

    /**
     * Oggetto di tipo Engine.
     */
    private Engine engine;
    /**
     * Costruttore della classe EscapeFromLabGame.
     * Chiama il costruttore della classe astratta GameDescription.
     * @throws IOException
     */
    public EscapeFromLabGame() throws IOException {
        super();
    }
    /**
     * Inizializza i comandi del gioco.
     */
    private void initCommands() {
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
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"utilizza","attiva"});
        getCommands().add(use);
        Command turnOn = new Command(CommandType.TURN_ON, "accendi");
        turnOn.setAlias(new String[]{"accendi"});
        getCommands().add(turnOn);
        Command turnOff = new Command(CommandType.TURN_OFF, "spegni");
        turnOff.setAlias(new String[]{"spegni"});
        getCommands().add(turnOff);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{"apri"});
        getCommands().add(open);
        Command load = new Command(CommandType.LOAD_GAME, "LOADGAME");
        load.setAlias(new String[]{"LOADGAME", "loadgame"});
        getCommands().add(load);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva", "blocca"});
        getCommands().add(push);
        Command unlock = new Command(CommandType.UNLOCK, "sblocca");
        unlock.setAlias(new String[]{"sblocca"});
        getCommands().add(unlock);
        Command help = new Command(CommandType.HELP, "help");
        help.setAlias(new String[]{"HELP", "aiuto", "comandi", "help", "istruzioni"});
        getCommands().add(help);
        Command saveGame = new Command(CommandType.SAVE_GAME, "SAVEGAME");
        saveGame.setAlias(new String[]{"SAVEGAME", "savegame"});
        getCommands().add(saveGame);
        Command incrementPbValue = new Command(CommandType.INCREMENT_PB_VALUE, "INCREMENTPBVALUE");
        incrementPbValue.setAlias(new String[]{"INCREMENTPBVALUE", "incrementpbvalue"});
        getCommands().add(incrementPbValue);
        Command getSavedGames = new Command(CommandType.GET_SAVED_GAMES, "GETSAVEDGAMES");
        getSavedGames.setAlias(new String[]{"GETSAVEDGAMES", "getsavedgames"});
        getCommands().add(getSavedGames);
        Command startTimer = new Command(CommandType.START_TIMER, "STARTTIMER");
        startTimer.setAlias(new String[]{"STARTTIMER", "starttimer"});
        getCommands().add(startTimer);
        Command stopTimer = new Command(CommandType.STOP_TIMER, "STOPTIMER");
        stopTimer.setAlias(new String[]{"STOPTIMER", "stoptimer"});
        getCommands().add(stopTimer);
    }
    /**
     * Inizializza le stanze del gioco e definisce la mappa.
     */
    private void initRooms(){
        String descFilePath = "resources/desc.txt";
        String titleFilePath = "resources/titles.txt";
        try {
            RoomDesc desc = new RoomDesc(descFilePath, titleFilePath);
            Room room1 = new Room(1, desc.getTitle(0), desc.getDescription(0));
            Room room2 = new Room(2, desc.getTitle(1), desc.getDescription(1));
            Room room3 = new Room(3, desc.getTitle(2), desc.getDescription(2));
            Room room4 = new Room(4, desc.getTitle(3), desc.getDescription(3));
            Room room5 = new Room(5, desc.getTitle(4), desc.getDescription(4));
            Room room6 = new Room(6, desc.getTitle(5), desc.getDescription(5));
            Room room7 = new Room(7, desc.getTitle(6), desc.getDescription(6));
            Room room8 = new Room(8, desc.getTitle(7), desc.getDescription(7));
            Room room9 = new Room(9, desc.getTitle(8), desc.getDescription(8));
            room9.setAccessible(false);
            Room room10 = new Room(10, desc.getTitle(9), desc.getDescription(9));
            Room room11 = new Room(11, desc.getTitle(10), desc.getDescription(10));
            Room room12 = new Room(12, desc.getTitle(11), desc.getDescription(11));
            Room room13 = new Room(13, desc.getTitle(12), desc.getDescription(12));
            room13.setAccessible(false);
            Room room14 = new Room(14, desc.getTitle(13), desc.getDescription(13));
            Room room15 = new Room(15, desc.getTitle(14), desc.getDescription(14));
            Room room16 = new Room(16, desc.getTitle(15), desc.getDescription(15));
            Room room17 = new Room(17, desc.getTitle(16), desc.getDescription(16));
            Room room18 = new Room(18, desc.getTitle(17), desc.getDescription(17));
            Room room19 = new Room(19, desc.getTitle(18), desc.getDescription(18));
            Room room20 = new Room(20, desc.getTitle(19), desc.getDescription(19));
            Room room21 = new Room(21, desc.getTitle(20), desc.getDescription(20));
            room21.setAccessible(false);
            Room room221 = new Room(221, desc.getTitle(21), desc.getDescription(21));
            room221.setAccessible(false);
            Room room23 = new Room(23, desc.getTitle(22), desc.getDescription(22));
            Room room24 = new Room(24, desc.getTitle(23), desc.getDescription(23));
            Room room25 = new Room(25, desc.getTitle(24), desc.getDescription(24));
            Room room26 = new Room(26, desc.getTitle(25), desc.getDescription(25));
            Room room27 = new Room(27, desc.getTitle(26), desc.getDescription(26));
            Room room28 = new Room(28, desc.getTitle(27), desc.getDescription(27));
            Room room29 = new Room(29, desc.getTitle(28), desc.getDescription(28));
            Room room30 = new Room(30, desc.getTitle(29), desc.getDescription(29));
            Room room31 = new Room(31, desc.getTitle(30), desc.getDescription(30));
            room31.setAccessible(false);
            Room room32 = new Room(32, desc.getTitle(31), desc.getDescription(31));
            Room room33 = new Room(33, desc.getTitle(32), desc.getDescription(32));
            Room room34 = new Room(34, desc.getTitle(33), desc.getDescription(33));
            Room room35 = new Room(35, desc.getTitle(34), desc.getDescription(34));
            Room room36 = new Room(36, desc.getTitle(35), desc.getDescription(35));



            room1.setNorth(room10);
            room1.setEast(room5);
            room1.setWest(room2);

            room2.setNorth(room3);
            room2.setSouth(room1);
            room2.setWest(room4);

            room3.setSouth(room2);

            room4.setSouth(room2);
            room4.setDark(true);

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

            AdvObjectContainer toolbox = new AdvObjectContainer(1, "Cassetta per gli attrezzi", "Una cassetta per gli attrezzi");
            toolbox.setAlias(new String[]{"cassetta", "attrezzi", "cassetta per gli attrezzi"});

            AdvObjectContainer generator = new AdvObjectContainer(11, "Generatore", "Una generatore di energia");
            generator.setAlias(new String[]{"generatore", "generatore di energia"});

            AdvObjectContainer valve = new AdvObjectContainer(18, "Cisterna", "Una cisterna, potrebbe essere spenta o accesa");
            valve.setAlias(new String[]{"cisterna"});

            AdvObjectContainer drawer = new AdvObjectContainer(13, "Cassetto", "Un cassetto, forse contiene qualcosa");
            drawer.setAlias(new String[]{"cassetto", "cassetta", "drawer"});

            AdvObjectContainer wardrobe = new AdvObjectContainer(15, "Armadio", "Un armadio di acciaio.");
            wardrobe.setAlias(new String[]{"armadio", "wardrobe", "armadio di acciaio"});

            AdvObject hammer = new AdvObject(2, "Martello", "Un martello");
            hammer.setAlias(new String[]{"martello", "martello da muratore", "martello da carpentiere", "martello da falegname", "martello da fabbro"});

            AdvObject torch = new AdvObject(3, "Torcia", "Una torcia");
            torch.setAlias(new String[]{"torcia", "lanterna", "lanterna elettrica", "torcia elettrica"});

            AdvObject metalKey = new AdvObject(4, "Chiave", "Una chiave metallica");
            metalKey.setAlias(new String[]{"chiave", "chiave metallica", "chiave di metallo", "chiave di ferro", "chiave ferrea"});

            AdvObject redKeyCard = new AdvObject(5, "RedKeycard", "Una KeyCard rossa");
            redKeyCard.setAlias(new String[]{"redkeycard", "rk", "red keycard"  });

            AdvObject yellowKeyCard = new AdvObject(6, "YellowKeycard", "Una KeyCard gialla");
            yellowKeyCard.setAlias(new String[]{"yellowkeycard", "yk", "yellow keycard"});

            AdvObject paper = new AdvObject(7, "Pezzo di carta", "123..-.-.,,-.721");
            paper.setAlias(new String[]{"carta", "pezzo di carta", "foglio", "foglio di carta", "pezzo di foglio", "pezzo di foglio di carta"});

            AdvObject memo = new AdvObject(8, "Post It", "-.-.--,,loco,,..--.-.");
            memo.setAlias(new String[]{"postit", "memo"});

            AdvObject bracelet = new AdvObject(9, "Bracciale", "-.-11--,..,.,,..11.-.");
            bracelet.setAlias(new String[]{"bracciale", "braccialetto", "bracciale di metallo", "braccialetto di metallo", "bracciale metallico", "braccialetto metallico"});

            AdvObject palmares = new AdvObject(10, "Palmares", "Palmares elettronico, potrebbe essere utile per qualcosa");
            palmares.setAlias(new String[]{"palmares", "palmares elettronico"});

            AdvObject commander = new AdvObject(12, "Telecomando", "Telecomando elettronico, per usarlo dovresti avere la password dell'amministratore");
            commander.setAlias(new String[]{"telecomando", "telecomando elettronico"});

            AdvObject rock = new AdvObject(14, "Roccia", "Una roccia solida");
            rock.setAlias(new String[]{"roccia", "masso", "pietra", "sasso"});

            AdvObject pengold = new AdvObject(16, "Penna d'oro", "Una penna di color oro");
            pengold.setAlias(new String[]{"penna", "penna d'oro", "penna oro", "penna di oro", "penna dorata", "penna color oro"});

            AdvObject tools = new AdvObject(17, "Strumentazione", "Strumentazione tecnica");
            tools.setAlias(new String[]{"strumentazione", "strumentazione tecnica", "strumenti", "strumenti tecnici"});

            AdvObject shutter = new AdvObject(19, "Saracinesca", "Una saracinesca, potrebbe essere aperta o chiusa");
            shutter.setAlias(new String[]{"saracinesca"});
            shutter.setOpenable(true);


            toolbox.setOpenable(true);
            toolbox.setPickupable(false);
            toolbox.add(hammer);
            drawer.setOpenable(true);
            drawer.setPickupable(false);
            drawer.add(paper);
            wardrobe.setOpenable(true);
            wardrobe.setPickupable(false);
            torch.setSwitchable(true);
            palmares.setUsable(true);
            palmares.setLocakble(true);
            palmares.setPassword("12311loco11721");
            commander.setUsable(true);
            commander.setLocakble(true);
            commander.setPassword("3215");
            generator.setOpenable(false);
            generator.setSwitchable(true);
            valve.setOpenable(false);
            valve.setSwitchable(true);
            yellowKeyCard.setPickupable(true);

            getListObjects().add(metalKey);
            getListObjects().add(toolbox);
            getListObjects().add(hammer);
            getListObjects().add(torch);
            getListObjects().add(yellowKeyCard);
            getListObjects().add(redKeyCard);
            getListObjects().add(paper);
            getListObjects().add(memo);
            getListObjects().add(bracelet);
            getListObjects().add(palmares);
            getListObjects().add(commander);
            getListObjects().add(generator);
            getListObjects().add(drawer);
            getListObjects().add(rock);
            getListObjects().add(wardrobe);
            getListObjects().add(pengold);
            getListObjects().add(tools);
            getListObjects().add(valve);
            getListObjects().add(shutter);

            room7.getObjects().add(valve);
            room1.getObjects().add(rock);
            room4.getObjects().add(metalKey);
            room3.getObjects().add(wardrobe);
            room10.getObjects().add(torch);
            room6.getObjects().add(toolbox);
            room9.getObjects().add(generator);
            room9.setKey(hammer);
            room18.getObjects().add(yellowKeyCard);
            room18.getObjects().add(pengold);
            room31.setKey(yellowKeyCard);
            room31.getObjects().add(redKeyCard);
            room14.getObjects().add(drawer);
            room13.getObjects().add(memo);
            room13.setKey(metalKey);
            room15.getObjects().add(palmares);
            room21.setKey(redKeyCard);
            room21.getObjects().add(commander);
            room16.getObjects().add(bracelet);
            room24.getObjects().add(tools);
            room221.getObjects().add(shutter);

        } catch (IOException e) {
            // Gestisci eventuali errori di IO durante la lettura dei file
            e.printStackTrace();
        }
    }
    /**
     * Inizializza i comandi.
     */
    @Override
    public void init() throws Exception {
        initCommands();
        initRooms();
    }

    /**
     * Imposta l'engine del gioco.
     * @param engine l'engine del gioco
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    /**
     * metodo booleano che controlla il movimento del giocatore verso nord
     * @param isKeyNeeded
     */
    private boolean goNorth(final MutableBoolean isKeyNeeded) {
        Room nextroom;
        isKeyNeeded.setFalse();
        if (getCurrentRoom().getNorth() != null) {
            nextroom = getCurrentRoom().getNorth();
            if (nextroom.isAccessible()) {
                setCurrentRoom(nextroom);
                return true;
            } else {
                isKeyNeeded.setTrue();
                if(canAccessRoom(nextroom)){
                    setCurrentRoom(nextroom);
                    return true;
                }
                else {
                    return false;
                }
            }
        } else {
            return false;
        }

    }
    /**
     * metodo booleano che controlla il movimento del giocatore verso sud
     * @param isKeyNeeded
     */
    private boolean goSouth(final MutableBoolean isKeyNeeded){
        Room nextroom;
        isKeyNeeded.setFalse();
        if (getCurrentRoom().getSouth() != null) {
            nextroom = getCurrentRoom().getSouth();
            if (nextroom.isAccessible()) {
                setCurrentRoom(nextroom);
                return true;
            } else {
                isKeyNeeded.setTrue();
                if(canAccessRoom(nextroom)){
                    setCurrentRoom(nextroom);
                    return true;
                }
                else{
                    return false;
                }
            }
        } else {
            return false;
        }
    }
    /**
     * metodo booleano che controlla il movimento del giocatore verso est
     * @param isKeyNeeded
     */
    private boolean goEast(final MutableBoolean isKeyNeeded){
        Room nextroom;
        isKeyNeeded.setFalse();
        if (getCurrentRoom().getEast() != null) {
            nextroom = getCurrentRoom().getEast();
            if (nextroom.isAccessible()) {
                setCurrentRoom(nextroom);
                return true;
            } else {
                isKeyNeeded.setTrue();
                if (canAccessRoom(nextroom)) {
                    setCurrentRoom(nextroom);
                    return true;
                }
                return false;
            }
        } else {
            return false;
        }
    }
    /**
     * metodo booleano che controlla il movimento del giocatore verso ovest
     * @param isKeyNeeded
     */
    private boolean goWest(final MutableBoolean isKeyNeeded){
        Room nextroom;
        isKeyNeeded.setFalse();
        if (getCurrentRoom().getWest() != null) {
            nextroom = getCurrentRoom().getWest();
            if (nextroom.isAccessible()) {
                setCurrentRoom(nextroom);
                return true;
            } else {
                isKeyNeeded.setTrue();
                if(canAccessRoom(nextroom)){
                    setCurrentRoom(nextroom);
                    return true;
                }
                return false;
            }
        } else {
            return false;
        }
    }
    /**
     * metodo che ritorna il contenuto dell'inventario in una stringa
     * @return response
     */
    private String checkInventory(){
        String response = "Nel tuo inventario ci sono:\n";
        for (AdvObject o : getInventory()) {
            response=response + o.getName() + ": " + o.getDescription()+ "\n";
        }
        return response;
    }
    /**
     * metodo che ritorna il contenuto della stanza in una stringa
     * @return response
     */
    private String checkRoom(){
        int count = 0;
        String response = "";
        if(getCurrentRoom().isDark()){
            response = "E' troppo bui qui, ti serve una torcia";
        }
        else{
            response = getCurrentRoom().getDescription() + "\nIn questa stanza ci sono:\n";
            if (getCurrentRoom().getObjects().size() > 0) {
                for (AdvObject o : getCurrentRoom().getObjects()) {
                    if(!getInventory().contains(o)){
                        count++;
                        response=response + o.getName() + ": " + o.getDescription()+ "\n";
                    }
                }
                if(count == 0)
                    response = response + "Non c'è niente di interessante qui.";
            } else {
                response="Non c'è niente di interessante qui.";
            }
            return response;
        }
        return response;
    }
    /**
     * metodo che gestisce la raccolta e ritorna l'oggetto raccolto in una stringa
     * @return response
     */
    private String pickUpObject(final ParserOutput p){
        String response = "";
        if (p.getObject() != null) {
            if (p.getObject().isPickupable()) {
                getInventory().add(p.getObject());
                getCurrentRoom().getObjects().remove(p.getObject());
                response = "Hai raccolto: " + p.getObject().getDescription();
            } else {
                response="Non puoi raccogliere questo oggetto.";
            }
        } else {
            response="Non c'è niente da raccogliere qui.";
        }
        return response;
    }
    /**
     * metodo che gestisce l'apertura di un oggetto contenitore e ritorna il contenuto in una stringa
     * @return response
     */
    private String openObject(ParserOutput p){
        /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
         * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
         * Potrebbe non esssere la soluzione ottimale.
         */
        String response = "";
        if (p.getObject() == null && p.getInvObject() == null) {
            response="Non c'è niente da aprire qui.";
        } else {
            if (p.getObject() != null) {
                if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                    if (p.getObject() instanceof AdvObjectContainer) {
                        response="Hai aperto: " + p.getObject().getName()+"\n";
                        AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                        if (!c.getList().isEmpty()) {
                            response=c.getName() + " contiene:";
                            Iterator<AdvObject> it = c.getList().iterator();
                            while (it.hasNext()) {
                                AdvObject next = it.next();
                                getCurrentRoom().getObjects().add(next);
                                response=" " + next.getName()+"\n";
                                it.remove();
                            }
                            return response;
                        }
                        p.getObject().setOpen(true);
                    } else {
                        response="Hai aperto: " + p.getObject().getName();
                        p.getObject().setOpen(true);
                        if(getCurrentRoom().getId() == 221){
                            response = "Dopo aver aperto la saracinesca, ti ritrovi di fronte all'uscita. Un'ondata di sollievo ti pervade mentre varchi la soglia, " +
                                    "lasciando il laboratorio alle tue spalle. Finalmente sei libero.Ti ritrovi all'aperto, respirando a pieni polmoni l'aria fresca e pura. Guardi indietro e vedi il laboratorio con le sue pareti di metallo," +
                                    " ora un ricordo di un pericolo sfiorato. Il cuore ancora palpitante, rifletti sulla tua fuga" +
                                    " avventurosa e sull'incredibile fortuna di essere riuscito a scappare.\n\n\n\n\n\n\n" +
                                    "" +
                                    "COMPLIMENTI, HAI COMPLETATO IL GIOCO!";

                        engine.endGame();
                        }
                    }
                } else {
                    response="Non puoi aprire questo oggetto.";
                }
            }
            if (p.getInvObject() != null) {
                if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                    if (p.getInvObject() instanceof AdvObjectContainer) {
                        AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                        if (!c.getList().isEmpty()) {
                            response=c.getName() + " contiene:"+"\n";
                            Iterator<AdvObject> it = c.getList().iterator();
                            while (it.hasNext()) {
                                AdvObject next = it.next();
                                getInventory().add(next);
                                response=" " + next.getName()+"\n";
                                it.remove();
                            }
                            return response;
                        }
                        p.getInvObject().setOpen(true);
                    } else {
                        p.getInvObject().setOpen(true);
                    }
                    response="Hai aperto nel tuo inventario: " + p.getInvObject().getName();
                } else {
                    response="Non puoi aprire questo oggetto.";
                }
            }
        }
        return response;
    }
    /**
     * metodo che gestisce l'uso di un oggetto e ritorna il risultato in una stringa
     * @return response
     */
    public String useObject(final ParserOutput p){
        String response = "";
        if (p.getInvObject() != null) {
            if (p.getInvObject().isUsable() && p.getInvObject().isLocakble()==false) {
                if(p.getInvObject().getId()==10){ //usa palmares
                    response = "Hai usato il palmares: la password è 3215" ;
                }
                if(p.getInvObject().getId()==12){ //usa palmares
                    response = "Hai usato il telecomando: il garage ora è aperto" ;
                    getRooms().get(21).setAccessible(true);
                }
            } else {
                response="Non puoi usare questo oggetto.";
            }
        } else {
            response="Non hai niente da usare.";
        }
        return response;
    }
    /**
     * metodo che gestisce lo sblocco di un oggetto e ritorna il risultato in una stringa
     * @return response
     */
    public String unlockObject(final ParserOutput p){
        String response = "";
        if (p.getInvObject() != null) {
            if (p.getInvObject().isUsable()) {
                if((p.getInvObject().getId()==10 | p.getInvObject().getId()==12)  && p.getInvObject().getPassword().equals(p.getAuxText())){ //si sblocca il palmares
                    p.getInvObject().setLocakble(false);
                    response = "Hai sbloccato: " + p.getInvObject().getDescription();
                }
            } else {
                response="Non puoi usare questo oggetto.";
            }
        } else {
            response="Non hai niente da sbloccare.";
        }
        return response;
    }
    /**
     * metodo che gestisce l'accensione di un oggetto e ritorna il risultato in una stringa
     * @return response
     */
    public String turnOnObject(final ParserOutput p){
        String response = "";
        if (p.getInvObject() != null && getCurrentRoom().getId()!=9) {
            if (p.getInvObject().isSwitchable()) {
                response = "Hai acceso: " + p.getInvObject().getDescription();
                if(getCurrentRoom().getId()==4 && p.getInvObject().getId()==3){ //si accende la torcia
                    getCurrentRoom().setBackgroundEnlightedImage(); //si cambia l'immagine di sfondo
                }
            } else {
                response="Non puoi accendere questo oggetto.";
            }
        } else {
            if(getCurrentRoom().getId()==9)
            {
                Room.setPowered(true);
                response="Hai acceso il generatore.";
            }
            else if(getCurrentRoom().getId()==7)
            {
                response="Hai acceso la cisterna. \n\nGrave errore! Il materiale radioattivo sta fuoriuscendo!\n";
                getTimer().setDelay(6000);
            }
            else
            {
                response="Non hai niente da accendere.";
            }
        }
        return response;
    }
    /**
     * metodo che gestisce lo spegnimento di un oggetto e ritorna il risultato in una stringa
     * @return response
     */
    public String turnOffObject(final ParserOutput p){
        String response = "";
        if (p.getObject() != null || p.getInvObject() != null) {
            if(p.getInvObject() != null){
                if (p.getInvObject().isSwitchable()) {
                    response = "Hai spento: " + p.getInvObject().getDescription();
                    if(getCurrentRoom().getId()==4 && p.getInvObject().getId()==3){ //si accende la torcia
                        getCurrentRoom().setDark(true); //la stanza è buia
                    }
                }
            }
            else {
                if(getCurrentRoom().getId()==7)
                {
                    getTimer().setDelay(9000);
                    response="Hai spento la cisterna.\n\n" +
                            "Buona notizia! Fortunatamente, la fuoriuscita del materiale radioattivo si è rallentata, offrendoti un prezioso respiro. " +
                            "Tuttavia, non abbassare la guardia! C'è materiale radioattivo ovunque! Continua a muoverti con cautela e cerca di trovare un'uscita sicura il prima possibile!\n " ;
                }
                else
                {
                    response="Non hai niente spegnere.";
                }
            }
        } else {
            response="Non hai niente da usare.";
        }
        return response;
    }
    /**
     * metodo che gestisce l'accesso a una stanza
     * @return true or false
     */
    public boolean canAccessRoom(final Room room) {
        AdvObject key;
        if (room.isAccessible()){
            return true;
        }
        else {
            key = room.getKey();
            if(getCurrentRoom().getId()!=20) {
                if (getInventory().contains(key)) {
                    return true;
                }
            }
            else{
                if(getInventory().contains(key) && Room.isPowered()){
                    return true;
                }
            }
            return false;
        }
    }
    /**
     * metodo che gestisce la prossima mossa del giocatore
     * @return response
     */
    @Override
    public String nextMove(final ParserOutput p) {
        //move
        String response = "";
        boolean noroom = false;
        boolean move = false;
        MutableBoolean isKeyNeeded = new MutableBoolean(false);
        CommandType commandType = p.getCommand().getType();
        switch (commandType)
        {
            case NORD:
                move = goNorth(isKeyNeeded);
                break;
            case SOUTH:
                move = goSouth(isKeyNeeded);
                break;
            case EAST:
                move = goEast(isKeyNeeded);
                break;
            case WEST:
                move = goWest(isKeyNeeded);
                break;
            case INVENTORY:
                response= checkInventory();
                break;
            case LOOK_AT:
                response= checkRoom();
                break;
            case PICK_UP:
                response=pickUpObject(p);
                break;
            case OPEN:
                response= openObject(p);
                break;
            case USE:
                response = useObject(p);
                break;
            case TURN_ON:
                response = turnOnObject(p);
                break;
            case TURN_OFF:
                response = turnOffObject(p);
                break;
            case PUSH:
                // pushObject(p);
                break;
            case UNLOCK:
                response = unlockObject(p);
                break;
        }
        if (commandType == CommandType.EAST ||commandType ==  CommandType.WEST
                || commandType ==  CommandType.SOUTH ||commandType ==  CommandType.NORD){
            if(move){
                if(getCurrentRoom().isVisited()){
                    response = getCurrentRoom().getName();
                }else{
                    response = getCurrentRoom().getName();
                    response = response + "\n================================================\n";
                    response = response + getCurrentRoom().getDescription()+"\n";
                }
            }
            else
            {
                if(isKeyNeeded.booleanValue())
                {
                    response = "Devi avere la chiave per aprire la porta, o devi azionare un meccanismo";
                }
                else
                {
                    response = "Non puoi andare in quella direzione";
                }
            }
        }
        return response;
    }
}
