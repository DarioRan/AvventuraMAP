package di.uniba.map.b.adventure.type;

public class UsableObject extends GameObject{
    private boolean usable = true;

    public UsableObject(int id) {
        super(id);
    }

    public UsableObject(int id, String name) {
        super(id, name);
    }

    public UsableObject(int id, String name, String description) {
        super(id, name, description);
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }
}
