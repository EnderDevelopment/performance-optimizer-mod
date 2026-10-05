package com.andihenninger16.performanceoptimizermod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.chunk.WorldChunk;

public
class ViewDistanceManager {
    private static final int DEFAULT_LOAD_RADIUS = 8;
    private static final int DEFAULT_BUFFER_ZONE = 2;
    private static int loadRadius = DEFAULT_LOAD_RADIUS;
    private static int bufferZone = DEFAULT_BUFFER_ZONE;

    public static void initialize() {
        // Initialization code
    }

    public static void updateViewDistance() {
        if (!PerformanceOptimizerMod.arePerformanceModsEnabled()) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        ClientWorld world = client.world;
        if (world == null) {
            return;
        }

        int playerChunkX = (int) client.player.getX() >> 4;
        int playerChunkZ = (int) client.player.getZ() >> 4;

        for (WorldChunk chunk : world.getChunkManager().getLoadedChunks()) {
            int chunkX = chunk.getPos().x;
            int chunkZ = chunk.getPos().z;

            int distanceX = Math.abs(chunkX - playerChunkX);
            int distanceZ = Math.abs(chunkZ - playerChunkZ);

            if (distanceX > loadRadius + bufferZone || distanceZ > loadRadius + bufferZone) {
                world.unloadBlock(chunkX, chunkZ);
            } else if (distanceX > loadRadius || distanceZ > loadRadius) {
                // Optional: Reduce detail for chunks in buffer zone
            }
        }
    }

    public static void setLoadRadius(int radius) {
        loadRadius = radius;
    }

    public static void setBufferZone(int zone) {
        bufferZone = zone;
    }
}
