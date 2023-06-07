package di.uniba.map.b.adventure.type;

import java.io.Serializable;
/**
 * Classe che rappresenta l'output di un comando.
 */
public class CommandGUIOutput implements Serializable {
    /**
     * Enumerazione che rappresenta i tipi di output di un comando.
     */
    private CommandGUIType type;
    /**
     * Testo dell'output.
     */
    private String text;
    /**
     * Percorso della risorsa da visualizzare.
     */
    private String pathResource;
    /**
     * Costruttore della classe.
     * @param type tipo di output
     * @param text testo dell'output
     * @param resource percorso della risorsa da visualizzare
     */
    public CommandGUIOutput(final CommandGUIType type,final String text,final String resource) {
        this.type = type;
        this.pathResource = resource;
        this.text = text;
    }
    /**
     * Costruttore della classe.
     * @param type tipo di output
     * @param text testo dell'output
     */
    public CommandGUIOutput(final CommandGUIType type,final String text) {
        this.type = type;
        this.text = text;
    }
    /**
     * Costruttore della classe.
     * @param type tipo di output
     */
    public CommandGUIOutput(final CommandGUIType type) {
        this.type = type;
    }
    /**
     * metodo che restituisce il testo dell'output.
     * @return type testo dell'output
     */
    public String getText() {
        return text;
    }
    /**
     * metodo che restituisce il tipo di output.
     * @return type tipo di output
     */
    public CommandGUIType getType() {
        return type;
    }
    /**
     * metodo che restituisce il percorso della risorsa da visualizzare.
     * @return pathResource percorso della risorsa da visualizzare
     */
    public String getResource() {
        return pathResource;
    }
    /**
     * metodo che setta il testo dell'output.
     * @param type testo dell'output
     */
    public void setType(final CommandGUIType type) {
        this.type = type;
    }
    /**
     * metodo che setta il il percorso della risorsa da visualizzare.
     */
    public void setResource(final String resource) {
        this.pathResource = resource;
    }

}
