package di.uniba.map.b.adventure.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 * Classe che gestisce la connessione al database.
 */
public class DBManager {
    /**
     * Connessione al database.
     */
    private Connection conn;
    /**
     * Costruttore della classe.
     * @throws SQLException
     */
    public DBManager() throws SQLException {
        //connessione con oggetto Properties
        Properties dbprops = new Properties();
        dbprops.setProperty("user", "user");
        dbprops.setProperty("password", "1234");
        conn = DriverManager.getConnection("jdbc:h2:./DB/DB",
                dbprops);

        initTables();
    }
    /**
     * Metodo che inizializza le tabelle del database.
     * @throws SQLException eccezione lanciata in caso di errore di connessione al database.
     */
    private void initTables() throws SQLException {
        Statement createTable = conn.createStatement();
        String query = "CREATE TABLE IF NOT EXISTS GAMESTATUS (Username VARCHAR PRIMARY KEY,"
                +
                "LastRoom INTEGER NOT NULL,"
                + "Inventory VARCHAR, "
                + "Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "Progress INTEGER NOT NULL," +
                "Delay INTEGER NOT NULL)";
        createTable.executeUpdate(query);
        createTable.close();
    }
    /**
     * Metodo che restituisce lo stato di gioco di un utente.
     * @param username nome utente.
     * @return stato di gioco.
     * @throws SQLException eccezione lanciata in caso di errore di connessione al database.
     */
    public GameStatus getGameStatus(final String username) throws SQLException {
        Statement getStatement = conn.createStatement();
        String lastRoomId="";
        String username1;
        String progress;
        String delay;
        List<Integer> inventoryIds = new ArrayList<Integer>();
        String query = "SELECT * FROM GameStatus WHERE username = '" + username + "'";
        ResultSet rs = getStatement.executeQuery(query);
        if (rs.next()) {
            username1 = rs.getString("Username");
            lastRoomId = rs.getString("LastRoom");
            String inventory = rs.getString("Inventory");
            Timestamp time = rs.getTimestamp("Time");
            progress = rs.getString("Progress");
            delay = rs.getString("Delay");
            if (!inventory.equals("")){
                for(String id : inventory.split(",")) {
                    inventoryIds.add(Integer.valueOf(id));
                }
            }
            return new GameStatus(username, Integer.valueOf(lastRoomId), inventoryIds,
                    time.toLocalDateTime(), Integer.valueOf(progress), Integer.valueOf(delay));
        }
        return null;

    }
    /**
     * Metodo che inserisce un nuovo stato di gioco nel database
     * @param gamestatus stato di gioco
     * @return codice di stato
     * @throws SQLException eccezione lanciata in caso di errore di connessione al database.
     */
    public int insertNewGameStatus(final GameStatus gamestatus) throws SQLException {
        int statusCode = 0;
        Statement updateStatement = conn.createStatement();
        String query = "SELECT * FROM GameStatus WHERE username = '" + gamestatus.getUsername() + "'";
        ResultSet rs = updateStatement.executeQuery(query);
        if (rs.next()) {
            query = "UPDATE GameStatus SET LastRoom = '" + gamestatus.getLastRoomId().toString() + "', Inventory = '" + gamestatus.getInventoryIdsAsString() + "', Progress = '" + gamestatus.getProgress() + "', Delay = '" + gamestatus.getDelay() + "' WHERE username = '" + gamestatus.getUsername() + "'";
            statusCode = updateStatement.executeUpdate(query); }
        else {
            query = "INSERT INTO GameStatus (Username, LastRoom, Inventory, Progress, Delay) VALUES ('" + gamestatus.getUsername() + "', '" + gamestatus.getLastRoomId().toString() + "', '" + gamestatus.getInventoryIdsAsString() + "', '" + gamestatus.getProgress() + "', '" + gamestatus.getDelay() + "');";
            statusCode = updateStatement.executeUpdate(query);
        }

        return statusCode;

    }
    /**
     * Metodo che restituisce tutti gli stati di gioco salvati.
     * @return lista di stati di gioco.
     */
    public List<GameStatus> getAllSavedGame() {
        List<GameStatus> savedGame = new ArrayList<GameStatus>();
        	try {
                Statement getStatement = conn.createStatement();
                String lastRoomId = "";
                String username1;
                String progress;
                String delay;
                List<Integer> inventoryIds = new ArrayList<Integer>();
                String query = "SELECT * FROM GameStatus";
                ResultSet rs = getStatement.executeQuery(query);
                while (rs.next()) {
                    username1 = rs.getString("Username");
                    lastRoomId = rs.getString("LastRoom");
                    String inventory = rs.getString("Inventory");
                    progress = rs.getString("Progress");
                    delay = rs.getString("Delay");
                    Timestamp time = rs.getTimestamp("Time");
                    if (!inventory.equals("")) {
                        for (String id : inventory.split(",")) {
                            inventoryIds.add(Integer.valueOf(id));
                        }
                    }
                    savedGame.add(new GameStatus(username1,
                            Integer.valueOf(lastRoomId),
                            inventoryIds,
                            time.toLocalDateTime(),Integer.valueOf(progress),Integer.valueOf(delay)));
                }
                return savedGame;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
