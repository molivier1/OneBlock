package Mathano.Plugins.oneBlock.handlers;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Menu {
    public static void show(Player player) {
        Gui gui = Gui.gui()
                .title(Component.text("OneBlock Menu"))
                .rows(6)
                .create();

        GuiItem go = ItemBuilder.from(Material.GRASS_BLOCK).asGuiItem(inventoryClickEvent -> {
            // if player has oneblock
            // player tp

            // else...
            Creation.create(player);
        });

        gui.addItem(go);

        gui.open(player);
    }
}
