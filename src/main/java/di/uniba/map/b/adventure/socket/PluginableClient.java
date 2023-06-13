package di.uniba.map.b.adventure.socket;

import di.uniba.map.b.adventure.type.CommandGUIOutput;

import java.io.IOException;

public interface PluginableClient {
    /**
     * Metodo che si occupa di inviare un comando al server e di ricevere la risposta
     * @param command Comando da inviare al server
     * @return Risposta del server
     * @throws IOException Eccezione lanciata in caso di errore di I/O
     * @throws ClassNotFoundException Eccezione lanciata in caso di classe non trovata
     */
    CommandGUIOutput executeCommand(String command) throws IOException, ClassNotFoundException;
    /**
     * Metodo che si occupa di inviare un comando di richiesta di risorse al server e di ricevere la risposta
     * @param command Comando da inviare al server
     * @return Risposta del server
     * @throws IOException Eccezione lanciata in caso di errore di I/O
     * @throws ClassNotFoundException Eccezione lanciata in caso di classe non trovata
     */
    <T> T getResourcesFromServer(String command) throws IOException, ClassNotFoundException;

    /**
     * Metodo che si occupa di inviare risorse al server e di ricevere la risposta
     * @param resource risorsa da inviare al server
     * @throws IOException Eccezione lanciata in caso di errore di I/O
     */
    public void sendResourcesToServer(String resource) throws IOException;

    /**
     * Chiudi la connessione con il server
     * @throws IOException Eccezione lanciata in caso di errore di I/O
     */
    void closeConnection() throws IOException;



    }
