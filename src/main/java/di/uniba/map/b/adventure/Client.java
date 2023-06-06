package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.type.CommandGUIOutput;
import di.uniba.map.b.adventure.type.CommandGUIType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    Socket socket;
    ObjectInputStream objectReader;
    BufferedWriter bufferedWriter;
    public Client() throws IOException {

        socket = new Socket("localhost", 7777);
        objectReader = new ObjectInputStream(socket.getInputStream());
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public CommandGUIOutput executeCommand(String command) throws IOException, ClassNotFoundException {

        CommandGUIOutput commandGUIOutput = null;
        Object response = null;

        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);


        System.out.println("Invio comando: " + command);
        out.println(command);
        response=objectReader.readObject();
        if(response instanceof CommandGUIOutput){
            commandGUIOutput=(CommandGUIOutput) response;
            return commandGUIOutput;
        }
        else
        {
            return new CommandGUIOutput(CommandGUIType.UNKNOWN);
        }
    }
}
