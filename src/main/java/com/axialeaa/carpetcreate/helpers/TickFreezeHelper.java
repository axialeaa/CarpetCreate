package com.axialeaa.carpetcreate.helpers;

import carpet.fakes.LevelInterface;
import carpet.helpers.TickRateManager;
import com.axialeaa.carpetcreate.CarpetCreateSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

public class TickFreezeHelper {

    public static boolean isPaused(Minecraft minecraft) {
        ClientLevel level = minecraft.level;
        if (level != null && CarpetCreateSettings.tickFreezePausesAnimations) {
            TickRateManager tickRateManager = ((LevelInterface) level).tickRateManager();
            return !tickRateManager.runsNormally();
        }
        return minecraft.isPaused();
    }

}
