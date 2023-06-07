package di.uniba.map.b.adventure.parser;

import di.uniba.map.b.adventure.Utils;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;
import java.util.List;
import java.util.Set;


/**
 * Classe che rappresenta il parser del gioco.
 */
public class Parser {

    /**
     * Insieme di parole da ignorare durante il parsing.
     */
    private final Set<String> stopwords;

    /**
     * Costruttore del parser.
     * @param stopwords Insieme di parole da ignorare durante il parsing.
     */
    public Parser(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    /**
     * Metodo che controlla se il token è un comando.
     * @param token Token da controllare.
     * @param commands Lista dei comandi.
     * @return Indice del comando se è presente, -1 altrimenti.
     */
    private int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals(token) || commands.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metodo che controlla se il token è un oggetto.
     * @param token Token da controllare.
     * @param obejcts Lista degli oggetti.
     * @return Indice dell'oggetto se è presente, -1 altrimenti.
     */
    private int checkForObject(String token, List<AdvObject> obejcts) {
        for (int i = 0; i < obejcts.size(); i++) {
            if (obejcts.get(i).getName().equals(token) || obejcts.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metodo che controlla se il token è un testo ausiliario.
     * @param tokens Token da controllare.
     * @return Indice dell'oggetto se è presente, -1 altrimenti.
     */
    private String checkForAuxText(List<String> tokens)
    {
        for (String s: tokens)
        {
            if (s.charAt(0) == '"' && s.charAt(s.length()-1) == '"'){
                return s.substring(1, s.length()-1);
            }
        }
        return null;
    }

    /**
     * Metodo che effettua il parsing del comando.
     * @param command Comando da parsare.
     * @param commands Lista dei comandi.
     * @param objects Lista degli oggetti.
     * @param inventory Lista degli oggetti nell'inventario.
     * @return Oggetto di tipo ParserOutput che contiene il comando, l'oggetto e l'oggetto dell'inventario.
     */
    public ParserOutput parse(String command, List<Command> commands, List<AdvObject> objects, List<AdvObject> inventory) {
        List<String> tokens = Utils.parseString(command, stopwords);
        String aux_text;
        if (!tokens.isEmpty()) {
            int ic = checkForCommand(tokens.get(0), commands);
            if (ic > -1) {
                if (tokens.size() > 1) {
                    int io = checkForObject(tokens.get(1), objects);
                    int ioinv = -1;
                    if (io < 0 && tokens.size() > 2) {
                        io = checkForObject(tokens.get(2), objects);
                    }
                    if (io < 0) {
                        ioinv = checkForObject(tokens.get(1), inventory);
                        if (ioinv < 0 && tokens.size() > 2) {
                            ioinv = checkForObject(tokens.get(2), inventory);
                        }
                    }
                    aux_text = checkForAuxText(tokens);
                    if (io > -1 && ioinv > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), inventory.get(ioinv));
                    } else if (io > -1 && aux_text != null) {
                        return new ParserOutput(commands.get(ic), objects.get(io), null, aux_text);
                    } else if (ioinv > -1 && aux_text != null) {
                        return new ParserOutput(commands.get(ic), null, inventory.get(ioinv), aux_text);
                    } else if (io > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), null);
                    } else if (ioinv > -1) {
                        return new ParserOutput(commands.get(ic), null, inventory.get(ioinv));
                    } else {
                        return new ParserOutput(commands.get(ic), null, null);
                    }
                } else {
                    return new ParserOutput(commands.get(ic), null);
                }
            } else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    }

}
