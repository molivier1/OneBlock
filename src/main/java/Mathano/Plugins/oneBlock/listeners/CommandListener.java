package Mathano.Plugins.oneBlock.listeners;

import Mathano.Plugins.oneBlock.handlers.Menu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName()) {
            case "oneblock":
            case "ob":
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Console cannot use this command.");
                    return true;
                }

                Menu.show(((Player) sender).getPlayer());

                break;
        }
        return true;
    }
}
