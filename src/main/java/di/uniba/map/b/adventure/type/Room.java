/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.type;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pierpaolo
 */
public class Room {

    private final int id;

    private String name;

    private String description;

    private String look;

    private boolean visible = true;

    private Room south = null;

    private Room north = null;

    private Room east = null;

    private Room west = null;

    private Image backgroundImage;

    private boolean accessible = true;

    private AdvObject key = null;


    private final List<AdvObject> objects=new ArrayList<>();

    public Room(int id) {
        this.id = id;
    }

    public Room(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        setBackgroundImage();
    }

    public Room(int id, String name, String description, boolean accessible, AdvObject key) {
        this.id = id;
        this.name = name;
        this.description = description;
        setBackgroundImage();
        this.accessible = accessible;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public List<AdvObject> getObjects() {
        return objects;
    }
    public void addObject(AdvObjectContainer object){
        this.objects.add(object);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    private void setBackgroundImage() {
        ImageIcon backgroundImageIcon = new ImageIcon("resources/"+this.id+".png");
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight(), Image.SCALE_SMOOTH);
        this.backgroundImage = backgroundImage;
    }

    public Image getBackgroundImage(){
        return this.backgroundImage;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public boolean setAccessible(boolean accessible) {
        return this.accessible = accessible;
    }

    public AdvObject getKey() {
        return key;
    }

    public AdvObject setKey(AdvObject key) {
        return this.key = key;
    }
}