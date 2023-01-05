package fr.buvance.menosia.command;

import fr.buvance.menosia.Core;
import fr.buvance.menosia.utils.CooldownUtils;
import fr.buvance.menosia.utils.commands.ICommand;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public abstract class BaseCommand extends ICommand {

    private Core main = Core.getInstance();

    public void setTeleportation(Player p, int time, Location loc)
    {
        if(CooldownUtils.isOnCooldown("teleport", p))
        {
            Bukkit.getScheduler().cancelTask(this.main.getTask().get(p));
            scheduleTp(p, time, loc);
        }
        CooldownUtils.addCooldown("teleport", p, time);
        scheduleTp(p, time, loc);

    }

    public static BukkitTask scheduleTp(Player p, int time, Location loc)
    {
       return new BukkitRunnable()
        {
            public void run()
            {
                p.sendMessage(Core.getInstance().getPrefix() + Core.getInstance().getConfig().getString("TELEPORTATION-MESSAGE").replace("%tp%", String.valueOf(Core.getInstance().getConfig().getInt("TELEPORTATION-TIME"))));
                p.teleport(loc);
                Core.getInstance().saveTaskId(p, this.getTaskId());
            }
        }.runTaskLater(Core.getInstance(), 20 * time);
    }
	



}
