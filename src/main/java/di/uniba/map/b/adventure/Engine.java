/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.games.EscapeFromLabGame;
import di.uniba.map.b.adventure.parser.Parser;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandGUIOutput;
import di.uniba.map.b.adventure.type.CommandGUIType;
import di.uniba.map.b.adventure.type.CommandType;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
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
    private AdventureGameGUI gui;

    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        this.gui = new AdventureGameGUI(this);
        try {
            Set<String> stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));
            parser = new Parser(stopwords);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public CommandGUIOutput execute() {
        String response;
        System.out.println("================================");
        System.out.println("* Adventure v. 0.3 - 2021-2022 *");
        System.out.println("================================");
        response = game.getCurrentRoom().getName();
        response = response + "\n================================================\n";
        response = response + game.getCurrentRoom().getDescription()+"\n";
        game.getCurrentRoom().setVisited(true);
        return new CommandGUIOutput(CommandGUIType.SHOW_TEXT, response);
    }

    public CommandGUIOutput executeCommand(String command) {
        String response =command + "\n\n";
        CommandGUIOutput commandGUIOutput;
        CommandType commType;
        ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        if (p == null || p.getCommand() == null) {
            response = response + "Non capisco quello che mi vuoi dire.\n";
        } else if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
            response = response + "Addio!\n";
        } else {
            commType = p.getCommand().getType();
            response = response + game.nextMove(p);
            //Se è un comando di movimento, cambia background
            if (commType==CommandType.EAST || commType==CommandType.NORD || commType==CommandType.SOUTH || commType==CommandType.WEST)
            {
                commandGUIOutput = new CommandGUIOutput(CommandGUIType.MOVE, response, game.getCurrentRoom().getBackgroundImage());
                System.out.println("Sono in " + game.getCurrentRoom().getId());
                System.out.println(commType);
            }
            else if(commType==CommandType.TURN_ON)
            {
                if(game.getCurrentRoom().isDark())
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.TURN_ON, response, game.getCurrentRoom().getBackgroundEnlightedImage());
                else
                    commandGUIOutput = new CommandGUIOutput(CommandGUIType.TURN_ON, response, game.getCurrentRoom().getBackgroundImage());
            } else if(commType==CommandType.TURN_OFF)
            {
                commandGUIOutput = new CommandGUIOutput(CommandGUIType.TURN_OFF, response, game.getCurrentRoom().getBackgroundImage());
            }else {
                commandGUIOutput = new CommandGUIOutput(CommandGUIType.SHOW_TEXT, response, null);
            }
            return commandGUIOutput;
            /*gui.appendTextAreaText(response);
            System.out.println("Sei in " + game.getCurrentRoom().getName());
            System.out.println(game.getCurrentRoom().getDescription());
            System.out.println();*/
        }
        return commandGUIOutput = new CommandGUIOutput(CommandGUIType.SHOW_TEXT, response, null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Engine engine;
        engine = new Engine(new EscapeFromLabGame());
    }

}
