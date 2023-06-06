/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.db.DBManager;
import di.uniba.map.b.adventure.db.GameStatus;
import di.uniba.map.b.adventure.games.EscapeFromLabGame;
import di.uniba.map.b.adventure.parser.Parser;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.CommandGUIOutput;
import di.uniba.map.b.adventure.type.CommandGUIType;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import di.uniba.map.b.adventure.type.TimerListener;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * ATTENZIONE: l'Engine è molto spartano, in realtà demanda la logica alla
 * classe che implementa GameDescription e si occupa di gestire I/O sul
 * terminale.
 *
 * @author pierpaolo
 */
public class Engine {

    private final GameDescription game;
    private Parser parser;
    private DBManager dbManager;
    private TimerListener timer;
    private int progressValue;
    private Server server;
    private String username;

    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            Set<String> stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));
            parser = new Parser(stopwords);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        timer = new TimerListener(this);
        this.game.setTimer(timer);
        try {
            dbManager=new DBManager();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            server = new Server(this);
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TimerListener getTimer() {
        return this.timer;
    }

    private void startTimer(){
        this.getTimer().start();
    }

    public void incrementProgressBarValue(int progressValue)
            throws IOException {
        this.setProgressValue(progressValue);
    }

    public int getProgressValue() {
        return this.progressValue;
    }

    public void setProgressValue(int progressValue) {
        this.progressValue = progressValue;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void loadGame(String username) throws SQLException {
        GameStatus gameStatus = null;
        try {
            gameStatus = dbManager.getGameStatus(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Integer> idsInventory = gameStatus.getInventoryIds();
        Integer lastRoom_id = gameStatus.getLastRoomId();
        List<AdvObject> inventory = game.filterObjects((AdvObject o) -> idsInventory.contains(o.getId()));
        List<Room> lastRoom = game.filterRoom((Room room)->room.getId() == lastRoom_id);

        game.setCurrentRoom(lastRoom.get(0));
        game.setInventory(inventory);

        System.out.println("Game loaded");
    }

    public void saveGame(String username)
    {
        List<Integer> inventoryIds = new ArrayList<>();
        for (AdvObject o: game.getInventory())
        {
            inventoryIds.add(o.getId());
            System.out.println("ID: "+o.getId());
            System.out.println("Name: "+o.getName());
        }
        GameStatus gameStatus = new GameStatus(username, game.getCurrentRoom().getId(), inventoryIds,
                LocalDateTime.now());
        try {
            dbManager.insertNewGameStatus(gameStatus);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void endGame(){
        System.out.println("helo");
        this.getTimer().stopTimer();
    }

    public CommandGUIOutput execute() {
        String response;
        System.out.println("================================");
        System.out.println("* Adventure v. 0.3 - 2021-2022 *");
        System.out.println("================================");
        response = game.getCurrentRoom().getName();
        response = response + "\n================================================\n\n";
        response = response + game.getCurrentRoom().getDescription()+"\n";
        game.getCurrentRoom().setVisited(true);
        return new CommandGUIOutput(CommandGUIType.SHOW_TEXT, response);
    }

    public CommandGUIOutput executeCommand(String command) throws SQLException {
        String response =command + "\n\n";
        CommandGUIOutput commandGUIOutput;
        CommandType commType;
        ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        if (p == null || p.getCommand() == null) {
            response = response + "Non capisco quello che mi vuoi dire.\n";
        } else {
            commType = p.getCommand().getType();

            response = response + game.nextMove(p);
            switch (commType) {
                case EAST:
                case NORD:
                case SOUTH:
                case WEST:
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.MOVE, response, game.getCurrentRoom().getBackgroundImagePath());
                    System.out.println("Sono in " + game.getCurrentRoom().getId());
                    System.out.println(commType);
                    break;
                case TURN_ON:
                    if (game.getCurrentRoom().isDark()) {
                        commandGUIOutput = new CommandGUIOutput(CommandGUIType.TURN_ON,
                                response, game.getCurrentRoom().getBackgroundEnlightedImagePath());
                        game.getCurrentRoom().setDark(false);
                    } else {
                        commandGUIOutput = new CommandGUIOutput(CommandGUIType.TURN_ON, response, game.getCurrentRoom().getBackgroundImagePath());
                    }
                    break;
                case TURN_OFF:
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.TURN_OFF, response, game.getCurrentRoom().getBackgroundImagePath());
                    break;
                case LOAD_GAME:
                    this.loadGame(this.getUsername());
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.LOAD_GAME, "Caricamento partita", String.valueOf(game.getCurrentRoom().getId()));
                    break;
                case HELP:
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.HELP, response, null);
                    break;
                case END:
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.END, response, null);
                    break;
                case INCREMENT_PB_VALUE:
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.INCREMENT_PB_VALUE, String.valueOf(this.getTimer().getDelay()), String.valueOf(this.getProgressValue()));
                    break;
                case SAVE_GAME:
                    this.saveGame(this.getUsername());
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.SAVE_GAME, String.valueOf(this.getTimer().getDelay()), null);
                    break;
                case START_TIMER:
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.START_TIMER, "", null);
                    this.startTimer();
                    break;
                case STOP_TIMER:
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.STOP_TIMER, "", null);
                    this.endGame();
                    break;
                default:
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.SHOW_TEXT, response, null);
                    break;
            }
            return commandGUIOutput;
        }

        return commandGUIOutput = new CommandGUIOutput(CommandGUIType.SHOW_TEXT, response, null);
    }

    public Object sendResourcesToClient(String request) throws SQLException {
        String response = "";
        Object resources = null;
        CommandType commType;
        ParserOutput p = parser.parse(request, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        if (p == null || p.getCommand() == null) {
            response = response + "Non capisco quello che mi vuoi dire.\n";
        } else {
            commType = p.getCommand().getType();
            switch (commType) {
                case GET_SAVED_GAMES:
                    System.out.println("GET_SAVED_GAMES");
                    resources = this.getSavedGames();
                    break;
                default:
                    resources = null;
                    break;
            }
            return resources;
        }

        return resources = null;
    }


    public List<GameStatus> getSavedGames() throws SQLException {

        return dbManager.getAllSavedGame();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Engine engine;
        engine = new Engine(new EscapeFromLabGame());
    }
}
