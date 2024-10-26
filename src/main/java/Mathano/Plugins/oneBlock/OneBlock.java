package Mathano.Plugins.oneBlock;

import Mathano.Plugins.oneBlock.listeners.CommandListener;
import Mathano.Plugins.oneBlock.managers.DatabaseManager;
import Mathano.Plugins.oneBlock.managers.OneBlocksManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class OneBlock extends JavaPlugin {
    public static OneBlock INSTANCE;

    private DatabaseManager databaseManager;
    private OneBlocksManager oneBlocksManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;

        databaseManager = new DatabaseManager();
        oneBlocksManager = new OneBlocksManager();

        initCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        oneBlocksManager.saveOneBlocksFromCache();
        databaseManager.close();
    }

    private void initCommands () {
        getCommand("oneblock").setExecutor(new CommandListener());
    }
}
