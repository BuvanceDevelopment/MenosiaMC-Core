package fr.buvance.menosia;

import fr.buvance.menosia.command.combattag.CombatTagCMD;
import fr.buvance.menosia.command.event.EventCMD;
import fr.buvance.menosia.command.farmzone.EndCMD;
import fr.buvance.menosia.command.farmzone.FarmZoneCMD;
import fr.buvance.menosia.command.farmzone.MinageCMD;
import fr.buvance.menosia.command.farmzone.NetherCMD;
import fr.buvance.menosia.command.rtp.RandomTeleportCMD;
import fr.buvance.menosia.module.automessage.AutoMessageRunnable;
import fr.buvance.menosia.module.chunklimiter.ChunkLimiterManager;
import fr.buvance.menosia.module.chunklimiter.listener.ChunkLimiterListener;
import fr.buvance.menosia.module.combattag.listener.CombatTagListener;
import fr.buvance.menosia.module.combattag.manager.CombatTagManager;
import fr.buvance.menosia.module.player.listener.PlayerListener;
import fr.buvance.menosia.module.sign.shop.listener.SignShopListener;
import fr.buvance.menosia.module.sign.shop.manager.SignShopManager;
import fr.buvance.menosia.utils.commands.CommandFramework;
import fr.mrmicky.fastinv.FastInvManager;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Core extends JavaPlugin {

    @Getter
    public static String LINE = "§7" + ChatColor.STRIKETHROUGH + StringUtils.repeat("-", 33);
    @Getter public String prefix;

    @Getter private static Core instance;

    @Getter public CombatTagManager combatTagManager;
    @Getter public ChunkLimiterManager chunkLimitManager;
    @Getter public SignShopManager signShopManager;

    private CommandFramework framework;
    @Getter private PlayerPoints playerPoints;

    @Getter public HashMap<Player, Integer>task;

    public void onEnable()
    {
        this.load();
    }

    public void onDisable()
    {
        this.unload();
    }

    public void load()
    {
        instance = this;
        this.registerFramework();
        FastInvManager.register(this);

        this.task = new HashMap<>();
        this.prefix = Core.getInstance().getConfig().getString("PREFIX");

        System.out.println(this.LINE);
        System.out.println("");
        System.out.println("    §f§lMenosia§c§lMC §7: §aENABLED");
        System.out.println("");
        System.out.println(this.LINE);
        this.loadManager();
        this.loadEvents();
        this.loadCommand();
        this.loadTask();
    }

    public void unload()
    {
        System.out.println(this.LINE);
        System.out.println("");
        System.out.println("    §f§lMenosia§c§lMC §7: §aENABLED");
        System.out.println("");
        System.out.println(this.LINE);

        this.saveDefaultConfig();
    }

    public void loadManager()
    {
        this.combatTagManager = new CombatTagManager();
        this.chunkLimitManager = new ChunkLimiterManager();
        this.signShopManager = new SignShopManager();

        this.hookPlayerPoints();

    }

    public void loadEvents()
    {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new CombatTagListener(), this);
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new ChunkLimiterListener(), this);
        pm.registerEvents(new SignShopListener(), this);

    }

    public void loadCommand()
    {
        this.framework.registerCommands(new CombatTagCMD());
        this.framework.registerCommands(new RandomTeleportCMD());
        this.framework.registerCommands(new FarmZoneCMD());
        this.framework.registerCommands(new EventCMD());
        this.framework.registerCommands(new NetherCMD());
        this.framework.registerCommands(new EndCMD());
        this.framework.registerCommands(new MinageCMD());

    }

    public void loadTask()
    {
        new AutoMessageRunnable().runTaskTimer(this, 0L, 20 * this.getConfig().getInt("AUTOMESSAGE-TIMER"));
    }

    public void registerFramework()
    {
        this.framework = new CommandFramework(this);
    }

    public void noPerm(Player p)
    {
       p.sendMessage(this.getPrefix() + "§cVous n'avez pas la permission.");
    }

    public void saveTaskId(Player p, int taskId)
    {
        this.task.put(p, taskId);
    }

    private boolean hookPlayerPoints() {
        final Plugin plugin = this.getServer().getPluginManager().getPlugin("PlayerPoints");
        playerPoints = PlayerPoints.class.cast(plugin);
        return playerPoints != null;
    }


}
