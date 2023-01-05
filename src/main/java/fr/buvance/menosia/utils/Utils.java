package fr.buvance.menosia.utils;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class Utils {

	public final static String LINE = "§7§m" + StringUtils.repeat("-", 53);

	public static String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public static void sendPacket(Player player, Packet packet) {
		PlayerConnection packetHandler = ((CraftPlayer) player).getHandle().playerConnection;
		packetHandler.sendPacket(packet);
	}

	public static void writeItem(PacketDataSerializer writer, ItemStack item) {
		writer.a(CraftItemStack.asNMSCopy(item));
	}

	public static String readString(PacketDataSerializer reader) throws IOException {
		return reader.c(999999);
	}

	@SuppressWarnings("deprecation")
	public static Collection<? extends Player> getOnlinePlayers() {
		return Bukkit.getOnlinePlayers();
	}

	public static boolean isInventoryFull(Player player) {
		return player.getInventory().firstEmpty() == -1;
	}

	public static File getFormatedFile(Plugin plugin, String fileName) {
		return new File(plugin.getDataFolder(), fileName);
	}
}
