package Mathano.Plugins.oneBlock.database;

import org.bukkit.Location;

import java.util.UUID;

public class DataOneBlocks {
    private UUID player_uuid;
    private Location location;

    public DataOneBlocks() {}

    public UUID getPlayer_uuid() {
        return player_uuid;
    }

    public void setPlayer_uuid(UUID uuid) {
        player_uuid = uuid;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location newLoc) {
        location = newLoc;
    }
}
