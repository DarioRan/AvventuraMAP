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
import java.sql.SQLException;

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

    public void start() throws IOException, SQLException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.objectOutputStream = objectOutputStream;
        CommandGUIOutput commandGUI = null;
        Object resource = null;
        String commandString = bufferedReader.readLine();

        System.out.println(objectOutputStream);
        while (true) {
                    System.out.println(commandString);
                    if(commandString.startsWith("username:")){
                        String username = commandString.split(":")[1];
                        engine.setUsername(username);
                    } else if(commandString.equals("GETSAVEDGAMES")){
                        resource = engine.sendResourcesToClient(commandString);
                        objectOutputStream.writeObject(resource);
                    } else{
                        commandGUI=engine.executeCommand(commandString);
                        objectOutputStream.writeObject(commandGUI);

                    }
                    objectOutputStream.flush();
                    commandString = bufferedReader.readLine();
        }
    }

    public void sendCommand(CommandGUIOutput commandGUIOutput) throws IOException {
        System.out.println(commandGUIOutput.getType());
        objectOutputStream.writeObject(commandGUIOutput);
        objectOutputStream.flush();
    }

}
