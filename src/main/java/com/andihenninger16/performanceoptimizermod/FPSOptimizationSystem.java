package com.andihenninger16.performanceoptimizermod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;

public
class FPSOptimizationSystem {
    public static void initialize() {
        // Initialization code
    }

    public static void applyOptimizations() {
        if (!PerformanceOptimizerMod.arePerformanceModsEnabled()) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();

        // Apply various FPS optimizations
        client.options.getGraphicsMode().setValue(SimpleOption.GraphicsMode.FAST);
        client.options.getParticles().setValue(SimpleOption.ParticlesMode.MINIMAL);
        client.options.getEntityDistanceScaling().setValue(0.5);
        client.options.getSimulationDistance().setValue(4);
        client.options.getViewDistance().setValue(4);
    }
}
