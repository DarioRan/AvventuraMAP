package di.uniba.map.b.adventure.type;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe che rappresenta una stanza.
 */
public class Room {
    /**
     *  private static boolean che rappresenta lo stato della corrente di una stanza.
     */
    private static boolean powered = false;
    /**
     * private final int che rappresenta l'id della stanza.
     */
    private final int id;
    /**
     * private String che rappresenta il nome della stanza.
     */
    private String name;
    /**
     * private String che rappresenta la descrizione della stanza.
     */
    private String description;
    /**
     * private String che rappresenta il testo che viene visualizzato quando si entra nella stanza.
     */
    private String look;
    /**
     * private boolean che rappresenta la visibilità della stanza.
     */
    private boolean visible = true;
    /**
     * stanza adiacente a sud.
     */
    private Room south = null;
    /**
     * stanza adiacente a nord.
     */
    private Room north = null;
    /**
     * stanza adiacente a est.
     */
    private Room east = null;
    /**
     * stanza adiacente a ovest.
     */
    private Room west = null;
    /**
     * background della stanza.
     */
    private Image backgroundImage;
    /**
     * background illuminato della stanza.
     */
    private Image backgroundEnlightedImage;
    /**
     * percorso del background della stanza.
     */
    private String backgroundImagePath;
    /**
     * percorso del background illuminato della stanza.
     */
    private String backgroundEnlightedImagePath;
    /**
     * private boolean che rappresenta l'accessibilità della stanza.
     */
    private boolean accessible = true;
    /**
     * private AdvObject che rappresenta la chiave della stanza.
     */
    private AdvObject key = null;
    /**
     * private boolean che rappresenta se la stanza è stata visitata o meno.
     */
    private boolean isVisited = false;
    /**
     * private boolean che rappresenta se la stanza è buia o meno.
     */
    private boolean isDark = false;
    /**
     * private final List che rappresenta la lista degli oggetti presenti nella stanza.
     */
    private final List<AdvObject> objects=new ArrayList<>();

    /**
     * Costruttore della classe.
     * @param id
     * @param name
     * @param description
     */
    public Room(final int id,final String name,final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        setBackgroundImage();
    }

    /**
     * metodo che restituisce l'id della stanza.
     * @return id id della stanza
     */
    public int getId() {
        return id;
    }
    /**
     * metodo che restituisce il nome della stanza.
     * @return name nome della stanza
     */
    public String getName() {
        return name;
    }

    /**
     * metodo che settta il nome della stanza.
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * metodo che restituisce la descrizione della stanza.
     * @return description descrizione della stanza
     */
    public String getDescription() {
        return description;
    }

    /**
     * meoto che setta la descrizione della stanza.
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * metodo che restituisce se la stanza è visibile o meno
     * @return visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * metodo che setta la visibilità della stanza.
     * @param visible
     */
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }

    /**
     * metodo che restituisce la stanza adiacente a sud.
     * @return south stanza adiacente a sud
     */
    public Room getSouth() {
        return south;
    }
    /**
     * metodo che setta la stanza adiacente a sud.
     * @param south stanza adiacente a sud
     */
    public void setSouth(final Room south) {
        this.south = south;
    }
    /**
     * metodo che restituisce la stanza adiacente a nord.
     * @return north stanza adiacente a nord
     */
    public Room getNorth() {
        return north;
    }
    /**
     * metodo che setta la stanza adiacente a nord.
     * @param north stanza adiacente a nord
     */
    public void setNorth(final Room north) {
        this.north = north;
    }
    /**
     * metodo che restituisce la stanza adiacente a est.
     * @return east stanza adiacente a est
     */
    public Room getEast() {
        return east;
    }
    /**
     * metodo che setta la stanza adiacente a est.
     * @param east stanza adiacente a est
     */
    public void setEast(final Room east) {
        this.east = east;
    }
    /**
     * metodo che restituisce la stanza adiacente a ovest.
     * @return west stanza adiacente a ovest
     */
    public Room getWest() {
        return west;
    }
/**
     * metodo che setta la stanza adiacente a ovest.
     * @param west stanza adiacente a ovest
     */
    public void setWest(final Room west) {
        this.west = west;
    }
    /**
     * metodo che restituisce gli oggetti presenti nella stanza.
     * @return objects oggetti presenti nella stanza
     */
    public List<AdvObject> getObjects() {
        return objects;
    }

    /**
     * metodo che aggiunge un oggetto alla stanza.
     * @param object
     */
    public void addObject(final AdvObjectContainer object){
        this.objects.add(object);
    }

    /**
     * metodo che restituisce se la stanza ha corrente o meno.
     * @return powered
     */
    public static boolean isPowered() {
        return powered;
    }
    /**
     * metodo che setta se la stanza ha corrente o meno.
     * @param powered
     */
    public static void setPowered(final boolean powered) {
        Room.powered = powered;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
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


    private void setBackgroundImage() {
        ImageIcon backgroundImageIcon = new ImageIcon("resources/"+this.id+".png");
        backgroundImagePath= "resources/"+this.id+".png";
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight(), Image.SCALE_SMOOTH);
        this.backgroundImage = backgroundImage;
    }

    public void setBackgroundEnlightedImage(){
        ImageIcon backgroundImageEnglightedIcon = new ImageIcon("resources/"+this.id+"d.png");
        backgroundEnlightedImagePath= "resources/"+this.id+"d.png";
        Image backgroundEnlightedImage = backgroundImageEnglightedIcon.getImage().getScaledInstance(backgroundImageEnglightedIcon.getIconWidth(), backgroundImageEnglightedIcon.getIconHeight(), Image.SCALE_SMOOTH);
        this.backgroundEnlightedImage = backgroundEnlightedImage;
    }

    public Image getBackgroundEnlightedImage(){
        return this.backgroundEnlightedImage;
    }

    public Image getBackgroundImage(){
        return this.backgroundImage;
    }

    public String getBackgroundImagePath(){
        return this.backgroundImagePath;
    }

    public String getBackgroundEnlightedImagePath(){
        return this.backgroundEnlightedImagePath;
    }
    /**
     * metodo che restituisce se la stanza è accessibile o meno.
     * @return accessible
     */
    public boolean isAccessible() {
        return accessible;
    }
    /**
     * metodo che setta se la stanza è accessibile o meno.
     * @param accessible
     */
    public boolean setAccessible(final boolean accessible) {
        return this.accessible = accessible;
    }
    /**
     * metodo che restituisce se la stanza è stata visitata o meno.
     * @return isVisited
     */
    public boolean isVisited(){
        return this.isVisited;
    }
    /**
     * metodo che setta se la stanza è stata visitata o meno.
     * @param isVisited
     */
    public void setVisited(final boolean isVisited){
        this.isVisited = isVisited;
    }
    /**
     * metodo che restituisce se la stanza è buia o meno.
     * @return isDark
     */
    public boolean isDark(){
        return this.isDark;
    }
    /**
     * metodo che setta se la stanza è buia o meno.
     * @param isDark
     */
    public void setDark(final boolean isDark){
        this.isDark = isDark;
    }
    /**
     * metodo che restituisce la chiave della stanza.
     * @return key
     */
    public AdvObject getKey() {
        return key;
    }
    /**
     * metodo che setta la chiave della stanza.
     * @param key
     * @return key
     */
    public AdvObject setKey(final AdvObject key) {
        return this.key = key;
    }
}