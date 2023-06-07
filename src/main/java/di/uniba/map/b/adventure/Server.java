package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.type.CommandGUIOutput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                    if(commandString.startsWith("username:")){
                        String username = commandString.split(":")[1];
                        engine.setUsername(username);
                    } else if(commandString.startsWith("resources:")){
                        String resourceRequest = commandString.split(":")[1];
                        resource = engine.sendResourcesToClient(resourceRequest);
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
