package fr.buvance.menosia.command.rtp;

import fr.buvance.menosia.Core;
import fr.buvance.menosia.command.BaseCommand;
import fr.buvance.menosia.utils.commands.CommandArgs;
import fr.buvance.menosia.utils.commands.annotations.Command;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Random;

public class RandomTeleportCMD extends BaseCommand {

    private Core main = Core.getInstance();

    @Override
    @Command(name = {"rtp", "randomtp", "wild"})
    public void onCommand(CommandArgs args) throws IOException {
        if(!(args.getSender() instanceof Player)) return;

        Player p = (Player)args.getSender();
        Random r = new Random();

        int min_x = this.main.getConfig().getInt("RANDOMTP-MINX");
        int max_x = this.main.getConfig().getInt("RANDOMTP-MAXX");

        int min_z = this.main.getConfig().getInt("RANDOMTP-MINZ");
        int max_z = this.main.getConfig().getInt("RANDOMTP-MAXZ");

        int x = r.nextInt(max_x + min_x) + min_x;
        int z = r.nextInt(max_z + min_z) + min_x;
        int y = p.getWorld().getHighestBlockAt(x, z).getY();;

        Location loc = new Location(Bukkit.getWorld(this.main.getConfig().getString("RANDOMTP-WORLD")), x, y, z);
        this.setTeleportation(p, 5, loc);


    }
}
