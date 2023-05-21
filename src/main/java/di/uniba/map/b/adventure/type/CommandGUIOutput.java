package di.uniba.map.b.adventure.type;

public class CommandGUIOutput {

    CommandGUIType type;
    String text;
    Object resource;

    public CommandGUIOutput(CommandGUIType type, String text,  Object resource) {
        this.type = type;
        this.resource = resource;
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

    public Object getResource() {
        return resource;
    }

    public void setType(CommandGUIType type) {
        this.type = type;
    }

    public void setResource(Object resource) {
        this.resource = resource;
    }
}
