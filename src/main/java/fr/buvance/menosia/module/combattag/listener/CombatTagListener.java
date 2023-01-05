package fr.buvance.menosia.module.combattag.listener;

import fr.buvance.menosia.Core;
import fr.buvance.menosia.utils.CooldownUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CombatTagListener implements Listener {

    private Core main = Core.getInstance();

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e)
    {
        if(!(e.getDamager() instanceof Player) && !(e.getEntity() instanceof Player)) return;
        this.main.getCombatTagManager().setCombat((Player) e.getDamager(), (Player) e.getEntity());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        if(CooldownUtils.isOnCooldown("combat", e.getPlayer()))
        {
            e.getPlayer().setLastDamage(10000);
            Bukkit.broadcastMessage(this.main.getPrefix() + "§c" + e.getPlayer().getName() + " §fvient de se §cdéconnecter§f en §ccombat§f.");
        }
    }

}
