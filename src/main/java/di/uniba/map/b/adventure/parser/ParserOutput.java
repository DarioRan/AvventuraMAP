package di.uniba.map.b.adventure.parser;

import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;

/**
 * Classe che rappresenta l'output del parser
 */
public class ParserOutput {

    /**
     * Comando inserito dall'utente.
     */
    private Command command;

    /**
     * Oggetto semplice.
     */
    private AdvObject object;

    /**
     * Oggetto nell'inventario.
     */
    private AdvObject invObject;

    /**
     * Testo ausiliario.
     */
    private String aux_text;

    /**
     * Costruttore della classe.
     * @param command comando
     * @param object oggetto
     */
    public ParserOutput(Command command, AdvObject object) {
        this.command = command;
        this.object = object;
    }

    /**
     * Costruttore della classe.
     * @param command comando
     * @param object oggetto
     * @param invObejct oggetto nell'inventario
     */
    public ParserOutput(Command command, AdvObject object, AdvObject invObejct) {
        this.command = command;
        this.object = object;
        this.invObject = invObejct;
    }

    /**
     * Costruttore della classe.
     * @param command comando
     * @param object oggetto
     * @param invObejct oggetto nell'inventario
     * @param aux_text testo ausiliario
     */
    public ParserOutput(Command command, AdvObject object, AdvObject invObejct, String aux_text){
        this.command = command;
        this.object = object;
        this.invObject = invObejct;
        this.aux_text = aux_text;
    }

    /**
     * Getter dell'attributo command.
     * @return comando
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Setter dell'attributo command.
     * @param command comando
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Getter dell'attributo object.
     * @return oggetto
     */
    public AdvObject getObject() {
        return object;
    }

    /**
     * Setter dell'attributo object.
     * @param object oggetto
     */
    public void setObject(AdvObject object) {
        this.object = object;
    }

    /**
     * Getter dell'attributo invObject.
     * @return oggetto nell'inventario
     */
    public AdvObject getInvObject() {
        return invObject;
    }

    /**
     * Setter dell'attributo invObject.
     * @param invObject oggetto nell'inventario
     */
    public void setInvObject(AdvObject invObject) {
        this.invObject = invObject;
    }

    /**
     * Getter dell'attributo aux_text.
     * @return testo ausiliario
     */
    public String getAuxText(){
        return aux_text;
    }

    /**
     * Setter dell'attributo aux_text.
     * @param aux_text testo ausiliario
     */
    public void setAuxText(String aux_text){
        this.aux_text = aux_text;
    }
}
