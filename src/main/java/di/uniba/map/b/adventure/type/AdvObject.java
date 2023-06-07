package di.uniba.map.b.adventure.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe che rappresenta un oggetto all'interno del gioco.
 */
public class AdvObject {

    /**
     * Identificativo dell'oggetto.
     */
    private final int id;

    /**
     * Nome dell'oggetto.
     */
    private String name;

    /**
     * Descrizione dell'oggetto.
     */
    private String description;

    /**
     * Alias dell'oggetto.
     */
    private Set<String> alias;

    /**
     * Indica se l'oggetto è apribile.
     */
    private boolean openable = false;

    /**
     * Indica se l'oggetto è raccoglibile.
     */
    private boolean pickupable = true;

    /**
     * Indica se l'oggetto è spingibile.
     */
    private boolean pushable = false;

    /**
     * Indica se l'oggetto è aperto.
     */
    private boolean open = false;

    /**
     * Indica se l'oggetto è spinto.
     */
    private boolean push = false;

    /**
     * Indica se l'oggetto è utilizzabile.
     */
    private boolean usable = false;

    /**
     * Indica se l'oggetto si può accendere e spegnere.
     */
    private boolean switchable = false;

    /**
     * Indica se l'oggetto è bloccato.
     */
    private boolean locakble = false;

    /**
     * Indica la password dell'oggetto.
     */
    private String password = null;

    /**
     * Costruttore di default.
     * @param id Identificativo dell'oggetto.
     */
    public AdvObject(int id) {
        this.id = id;
    }

    /**
     * Costruttore con parametri.
     * @param id Identificativo dell'oggetto.
     * @param name Nome dell'oggetto.
     */
    public AdvObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Costruttore con parametri.
     * @param id Identificativo dell'oggetto.
     * @param name Nome dell'oggetto.
     * @param description Descrizione dell'oggetto.
     */
    public AdvObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Costruttore con parametri.
     * @param id Identificativo dell'oggetto.
     * @param name Nome dell'oggetto.
     * @param description Descrizione dell'oggetto.
     * @param alias Alias dell'oggetto.
     */
    public AdvObject(int id, String name, String description, String[] alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * getter del name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter del name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter della description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter della description.
     * @param description Descrizione dell'oggetto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter se è apribile.
     * @return openable
     */
    public boolean isOpenable() {
        return openable;
    }

    /**
     * setter se è apribile.
     * @param openable Indica se l'oggetto è apribile.
     */
    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    /**
     * getter se è raccoglibile.
     * @return pickupable
     */
    public boolean isPickupable() {
        return pickupable;
    }

    /**
     * setter se è raccoglibile.
     * @param pickupable Indica se l'oggetto è raccoglibile.
     */
    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }

    /**
     * getter se è spingibile.
     * @return pushable
     */
    public boolean isPushable() {
        return pushable;
    }

    /**
     * setter se è spingibile.
     * @param  pushable Indica se l'oggetto è spingibile.
     */
    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }

    /**
     * getter se è aperto.
     * @return open
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * setter se è aperto.
     * @param open Indica se l'oggetto è aperto.
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * getter se è spinto.
     * @return push
     */
    public boolean isPush() {
        return push;
    }

    /**
     * setter se è spinto.
     * @param push Indica se l'oggetto è spinto.
     */
    public void setPush(boolean push) {
        this.push = push;
    }

    /**
     * getter se è utilizzabile.
     * @return usable
     */
    public boolean isUsable() {
        return usable;
    }

    /**
     * setter se è utilizzabile.
     * @param usable Indica se l'oggetto è utilizzabile.
     */
    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    /**
     * getter se è accendibile.
     * @return switchable
     */
    public void setSwitchable(boolean switchable) {
        this.switchable = switchable;
    }

    /**
     * getter se è bloccabile.
     * @return  locakble Indica se l'oggetto è bloccabile.
     */
    public boolean isLocakble() {
        return locakble;
    }

    /**
     * setter se è bloccabile.
     * @param locakble Indica se l'oggetto è bloccabile.
     */
    public void setLocakble(boolean locakble) {
        this.locakble = locakble;
    }

    /**
     * getter se è accendibile.
     * @return switchable
     */
    public boolean isSwitchable() {
        return switchable;
    }

    /**
     * getter della password dell'oggetto.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter della password dell'oggetto.
     * @param password Password dell'oggetto.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter alias dell'oggetto.
     * @return set di alias
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * setter alias dell'oggetto.
     * @param alias Alias dell'oggetto.
     */
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    /**
     * setter alias dell'oggetto.
     * @param alias Alias dell'oggetto.
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * getter dell'id dell'oggetto.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * converte in hascode l'id dell'oggetto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    /**
     * confronta l'id dell'oggetto.
     * @param obj Oggetto da confrontare.
     * @return true se l'id è uguale, false altrimenti.
     */
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
        final AdvObject other = (AdvObject) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
