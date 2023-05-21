package di.uniba.map.b.adventure.type;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ContainerObject extends InteractiveObject {

    Set container = new HashSet<PickableObject>();

    public ContainerObject(int id) {
        super(id);
    }

    public ContainerObject(int id, String name) {
        super(id, name);
    }

    public ContainerObject(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    void interact() {
        //show object that are in the container
    }

    void addObject(PickableObject object){
        container.add(object);
    }

    void removeObject(PickableObject object){
        container.remove(object);
    }
}
