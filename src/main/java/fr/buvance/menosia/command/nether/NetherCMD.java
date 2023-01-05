package fr.buvance.menosia.command.farmzone;

import fr.buvance.menosia.Core;
import fr.buvance.menosia.command.BaseCommand;
import fr.buvance.menosia.utils.CooldownUtils;
import fr.buvance.menosia.utils.commands.CommandArgs;
import fr.buvance.menosia.utils.commands.annotations.Command;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;

public class NetherCMD extends BaseCommand {

    private Core main = Core.getInstance();
    private static String nether_perm = Core.getInstance().getConfig().getString("NETHER-PERM");


    @Override
    @Command(name = "nether")
    public void onCommand(CommandArgs args) throws IOException {

        if(!(args.getSender() instanceof Player)) return;
        Player p = (Player) args.getSender();
        Location loc = new Location(Bukkit.getWorld(this.main.getConfig().getString("NETHER-WORLD")), this.main.getConfig().getInt("NETHER-POSX"), this.main.getConfig().getInt("NETHER-POSY"), this.main.getConfig().getInt("NETHER-POSZ"), this.main.getConfig().getInt("NETHER-YAW"), this.main.getConfig().getInt("NETHER-PITCH"));
        if(!p.hasPermission(nether_perm))
        {
            this.main.noPerm(p);
            return;
        }
        this.setTeleportation(p, this.main.getConfig().getInt("TELEPORTATION-TIME"), loc);



    }
}
