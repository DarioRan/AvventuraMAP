package di.uniba.map.b.adventure.type;

import java.io.Serializable;

public class CommandGUIOutput implements Serializable {

    CommandGUIType type;
    String text;
    String pathResource;

    public CommandGUIOutput(CommandGUIType type, String text,  String resource) {
        this.type = type;
        this.pathResource = resource;
        this.text = text;
    }

    public CommandGUIOutput(CommandGUIType type, String text) {
        this.type = type;
        this.text = text;
    }

    public CommandGUIOutput(CommandGUIType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }



    public CommandGUIType getType() {
        return type;
    }

    public String getResource() {
        return pathResource;
    }

    public void setType(CommandGUIType type) {
        this.type = type;
    }

    public void setResource(String resource) {
        this.pathResource = resource;
    }

}
