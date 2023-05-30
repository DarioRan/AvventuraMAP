package di.uniba.map.b.adventure.type;

public class Torch extends AdvObject{

    public Torch(int id) {
        super(id);
    }

    public Torch(int id, String name) {
        super(id, name);
    }

    public Torch(int id, String name, String description) {
        super(id, name, description);
    }

    void interact(){};



}
