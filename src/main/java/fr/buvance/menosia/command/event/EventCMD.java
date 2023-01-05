package fr.buvance.menosia.command.event;

import fr.buvance.menosia.command.BaseCommand;
import fr.buvance.menosia.module.inventory.event.EventInventory;
import fr.buvance.menosia.utils.commands.CommandArgs;
import fr.buvance.menosia.utils.commands.annotations.Command;
import org.bukkit.entity.Player;

import java.io.IOException;

public class EventCMD extends BaseCommand {
    @Override
    @Command(name = "event")
    public void onCommand(CommandArgs args) throws IOException {
        if(!(args.getSender() instanceof Player)) return;
        Player p = (Player) args.getSender();
        new EventInventory().open(p);

    }
}
