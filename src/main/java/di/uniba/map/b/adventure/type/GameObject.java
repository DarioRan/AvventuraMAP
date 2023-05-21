package di.uniba.map.b.adventure.type;

import java.util.Set;

public class GameObject {
    private final int id;
    private String name;
    private String description;

    private Set<String> alias;

    public GameObject(int id) {
        this.id = id;
    }

    public GameObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GameObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public GameObject(int id, String name, String description, Set<String> alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }
}
