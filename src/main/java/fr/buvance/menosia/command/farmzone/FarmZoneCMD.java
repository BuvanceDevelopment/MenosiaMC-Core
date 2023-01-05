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

public class FarmZoneCMD extends BaseCommand {

    private Core main = Core.getInstance();
    private static String farmzone_perm = Core.getInstance().getConfig().getString("FARMZONE-PERM");


    @Override
    @Command(name = "farmzone")
    public void onCommand(CommandArgs args) throws IOException {

        if(!(args.getSender() instanceof Player)) return;
        Player p = (Player) args.getSender();
        Location loc = new Location(Bukkit.getWorld(this.main.getConfig().getString("FARMZONE-WORLD")), this.main.getConfig().getInt("FARMZONE-POSX"), this.main.getConfig().getInt("FARMZONE-POSY"), this.main.getConfig().getInt("FARMZONE-POSZ"), this.main.getConfig().getInt("FARMZONE-YAW"), this.main.getConfig().getInt("FARMZONE-PITCH"));
        if(!p.hasPermission(farmzone_perm))
        {
            this.main.noPerm(p);
            return;
        }
        this.setTeleportation(p, this.main.getConfig().getInt("TELEPORTATION-TIME"), loc);



    }
}
