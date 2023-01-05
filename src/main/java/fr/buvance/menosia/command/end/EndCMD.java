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

public class EndCMD extends BaseCommand {

    private Core main = Core.getInstance();
    private static String end_perm = Core.getInstance().getConfig().getString("NETHER-PERM");


    @Override
    @Command(name = "nether")
    public void onCommand(CommandArgs args) throws IOException {

        if(!(args.getSender() instanceof Player)) return;
        Player p = (Player) args.getSender();
        Location loc = new Location(Bukkit.getWorld(this.main.getConfig().getString("END-WORLD")), this.main.getConfig().getInt("END-POSX"), this.main.getConfig().getInt("END-POSY"), this.main.getConfig().getInt("END-POSZ"), this.main.getConfig().getInt("END-YAW"), this.main.getConfig().getInt("END-PITCH"));
        if(!p.hasPermission(end_perm))
        {
            this.main.noPerm(p);
            return;
        }
        this.setTeleportation(p, this.main.getConfig().getInt("TELEPORTATION-TIME"), loc);



    }
}
