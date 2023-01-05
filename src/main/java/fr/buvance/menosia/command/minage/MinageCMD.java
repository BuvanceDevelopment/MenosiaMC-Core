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

public class MinageCMD extends BaseCommand {

    private Core main = Core.getInstance();
    private static String minage_perm = Core.getInstance().getConfig().getString("MINAGE-PERM");


    @Override
    @Command(name = "nether")
    public void onCommand(CommandArgs args) throws IOException {

        if(!(args.getSender() instanceof Player)) return;
        Player p = (Player) args.getSender();
        Location loc = new Location(Bukkit.getWorld(this.main.getConfig().getString("MINAGE-WORLD")), this.main.getConfig().getInt("MINAGE-POSX"), this.main.getConfig().getInt("MINAGE-POSY"), this.main.getConfig().getInt("MINAGE-POSZ"), this.main.getConfig().getInt("MINAGE-YAW"), this.main.getConfig().getInt("MINAGE-PITCH"));
        if(!p.hasPermission(minage_perm))
        {
            this.main.noPerm(p);
            return;
        }
        this.setTeleportation(p, this.main.getConfig().getInt("TELEPORTATION-TIME"), loc);



    }
}
