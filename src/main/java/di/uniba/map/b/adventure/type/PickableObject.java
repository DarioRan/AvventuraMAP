package di.uniba.map.b.adventure.type;

public class PickableObject extends AdvObject{
    private boolean pickupable = true;

    public PickableObject(int id) {
        super(id);
    }

    public PickableObject(int id, String name) {
        super(id, name);
    }

    public PickableObject(int id, String name, String description) {
        super(id, name, description);
    }

    public boolean isPickupable() {
        return pickupable;
    }

    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }
}
