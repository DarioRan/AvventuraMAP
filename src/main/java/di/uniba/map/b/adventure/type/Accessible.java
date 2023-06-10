package di.uniba.map.b.adventure.type;

public interface Accessible {
    /**
     * private boolean che rappresenta l'accessibilità della stanza.
     */
     boolean accessible = true;
    /**
     * metodo che restituisce se la stanza è accessibile o meno.
     * @return accessible
     */
    public default boolean isAccessible() {return accessible;}
    /**
     * metodo che setta se la stanza è accessibile o meno.
     * @param accessible
     */
}
