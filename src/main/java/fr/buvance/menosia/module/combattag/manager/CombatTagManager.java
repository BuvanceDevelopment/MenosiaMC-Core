package fr.buvance.menosia.module.combattag.manager;

import fr.buvance.menosia.Core;
import fr.buvance.menosia.utils.CooldownUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CombatTagManager {

    private Core main;
    private HashMap<String, Integer> combat;

    public CombatTagManager()
    {
        this.combat = new HashMap<>();
        this.main = Core.getInstance();
    }

    public void setCombat(Player attacker, Player victim)
    {
        if(CooldownUtils.isOnCooldown("combat", attacker))
        {
            CooldownUtils.removeCooldown("combat", attacker);
            CooldownUtils.addCooldown("combat", attacker, this.main.getConfig().getInt("COMBATTAG-TIMER"));
            return;
        }
        else if(CooldownUtils.isOnCooldown("combat", victim))
        {
            CooldownUtils.removeCooldown("combat", attacker);
            CooldownUtils.addCooldown("combat", attacker, this.main.getConfig().getInt("COMBATTAG-TIMER"));
            return;
        }
        CooldownUtils.addCooldown("combat", attacker, this.main.getConfig().getInt("COMBATTAG-TIMER"));
        this.combat.put(attacker.getName(), CooldownUtils.getCooldownForPlayerInt("combat", attacker));

        CooldownUtils.addCooldown("combat", victim, this.main.getConfig().getInt("COMBATTAG-TIMER"));
        this.combat.put(victim.getName(), CooldownUtils.getCooldownForPlayerInt("combat", victim));

        attacker.sendMessage(this.main.getPrefix() + this.main.getConfig().getString("COMBATTAG-ATTACKER-MESSAGE").replace("%player%", victim.getName()));
        victim.sendMessage(this.main.getPrefix() + this.main.getConfig().getString("COMBATTAG-VICTIM-MESSAGE").replace("%player%", attacker.getName()));

    }
}
