package fr.buvance.menosia.module.sign.shop.listener;

import fr.buvance.menosia.Core;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SignShopListener implements Listener {

    private Core main = Core.getInstance();

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getType() == Material.WALL_SIGN) {
                Sign s = (Sign) e.getClickedBlock();
                for(String line : s.getLines())
                {

                   if(line == s.getLine(0))
                   {
                       if((line == this.main.getConfig().getString("SIGN-SHOP-TITLE")))
                       {
                            Material mat = Material.getMaterial(s.getLine(1));
                            int prix = Integer.valueOf(s.getLine(2));
                            int qty = Integer.valueOf(s.getLine(3));

                            if(this.main.getSignShopManager().paid(p, prix))
                            {
                                p.getInventory().addItem(new ItemStack(mat, qty));
                            }
                       }
                   }
                }

            }
        }
    }
}
