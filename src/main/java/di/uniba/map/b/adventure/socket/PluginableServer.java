package di.uniba.map.b.adventure.socket;

import di.uniba.map.b.adventure.Engine;
import di.uniba.map.b.adventure.type.CommandGUIOutput;

import java.io.IOException;
import java.sql.SQLException;

public interface PluginableServer {
    void sendCommand(CommandGUIOutput commandGUIOutput) throws IOException;
    void start() throws IOException, SQLException;
    void setEngine(Engine engine);
}
