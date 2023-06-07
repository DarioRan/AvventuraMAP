package di.uniba.map.b.adventure.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Classe che gestisce le descrizioni delle stanze.
 */
public class RoomDesc {
    /**
     * Array di stringhe che contiene le descrizioni delle stanze.
     */
    private final String[] descriptions;
    /**
     * Array di stringhe che contiene i titoli delle stanze.
     */
    private final String[] titles;
    /**
     * Costruttore della classe RoomDesc.
     * @param descFilePath Percorso del file che contiene le descrizioni delle stanze.
     * @param titleFilePath Percorso del file che contiene i titoli delle stanze.
     * @throws IOException Eccezione che viene lanciata se il file non viene trovato.
     */
    public RoomDesc(final String descFilePath, final String titleFilePath) throws IOException {
        descriptions = readLines(descFilePath);
        titles = readLines(titleFilePath);
    }
    /**
     * Metodo che legge le descrizioni delle stanze da un file.
     * @param filePath Percorso del file che contiene le descrizioni delle stanze.
     * @return Array di stringhe che contiene le descrizioni delle stanze.
     * @throws IOException Eccezione che viene lanciata se il file non viene trovato.
     */
    private String[] readLines(final String filePath) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            return reader.lines().toArray(String[]::new);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    /**
     * Metodo che restituisce la descrizione di una stanza.
     * @param index Indice della descrizione della stanza.
     * @return Descrizione della stanza.
     */
    public String getDescription(final int index) {
        if (index >= 0 && index < descriptions.length) {
            return descriptions[index];
        }
        return "";
    }

    /**
     * Metodo che restituisce il titolo di una stanza.
     * @param index
     * @return Titolo della stanza.
     */
    public String getTitle(final int index) {
        if (index >= 0 && index < titles.length) {
            return titles[index];
        }
        return "";
    }
}
