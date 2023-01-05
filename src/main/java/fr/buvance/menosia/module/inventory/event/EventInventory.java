package fr.buvance.menosia.module.inventory.event;

import fr.buvance.menosia.Core;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.List;

public class EventInventory extends FastInv {

    private Core main = Core.getInstance();
    private boolean preventClose = false;


    public EventInventory() {
        super(27, Core.getInstance().getConfig().getString("INVENTORY-EVENT-NAME"));

        List<String> info_lore = this.main.getConfig().getStringList("INVENTORY-EVENT-INFOSLORE");
        List<String> event_lore = this.main.getConfig().getStringList("INVENTORY-EVENT-LORE");
        List<String> totem1_lore = this.main.getConfig().getStringList("INVENTORY-EVENT-TOTEM-1-LORE");
        List<String> totem2_lore = this.main.getConfig().getStringList("INVENTORY-EVENT-TOTEM-2-LORE");
        List<String> totem3_lore = this.main.getConfig().getStringList("INVENTORY-EVENT-TOTEM-3-LORE");

        setItem(0, new ItemBuilder(Material.getMaterial(this.main.getConfig().getString("INVENTORY-EVENT-ITEMINFOS"))).name("").addLore(info_lore).build());

        setItem(9, new ItemBuilder(Material.getMaterial(this.main.getConfig().getString("INVENTORY-EVENT-INFOS"))).name(this.main.getConfig().getString("INVENTORY-EVENT-INFOS-NAME")).addLore(event_lore).build());

        setItem(10, new ItemBuilder(Material.getMaterial(this.main.getConfig().getString("INVENTORY-EVENT-TOTEM-1"))).name(this.main.getConfig().getString("INVENTORY-EVENT-TOTEM-1-NAME")).addLore(totem1_lore).build(), e -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/warp totem1 " + e.getWhoClicked().getName()));
        setItem(11, new ItemBuilder(Material.getMaterial(this.main.getConfig().getString("INVENTORY-EVENT-TOTEM-2"))).name(this.main.getConfig().getString("INVENTORY-EVENT-TOTEM-2-NAME")).addLore(totem2_lore).build(), e -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/warp totem2 " + e.getWhoClicked().getName()));
        setItem(12, new ItemBuilder(Material.getMaterial(this.main.getConfig().getString("INVENTORY-EVENT-TOTEM-3"))).name(this.main.getConfig().getString("INVENTORY-EVENT-TOTEM-3-NAME")).addLore(totem3_lore).build(), e -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/warp totem3 " + e.getWhoClicked().getName()));

    }
}
