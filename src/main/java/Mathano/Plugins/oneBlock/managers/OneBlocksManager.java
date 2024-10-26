package Mathano.Plugins.oneBlock.managers;

import Mathano.Plugins.oneBlock.database.statements.DataOneBlocksStatements;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OneBlocksManager {
    public static OneBlocksManager INSTANCE;

    public static Map<UUID, Location> oneBlocksData = new HashMap<>();

    public OneBlocksManager() {
        INSTANCE = this;
    }

    public void loadOneBlocksIntoCache() {
        // retrieve ob data.......
        try(Connection connection = DatabaseManager.INSTANCE.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DataOneBlocksStatements.LOAD_ONEBLOCKS_CACHE);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UUID uuid = UUID.fromString(resultSet.getString("uuid"));
                String location = resultSet.getString("location");


                oneBlocksData.put(uuid, getLocationString(location));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Location getLocationString(String string) {
        if (string == null || string.trim() == "") {
            return null;
        }

        String[] parts = string.split(":");
        if (parts.length == 4) {
            World world = Bukkit.getServer().getWorld(parts[0]);
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int z = Integer.parseInt(parts[3]);

            return new Location(world, x, y ,z);
        }
        return null;
    }

    public void saveOneBlocksFromCache() {
        // save in db ob data.......
        // missing redundancy checks......
        try(Connection connection = DatabaseManager.INSTANCE.getConnection()) {
            PreparedStatement insertStatement = connection.prepareStatement(DataOneBlocksStatements.INSERT_ONEBLOCK);

            for (Map.Entry<UUID, Location> entry : oneBlocksData.entrySet()) {
                UUID uuid = entry.getKey();
                Location location = entry.getValue();

                insertStatement.setString(1, String.valueOf(uuid));
                insertStatement.setString(2, getStringLocation(location));
                insertStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public String getStringLocation(Location location) {
        if (location == null) {
            return "";
        }
        return location.getWorld().getName() + ":" + location.getBlockX() + ":" + location.getBlockY() + ":" + location.getBlockZ();
    }

    // Check for changes.......
}
