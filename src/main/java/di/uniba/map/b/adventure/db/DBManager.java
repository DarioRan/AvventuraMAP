package di.uniba.map.b.adventure.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBManager {
    Connection conn;
    public DBManager() throws SQLException {
        //connessione con oggetto Properties
        Properties dbprops = new Properties();
        dbprops.setProperty("user", "user");
        dbprops.setProperty("password", "1234");
        conn = DriverManager.getConnection("jdbc:h2:C:/Users/ranie/IdeaProjects/TestMap/DB/DB",
                dbprops);

        initTables();
    }

    private void initTables() throws SQLException {
        Statement createTable = conn.createStatement();
        String query = "CREATE TABLE IF NOT EXISTS GAME (Username VARCHAR PRIMARY KEY," +
                "LastRoom INTEGER NOT NULL," +
                "Inventory STRING)";
        createTable.executeUpdate(query);
    }


    public GameStatus getGameStatus(String username) throws SQLException {

        Statement getStatement = conn.createStatement();
        String lastRoomId="";
        String username1;
        List<Integer> inventory_ids = new ArrayList<Integer>();
        String query = "SELECT * FROM GameStatus WHERE username = '" + username + "'";
        ResultSet rs=getStatement.executeQuery(query);
        if (rs.next()) {
            username1 = rs.getString("Username");
            lastRoomId = rs.getString("LastRoom");
            String inventory = rs.getString("Inventory");
            for(String id : inventory.split(","))
            {
                inventory_ids.add(Integer.valueOf(id));
            }
            return new GameStatus(username, Integer.valueOf(lastRoomId), inventory_ids);
        }
        return null;

    }

    public int insertNewGameStatus(GameStatus gamestatus) throws SQLException {
        int statusCode=0;
        Statement UpdateStatement = conn.createStatement();
        String query = "SELECT * FROM GameStatus WHERE username = '" + gamestatus.username + "'";
        ResultSet rs=UpdateStatement.executeQuery(query);
        if (rs.next()) {
            query = "UPDATE GameStatus SET LastRoom = '"+gamestatus.getLastRoomId().toString()+"', Inventory = '"+gamestatus.getInventoryIdsAsString()+"' WHERE username = '" + gamestatus.username + "'";
            statusCode=UpdateStatement.executeUpdate(query);
        }
        else
        {
            query = "INSERT INTO GameStatus VALUES ('"+gamestatus.getUsername()+"', '"+gamestatus.getLastRoomId().toString()+"', '"+gamestatus.getInventoryIdsAsString()+"');";
            statusCode=UpdateStatement.executeUpdate(query);
        }

        return statusCode;

    }
}
