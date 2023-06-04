package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.misc.RoomDesc;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;

import java.awt.Image;
import java.io.PrintStream;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.Iterator;

import di.uniba.map.b.adventure.type.TimerListener;
import org.apache.commons.lang3.mutable.MutableBoolean;

import javax.swing.ImageIcon;

public class EscapeFromLabGame extends GameDescription {

    RoomDesc desc = new RoomDesc();
    private TimerListener timer;

    public EscapeFromLabGame(TimerListener timer) {
        super();
        this.timer = timer;
    }

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
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"utilizza","attiva"});
        getCommands().add(use);
        Command turn_on = new Command(CommandType.TURN_ON, "accendi");
        turn_on.setAlias(new String[]{"accendi"});
        getCommands().add(turn_on);
        Command turn_off = new Command(CommandType.TURN_OFF, "spegni");
        turn_off.setAlias(new String[]{"spegni"});
        getCommands().add(turn_off);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{"apri"});
        getCommands().add(open);
        Command load = new Command(CommandType.LOAD_GAME, "LOADGAME");
        load.setAlias(new String[]{"LOADGAME", "loadgame"});
        getCommands().add(load);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva","blocca"});
        getCommands().add(push);
        Command unlock = new Command(CommandType.UNLOCK, "sblocca");
        unlock.setAlias(new String[]{"sblocca"});
        getCommands().add(unlock);
        Command help = new Command(CommandType.HELP, "help");
        help.setAlias(new String[]{"HELP", "aiuto", "comandi", "help", "istruzioni"});
        getCommands().add(help);

    }

    /**
     * Inizializza le stanze del gioco e definisce la mappa
     */
    private void initRooms(){
        Room room1 = new Room(1,desc.getTITLE_1() ,desc.getDESC_1());
        Room room2 = new Room(2,desc.getTITLE_2() , desc.getDESC_2());
        Room room3 = new Room(3,desc.getTITLE_3() , desc.getDESC_3());
        Room room4 = new Room(4,desc.getTITLE_4() , desc.getDESC_4());
        Room room5 = new Room(5,desc.getTITLE_5() , desc.getDESC_5());
        Room room6 = new Room(6,desc.getTITLE_6() , desc.getDESC_6());
        Room room7 = new Room(7,desc.getTITLE_7() , desc.getDESC_7());
        Room room8 = new Room(8,desc.getTITLE_8() , desc.getDESC_8());
        Room room9 = new Room(9,desc.getTITLE_9() , desc.getDESC_9());
        room9.setAccessible(false);
        Room room10 = new Room(10,desc.getTITLE_10() , desc.getDESC_10());
        Room room11 = new Room(11,desc.getTITLE_11() , desc.getDESC_11());
        Room room12 = new Room(12,desc.getTITLE_12() , desc.getDESC_12());
        Room room13 = new Room(13,desc.getTITLE_13() , desc.getDESC_13());
        room13.setAccessible(false);
        Room room14 = new Room(14,desc.getTITLE_14() , desc.getDESC_14());
        Room room15 = new Room(15,desc.getTITLE_15() , desc.getDESC_15());
        Room room16 = new Room(16,desc.getTITLE_16() , desc.getDESC_16());
        Room room17 = new Room(17,desc.getTITLE_17() , desc.getDESC_17());
        Room room18 = new Room(18,desc.getTITLE_18() , desc.getDESC_18());
        Room room19 = new Room(19,desc.getTITLE_19() , desc.getDESC_19());
        Room room20 = new Room(20,desc.getTITLE_20() , desc.getDESC_20());
        Room room21 = new Room(21,desc.getTITLE_21() , desc.getDESC_21());
        room21.setAccessible(false);
        Room room221 = new Room(221,desc.getTITLE_22() , desc.getDESC_22());
        room221.setAccessible(false);
        Room room222 = new Room(222,desc.getTITLE_22() , desc.getDESC_22());
        Room room23 = new Room(23,desc.getTITLE_23() , desc.getDESC_23());
        Room room24 = new Room(24,desc.getTITLE_24() , desc.getDESC_24());
        Room room25 = new Room(25,desc.getTITLE_25() , desc.getDESC_25());
        Room room26 = new Room(26,desc.getTITLE_26() , desc.getDESC_26());
        Room room27 = new Room(27,desc.getTITLE_27() , desc.getDESC_27());
        Room room28 = new Room(28,desc.getTITLE_28() , desc.getDESC_28());
        Room room29 = new Room(29,desc.getTITLE_29() , desc.getDESC_29());
        Room room30 = new Room(30,desc.getTITLE_30() , desc.getDESC_30());
        Room room31 = new Room(31,desc.getTITLE_31() , desc.getDESC_31());
        room31.setAccessible(false);
        Room room32 = new Room(32,desc.getTITLE_32(), desc.getDESC_32());
        Room room33 = new Room(33,desc.getTITLE_33() , desc.getDESC_33());
        Room room34 = new Room(34,desc.getTITLE_34(), desc.getDESC_34());
        Room room35 = new Room(35,desc.getTITLE_35() , desc.getDESC_35());
        Room room36 = new Room(36,desc.getTITLE_36() , desc.getDESC_36());

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


    }
    @Override
    public void init() throws Exception {
        initCommands();
        initRooms();
    }

    private boolean goNorth(MutableBoolean isKeyNeeded){
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

    private boolean goSouth(MutableBoolean isKeyNeeded){
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

    private boolean goEast(MutableBoolean isKeyNeeded){
        Room nextroom;
        isKeyNeeded.setFalse();
        if (getCurrentRoom().getEast() != null) {
            nextroom = getCurrentRoom().getEast();
            if (nextroom.isAccessible()) {
                setCurrentRoom(nextroom);
                return true;
            } else {
                isKeyNeeded.setTrue();
                System.out.println(isKeyNeeded);
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

    private boolean goWest(MutableBoolean isKeyNeeded){
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

    private String checkInventory(){
        String response = "Nel tuo inventario ci sono:\n";
        for (AdvObject o : getInventory()) {
            response=response + o.getName() + ": " + o.getDescription()+ "\n";
        }
        return response;
    }

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

    private String pickUpObject(ParserOutput p){
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

    private String openObject(ParserOutput p){
        /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
         * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
         * Potrebbe non esssere la soluzione ottimale.
         */
        String response = "";
        System.out.println("\n p" + p);
        System.out.println("\n obj" + p.getObject());
        System.out.println("\n inv obj" + p.getInvObject());
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

    public String useObject(ParserOutput p){
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

    public String unlockObject(ParserOutput p){
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

    public String turnOnObject(ParserOutput p){
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
                else
                {
                   response="Non hai niente da usare.";
                }
        }
        return response;
    }

    public String turnOffObject(ParserOutput p){
        String response = "";
        if (p.getInvObject() != null) {
            if (p.getInvObject().isSwitchable()) {
                response = "Hai spento: " + p.getInvObject().getDescription();
                if(getCurrentRoom().getId()==4 && p.getInvObject().getId()==3){ //si accende la torcia
                    getCurrentRoom().setDark(true); //la stanza è buia
                }
            } else {
                if(getCurrentRoom().getId()==7)
                {
                    //timer.setdelay 5000
                    response="Hai spento la cisterna.";
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

    public boolean canAccessRoom(Room room) {
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

    @Override
    public String nextMove(ParserOutput p) {
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

        System.out.println(isKeyNeeded);

        if (commandType == CommandType.EAST ||commandType ==  CommandType.WEST
                || commandType ==  CommandType.SOUTH ||commandType ==  CommandType.NORD){
            if(move){
                if(getCurrentRoom().isVisited()){
                    response = getCurrentRoom().getName();
                    response = response + "\n================================================\n";
                }else{
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

    private void end (PrintStream out) {
        out.println("Premi il pulsante del giocattolo e in seguito ad una forte esplosione la tua casa prende fuoco...\ntu e tuoi famigliari cercate invano di salvarvi e venite avvolti dalle fiamme...\nè stata una morte CALOROSA...addio!");
        System.exit(0);
    }
}
