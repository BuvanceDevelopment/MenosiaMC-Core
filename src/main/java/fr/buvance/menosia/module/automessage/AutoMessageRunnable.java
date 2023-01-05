package fr.buvance.menosia.module.automessage;

import fr.buvance.menosia.Core;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class AutoMessageRunnable extends BukkitRunnable {

    private Core main = Core.getInstance();

    @Override
    public void run() {

        List<String> msg = this.main.getConfig().getStringList("AUTOMESSAGE");
        Random r = new Random();
        int value = r.nextInt(msg.size());

        Bukkit.getOnlinePlayers().forEach(player ->{
            player.sendMessage(Core.getLINE());
            player.sendMessage("");
            player.sendMessage(msg.get(value));
            player.sendMessage("");
            player.sendMessage(Core.getLINE());

        });



    }
}
