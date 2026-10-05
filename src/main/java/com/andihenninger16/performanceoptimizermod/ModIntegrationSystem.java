package com.andihenninger16.performanceoptimizermod;

import java.util.ArrayList;
import java.util.List;

public
class ModIntegrationSystem {
    private static final List<String> supportedMods = new ArrayList<>();

    static {
        supportedMods.add("sodium");
        supportedMods.add("lithium");
        supportedMods.add("ferritecore");
        supportedMods.add("starlight");
    }

    public static void initialize() {
        // Initialization code
    }

    public static boolean isModSupported(String modId) {
        return supportedMods.contains(modId.toLowerCase());
    }

    public static void applyModOptimizations(String modId) {
        if (!PerformanceOptimizerMod.arePerformanceModsEnabled()) {
            return;
        }

        if (isModSupported(modId)) {
            // Apply specific optimizations for the mod
            PerformanceOptimizerMod.LOGGER.info("Applying optimizations for " + modId);
        }
    }
}
