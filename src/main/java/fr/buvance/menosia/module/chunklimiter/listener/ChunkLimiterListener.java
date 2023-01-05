package fr.buvance.menosia.module.chunklimiter.listener;

import fr.buvance.menosia.Core;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ChunkLimiterListener implements Listener
{
    private Core main = Core.getInstance();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        Chunk chunk = e.getPlayer().getLocation().getChunk();
        final int[] amount = getMaterialAmount(e.getPlayer().getLocation().getChunk());
        if(this.main.getChunkLimitManager().getChunk_limit().containsKey(e.getBlock().getType()))
        {
            if(amount[e.getBlock().getType().ordinal()] == this.main.getChunkLimitManager().getChunk_limit().get(e.getBlock().getType()))
            {
                e.setCancelled(true);
                e.getPlayer().sendMessage("§cVous avez dépassez la limite de ce block par chunk. §7§l/wiki");
                return;
            }
        }
    }

    public static int[] getMaterialAmount(final Chunk chunk) {
        final int[] amount = new int[Material.values().length];
        final int minX = chunk.getX() << 4;
        final int minZ = chunk.getZ() << 4;
        final int maxX = minX | 15;
        final int maxY = chunk.getWorld().getMaxHeight();
        final int maxZ = minZ | 15;

        for (int x = minX; x <= maxX; ++x) {
            for (int y = 0; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    ++amount[chunk.getBlock(x, y, z).getType().ordinal()];
                }
            }
        }
        return amount;
    }

}
