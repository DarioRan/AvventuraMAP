package di.uniba.map.b.adventure.type;

public interface Powerable {
    static boolean powered = false;
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
    public static void setPowered(final boolean powered) {}

}
