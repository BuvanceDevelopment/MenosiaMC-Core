package fr.buvance.menosia.module.chunklimiter;

import lombok.Getter;
import org.bukkit.Material;

import java.util.HashMap;

public class ChunkLimiterManager {

    @Getter
    public HashMap<Material, Integer> chunk_limit = new HashMap<Material, Integer>();

    public ChunkLimiterManager()
    {
        chunk_limit.put(Material.MOB_SPAWNER, 2);
    }
}
