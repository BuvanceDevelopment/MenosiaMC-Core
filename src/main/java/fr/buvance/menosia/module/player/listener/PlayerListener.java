package fr.buvance.menosia.module.player.listener;

import fr.buvance.menosia.Core;
import fr.buvance.menosia.command.BaseCommand;
import fr.buvance.menosia.utils.CooldownUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class PlayerListener implements Listener {

    private Core main = Core.getInstance();

    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
        if(!(e.getEntity() instanceof Player)) return;
        Player p = e.getEntity();
        if(p.hasPermission(this.main.getConfig().getString("NOLOSELEVEL-PERM"))) {
            e.setDroppedExp(0);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();
        if(CooldownUtils.isOnCooldown("teleport", p)) {
            if (p.hasPermission("menosia.admin"))
            {
                e.setCancelled(false);
                return;
            }
            Bukkit.getScheduler().cancelTask(Core.getInstance().getTask().get(p));
            CooldownUtils.removeCooldown("teleport", p);
            p.sendMessage(Core.getInstance().getPrefix() + Core.getInstance().getConfig().getString("TELEPORTATION-CANCEL"));

        }
    }
}
