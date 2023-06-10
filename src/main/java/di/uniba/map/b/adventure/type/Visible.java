package di.uniba.map.b.adventure.type;

public interface Visible {

    /**
     * private boolean che rappresenta la visibilità della stanza.
     */
     boolean visible = true;
    /**
     * metodo che restituisce se la stanza è visibile o meno
     * @return visible
     */
    public default boolean isVisible() {
        return visible;
    }

    /**
     * metodo che setta la visibilità della stanza.
     * @param visible
     */
    public default void setVisible(final boolean visible){}

}
