package com.andihenninger16.performanceoptimizermod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public
class PerformanceOptimizerMod implements ModInitializer {
    public static final String MOD_ID = "performanceoptimizermod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static KeyBinding farmModeKeyBinding;
    private static KeyBinding togglePerformanceModsKeyBinding;
    private static boolean farmModeEnabled = false;
    private static boolean performanceModsEnabled = true;

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Performance Optimizer Mod");

        // Register keybindings
        farmModeKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "key.performanceoptimizermod.farm_mode",
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_F,
        "category.performanceoptimizermod.performance"
        ));

        togglePerformanceModsKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "key.performanceoptimizermod.toggle_performance_mods",
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_P,
        "category.performanceoptimizermod.performance"
        ));

        // Register client tick events
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (farmModeKeyBinding.wasPressed()) {
                farmModeEnabled = !farmModeEnabled;
                LOGGER.info("Farm mode " + (farmModeEnabled ? "enabled" : "disabled"));
            }

            if (togglePerformanceModsKeyBinding.wasPressed()) {
                performanceModsEnabled = !performanceModsEnabled;
                LOGGER.info("Performance mods " + (performanceModsEnabled ? "enabled" : "disabled"));
            }
        });

        // Initialize other systems
        ShaderEffectScalingSystem.initialize();
        ViewDistanceManager.initialize();
        FPSOptimizationSystem.initialize();
        ModIntegrationSystem.initialize();
    }

    public static boolean isFarmModeEnabled() {
        return farmModeEnabled;
    }

    public static boolean arePerformanceModsEnabled() {
        return performanceModsEnabled;
    }
}
