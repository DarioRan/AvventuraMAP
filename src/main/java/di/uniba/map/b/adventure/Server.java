package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.games.EscapeFromLabGame;
import di.uniba.map.b.adventure.type.CommandGUIOutput;
import di.uniba.map.b.adventure.type.CommandGUIType;
import di.uniba.map.b.adventure.type.CommandType;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    Engine engine;
    ServerSocket serverSocket;
    Socket clientSocket;

    BufferedReader bufferedReader;

    ObjectOutputStream objectOutputStream;

    public Server(Engine engine) throws IOException {
        this.engine = engine;

        serverSocket = new ServerSocket(7777);

        clientSocket=serverSocket.accept();
        System.out.println("Client connesso");

    }

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        CommandGUIOutput commandGUI = null;
        String commandString = bufferedReader.readLine();

        while (true) {
                    System.out.println(commandString);
                    commandGUI=engine.executeCommand(commandString);
                    objectOutputStream.writeObject(commandGUI);
                    objectOutputStream.flush();
                    commandString = bufferedReader.readLine();
        }

    }

    public void sendCommand(CommandGUIOutput commandGUIOutput) throws IOException {
        objectOutputStream.writeObject(commandGUIOutput);
        objectOutputStream.flush();
    }
}