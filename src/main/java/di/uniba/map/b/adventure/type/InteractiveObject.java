package di.uniba.map.b.adventure.type;

public abstract class InteractiveObject extends GameObject{

    public InteractiveObject(int id) {
        super(id);
    }

    public InteractiveObject(int id, String name) {
        super(id, name);
    }

    public InteractiveObject(int id, String name, String description) {
        super(id, name, description);
    }

    abstract void interact();



}
