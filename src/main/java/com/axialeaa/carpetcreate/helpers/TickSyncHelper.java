package com.axialeaa.carpetcreate.helpers;

import carpet.CarpetServer;
import carpet.fakes.MinecraftServerInterface;
import carpet.helpers.ServerTickRateManager;
import carpet.helpers.TickRateManager;
import com.axialeaa.carpetcreate.CarpetCreateSettings;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.OptionalDouble;

public class TickSyncHelper {

    /**
     * @return if the client should consider the game "paused", taking into account tick freeze, superhot and the ESC screen.
     */
    public static boolean isGamePaused() {
        Minecraft mc = Minecraft.getInstance();
        if (CarpetCreateSettings.tickSyncedCreateAnimations) {
            TickRateManager trm = ((MinecraftServerInterface) CarpetServer.minecraft_server).getTickRateManager();
            if (trm != null)
                return !trm.runsNormally() || mc.isPaused();
        }
        return mc.isPaused();
    }

    /**
     * @param f The value to multiply.
     * @return a float equivalent to the parameter multiplied by {@link TickSyncHelper#getTickRateFactor()}.
     */
    public static float scaleByTickRateFactor(float f) {
        return CarpetCreateSettings.tickSyncedCreateAnimations ? f * getTickRateFactor() : f;
    }

    /**
     * Useful for scaling values according to how fast the game is moving, without having to bake the tick rate into the value itself.
     * @return a float equivalent to how many times larger the current tick rate is than 20.
     */
    private static float getTickRateFactor() {
        ServerTickRateManager trm = ((MinecraftServerInterface) CarpetServer.minecraft_server).getTickRateManager();
        if (trm != null) {
            OptionalDouble mspt = Arrays.stream(CarpetServer.minecraft_server.tickTimes).average();
            if (mspt.isPresent()) {
                float tickRate = 1000 / Math.max(trm.isInWarpSpeed() ? 0.0f : trm.mspt(), (float) mspt.getAsDouble() * 1.0E-6F);
                return tickRate / 20;
            }
        }
        return 1;
    }

}
