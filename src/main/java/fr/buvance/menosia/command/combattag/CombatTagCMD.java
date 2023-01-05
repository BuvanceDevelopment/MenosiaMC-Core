package fr.buvance.menosia.command.combattag;

import fr.buvance.menosia.Core;
import fr.buvance.menosia.command.BaseCommand;
import fr.buvance.menosia.utils.CooldownUtils;
import fr.buvance.menosia.utils.commands.CommandArgs;
import fr.buvance.menosia.utils.commands.annotations.Command;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CombatTagCMD extends BaseCommand {

    private Core main = Core.getInstance();

    @Override
    @Command(name= {"ct", "combattag"})
    public void onCommand(CommandArgs args) throws IOException {
        Player p = args.getPlayer();
        if(CooldownUtils.isOnCooldown("combat", p)) {
            p.sendMessage(this.main.getPrefix() + "Vous êtes en §ccombat§f pour encore §7: §c" + CooldownUtils.getCooldownForPlayerInt("combat", p) + "s");
            return;
        }
        p.sendMessage(this.main.getPrefix() + "Vous n'êtes pas en §ccombat§f.");

    }
}
