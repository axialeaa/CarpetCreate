package com.axialeaa.carpetcreate.mixin.rule.tickFreezePausesAnimations;

import com.axialeaa.carpetcreate.helpers.TickFreezeHelper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.kinetics.turntable.TurntableHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(TurntableHandler.class)
public class TurntableHandlerMixin {

	@WrapOperation(method = "gameRenderTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isPaused()Z"), remap = false)
	private static boolean shouldPause(Minecraft instance, Operation<Boolean> original) {
		return TickFreezeHelper.isPaused(instance);
	}

}
