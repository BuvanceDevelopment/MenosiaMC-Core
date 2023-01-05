package fr.buvance.menosia.module.sign.shop.manager;

import fr.buvance.menosia.Core;
import org.bukkit.entity.Player;

public class SignShopManager {

    private Core main = Core.getInstance();

    public boolean paid(Player p, int point)
    {
        if(havePoints(p, point))
        {
            this.main.getPlayerPoints().getAPI().take(p.getUniqueId(), point);
            p.sendMessage(this.main.getPrefix() + this.main.getConfig().getString("ACHAT-EFFECTUE"));
            return true;
        }
        p.sendMessage(this.main.getPrefix() + this.main.getConfig().getString("ACHAT-DENY").replace("%price%", String.valueOf(point)));
        return false;
    }

    public boolean havePoints(Player p, int point)
    {
        if(this.main.getPlayerPoints().getAPI().look(p.getUniqueId()) >= point)
        {
            return true;
        }
        return false;
    }

}
