package Mathano.Plugins.oneBlock.handlers;

import Mathano.Plugins.oneBlock.managers.OneBlocksManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Creation {
    public static void create(Player player) {
        Location location = player.getLocation();
        OneBlocksManager.oneBlocksData.put(player.getUniqueId(), location);

        Block block = location.getBlock();

        block.setType(Material.GRASS_BLOCK);


    }
}
