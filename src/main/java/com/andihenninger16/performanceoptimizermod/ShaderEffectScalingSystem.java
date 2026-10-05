package com.andihenninger16.performanceoptimizermod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

public
class ShaderEffectScalingSystem {
    private static final double MAX_SHADER_DISTANCE = 64.0;
    private static final double MIN_SHADER_SCALE = 0.5;

    public static void initialize() {
        // Initialization code
    }

    public static void applyShaderEffects(MatrixStack matrices, VertexConsumerProvider vertexConsumers, double cameraX, double cameraY, double cameraZ) {
        if (!PerformanceOptimizerMod.arePerformanceModsEnabled()) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        Vec3d playerPos = client.player.getPos();
        double distance = playerPos.distanceTo(new Vec3d(cameraX, cameraY, cameraZ));

        if (distance > MAX_SHADER_DISTANCE) {
            return;
        }

        double scale = 1.0 - (distance / MAX_SHADER_DISTANCE) * (1.0 - MIN_SHADER_SCALE);
        matrices.scale((float) scale, (float) scale, (float) scale);
    }
}
