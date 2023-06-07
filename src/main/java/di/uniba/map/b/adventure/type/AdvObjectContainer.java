package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un oggetto contenitore.
 */
public class AdvObjectContainer extends AdvObject {
    /**
     * Lista di oggetti contenuti.
     */
    private List<AdvObject> list = new ArrayList<>();
    /**
     * Costruttore della classe.
     * @param id id dell'oggetto
     * @param name nome dell'oggetto
     * @param description descrizione dell'oggetto
     */
    public AdvObjectContainer(final int id,final String name,final String description) {
        super(id, name, description);
    }
    /**
     * metodo che restituisce la lista di oggetti contenuti.
     * @return list lista di oggetti contenuti
     */
    public List<AdvObject> getList() {
        return list;
    }
    /**
     * metodo che setta la lista di oggetti contenuti.
     * @param listParam lista di oggetti contenuti
     */
    public void setList(final List<AdvObject> listParam) {
        this.list = listParam;
    }
    /**
     * metodo che aggiunge un oggetto alla lista di oggetti contenuti.
     * @param o oggetto da aggiungere
     */
    public void add(final AdvObject o) {
        list.add(o);
    }
    /**
     * metodo che rimuove un oggetto dalla lista di oggetti contenuti.
     * @param o oggetto da rimuovere
     */
    public void remove(final AdvObject o) {
        list.remove(o);
    }

}
