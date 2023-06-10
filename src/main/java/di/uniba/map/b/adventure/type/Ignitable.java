package di.uniba.map.b.adventure.type;

public interface Ignitable {

    /**
     * boolean che rappresenta se la stanza è buia o meno.
     */
     boolean isDark = false;

    /**
     * metodo che restituisce se la stanza è buia o meno.
     * @return isDark
     */
    public default boolean isDark(){return this.isDark;}
    /**
     * metodo che setta se la stanza è buia o meno.
     * @param isDark
     */
    public default void setDark(final boolean isDark){}
}
